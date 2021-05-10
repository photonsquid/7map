package com.sevenmap.ui.elements.colliders;

import com.sevenmap.ui.elements.Node;

import imgui.ImGui;

public class GuiLayer extends Node {

    public void draw() {
        ImGui.begin("layer");

        ImGui.end();
    }
}
