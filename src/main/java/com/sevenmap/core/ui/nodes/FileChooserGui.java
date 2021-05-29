package com.sevenmap.core.ui.nodes;

import com.sevenmap.core.ui.UI;
import com.sevenmap.core.ui.events.FileLoadedEvent;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.gui.GuiNode;
import com.sevenmap.spinel.utils.FileChooser;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;

public class FileChooserGui extends GuiNode {
    private int[] size = new int[] { 370, 95 };
    private UI parentUI;
    private FileChooser fc;
    private String file;

    public FileChooserGui(UI parentUI) {
        super();
        this.parentUI = parentUI;
    }

    public FileChooserGui(UI parentUI, String name) {
        super(name);
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
            fc = new FileChooser("*.osm");
            parentUI.toggleReactivity(); // block user interaction
        }
        ImGui.sameLine();
        if (ImGui.button("exit")) {
            Engine.getInstance().stop();
        }
        ImGui.end();

        if (fc != null && fc.isDone() && !parentUI.isReactive()) {
            // disable overlay (interaction allowed)
            parentUI.toggleReactivity();
            file = fc.getFilePath();
            if (file != null) {
                Engine.getInstance().getWindow().throwEvent(new FileLoadedEvent());
            }
        }
    }

    public String getFilePath() {
        return file;
    }
}
