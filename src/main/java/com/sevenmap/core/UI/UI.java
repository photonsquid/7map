package com.sevenmap.core.UI;

import com.sevenmap.core.Loadable;
import com.sevenmap.exceptions.ExitOverrideException;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.GuiLayer;
import com.sevenmap.spinel.elements.GuiNode;
import com.sevenmap.spinel.elements.RootNode;
import com.sevenmap.spinel.utils.FileChooser;

import org.apache.commons.cli.CommandLine;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCond;

public class UI extends Loadable {
    private GuiNode mapLoadingLayer;
    private GuiNode runtimeMenus;
    private GuiNode overlay;
    private GuiLayer searchBar;
    private FileChooser fc;
    private boolean isReactive = true;
    private Engine parentEngine;

    /**
     * Create a new UI and initialize its components.
     * 
     * @param engine The target engine
     */
    public UI(Engine engine) {
        parentEngine = engine;
        RootNode root = engine.getGuiRoot();
        setUpStyle();
        mapLoadingLayer = new GuiNode("Map Loading");
        runtimeMenus = new GuiNode("Runtime Menus");
        overlay = new GuiNode("overlay");
        searchBar = new GuiLayer("Search bar");

        mapLoadingLayer.setParent(root);
        runtimeMenus.setParent(root);
        overlay.setParent(root);
        searchBar.setParent(root);

        mapLoadingLayer.hide();
        runtimeMenus.hide();
        overlay.hide();
        searchBar.hide();

    }

    /**
     * {@inheritDoc}
     */
    public void load(CommandLine cl) {
        setUpContent();
    }

    private void setUpStyle() {
        ImGuiStyle style = ImGui.getStyle();
        style.setWindowRounding(10);
    }

    private void setUpContent() {
        mapLoadingLayer.addLogic(() -> {
            int[] size = new int[] { 370, 95 };
            ImGui.setNextWindowSize(size[0], size[1]);
            ImGui.setNextWindowPos(ImGui.getMainViewport().getCenter().x - size[0] / 2,
                    ImGui.getMainViewport().getCenter().y - size[1] / 2, ImGuiCond.Once);
            ImGui.begin("Setting things up");
            ImGui.alignTextToFramePadding();
            ImGui.text("Load new map");

            if (ImGui.button("Browse...") && isReactive) {
                fc = new FileChooser();
                toggleReactivity(); // block user interaction
            }
            ImGui.sameLine();
            if (ImGui.button("exit")) {
                throw new ExitOverrideException(0);
            }
            ImGui.end();

            if (fc != null && fc.isDone() && !isReactive) { // disable overlay (interaction allowed)
                toggleReactivity();
            }
        });

        overlay.addLogic(() -> {
            ImGui.setNextWindowSize(ImGui.getMainViewport().getSizeX(), ImGui.getMainViewport().getSizeY());
            ImGui.setNextWindowPos(0, 0);
        });
    }

    public void toggleReactivity() {
        isReactive = !isReactive;
        if (isReactive) {
            overlay.hide();
        } else {
            overlay.show();
        }
    }
}
