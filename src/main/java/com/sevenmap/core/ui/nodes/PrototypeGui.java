package com.sevenmap.core.ui.nodes;

import com.sevenmap.spinel.elements.gui.GuiNode;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImString;

public class PrototypeGui extends GuiNode {

    ImString content = new ImString("");

    public PrototypeGui() {
        super();
    }

    public PrototypeGui(String name) {
        super(name);
    }

    @Override
    public void compute() {
        ImGui.begin(name);
        ImGui.inputText("Search", content, ImGuiInputTextFlags.CallbackResize);
        ImGui.end();

        ImGui.begin("Simple text");
        ImGui.text("text content");
        ImGui.end();
    }

}
