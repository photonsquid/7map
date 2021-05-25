package com.sevenmap.core.UI;

import com.sevenmap.core.Loadable;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.RootNode;
import com.sevenmap.spinel.elements.gui.FileChooserGui;
import com.sevenmap.spinel.elements.gui.GuiLayer;
import com.sevenmap.spinel.elements.gui.GuiNode;
import com.sevenmap.spinel.scheduling.events.FileLoadedEvent;

import org.apache.commons.cli.CommandLine;

import imgui.ImGui;
import imgui.ImGuiStyle;

public class UI extends Loadable {
    private FileChooserGui mapLoadingLayer;
    private GuiNode runtimeMenus;
    private GuiNode overlay;
    private GuiLayer searchBar;
    private Engine parentEngine;

    private boolean isReactive = true;
    private CommandLine clInput;

    /**
     * Create a new UI and initialize its components.
     * 
     * @param engine The target engine
     */
    public UI(Engine engine) {
        parentEngine = engine;
        RootNode root = parentEngine.getGuiRoot();
        setUpStyle();
        mapLoadingLayer = new FileChooserGui(this, engine, "Map Loading");
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
        clInput = cl;
        setUpContent();
    }

    /**
     * Load file chooser UI.
     */
    public void ldFileChooser() {
        hideAll();
        mapLoadingLayer.show();
        parentEngine.getWindow().onEvent(new FileLoadedEvent(parentEngine), () -> {
            ldMapDisplay();
        });
    }

    public void ldMapDisplay() {
        // TODO: Map display gui
        System.out.println("loading map display");
    }

    /**
     * Set up UI styles and themes.
     */
    private void setUpStyle() {
        ImGuiStyle style = ImGui.getStyle();
        style.setWindowRounding(10);
    }

    private void setUpContent() {
        overlay.addLogic(() -> {
            ImGui.setNextWindowSize(ImGui.getMainViewport().getSizeX(), ImGui.getMainViewport().getSizeY());
            ImGui.setNextWindowPos(0, 0);
        });
    }

    /**
     * Switch between non-reactive and reactive mode.
     * <p>
     * Toggles user input reactivity capabilities by setting up a layer on top of
     * the elements already on screen
     * <p/>
     */
    public void toggleReactivity() {
        isReactive = !isReactive;
        if (isReactive) {
            overlay.hide();
        } else {
            overlay.show();
        }
    }

    /**
     * Returns true if the window reacts to user input.
     */
    public boolean isReactive() {
        return isReactive;
    }

    private void hideAll() {
        mapLoadingLayer.hide();
        runtimeMenus.hide();
        overlay.hide();
        searchBar.hide();
    }
}
