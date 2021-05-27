package com.sevenmap.core.ui;

import com.sevenmap.exceptions.ExitOverrideException;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.gui.GuiNode;
import com.sevenmap.spinel.scheduling.events.FileLoadedEvent;
import com.sevenmap.spinel.utils.FileChooser;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;

public class FileChooserGui extends GuiNode {
    private int[] size = new int[] { 370, 95 };
    private UI parentUI;
    private Engine parentEngine;
    private FileChooser fc;
    private String filename;

    public FileChooserGui(UI parentUI, Engine engine) {
        super();
        parentEngine = engine;
        this.parentUI = parentUI;
    }

    public FileChooserGui(UI parentUI, Engine engine, String name) {
        super(name);
        parentEngine = engine;
        this.parentUI = parentUI;
    }

    @Override
    public void compute() {
        ImGui.setNextWindowSize(size[0], size[1]);
        ImGui.setNextWindowPos(ImGui.getMainViewport().getCenter().x - size[0] / 2,
                ImGui.getMainViewport().getCenter().y - size[1] / 2, ImGuiCond.Once);
        ImGui.begin("Setting things up",
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse);
        ImGui.alignTextToFramePadding();
        ImGui.text("Load new map");

        if (ImGui.button("Browse...") && parentUI.isReactive()) {
            fc = new FileChooser();
            parentUI.toggleReactivity(); // block user interaction
        }
        ImGui.sameLine();
        if (ImGui.button("exit")) {
            throw new ExitOverrideException(0);
        }
        ImGui.end();

        if (fc != null && fc.isDone() && !parentUI.isReactive()) {
            // disable overlay (interaction allowed)
            parentUI.toggleReactivity();
            filename = fc.getFilename();
            if (filename != null) {
                parentEngine.getWindow().throwEvent(new FileLoadedEvent(parentEngine));
            }
        }
    }

    public String getFilename() {
        return filename;
    }
}
