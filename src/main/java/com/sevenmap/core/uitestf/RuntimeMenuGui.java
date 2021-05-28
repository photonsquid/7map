package com.sevenmap.core.uitestf;

import com.sevenmap.core.uitestf.events.DbSearchEvent;
import com.sevenmap.spinel.Engine;
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
        ImGui.begin("Search bar", ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize); // https://github.com/ocornut/imgui/issues/589
        if (ImGui.inputText("Search", content, ImGuiInputTextFlags.CallbackResize)) {
            Engine.getInstance().getWindow().throwEvent(new DbSearchEvent(Engine.getInstance()));
        }
        ImGui.end();
    }

    public String getSearchQuery() {
        return content.toString();
    }

}
