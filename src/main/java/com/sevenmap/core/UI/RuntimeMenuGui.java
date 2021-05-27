package com.sevenmap.core.ui;

import com.sevenmap.spinel.elements.gui.GuiNode;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImString;

public class RuntimeMenuGui extends GuiNode {

    ImString content = new ImString("");

    public RuntimeMenuGui() {
    }

    public RuntimeMenuGui(String name) {
        super(name);
    }

    @Override
    public void compute() {
        ImGui.setNextWindowSize(600, 40);
        ImGui.setNextWindowPos(10, 10);
        ImGui.begin("Search bar", ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize);
        ImGui.inputText("Search", content, ImGuiInputTextFlags.CallbackResize);
        ImGui.end();
    }

}
