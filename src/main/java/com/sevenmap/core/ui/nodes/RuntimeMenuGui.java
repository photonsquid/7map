package com.sevenmap.core.ui.nodes;

import com.sevenmap.core.ui.events.DbSearchEvent;
import com.sevenmap.core.ui.events.FileOpeningEvent;
import com.sevenmap.core.ui.events.ZoomEvent;
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
        int flags;

        // Search bar
        ImGui.setNextWindowSize(600, 40);
        ImGui.setNextWindowPos(10, 20);
        flags = ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize
                | ImGuiWindowFlags.NoBackground | ImGuiInputTextFlags.EnterReturnsTrue;
        ImGui.begin("Search bar", flags);
        if (ImGui.inputText("Search", content, ImGuiInputTextFlags.CallbackResize)) {
            Engine.getInstance().getWindow().throwEvent(new DbSearchEvent());
        }
        ImGui.end();

        // Control buttons
        int size[] = new int[] { 70, 100 };
        ImGui.setNextWindowSize(size[0], size[1]);
        ImGui.setNextWindowPos(viewport.getSizeX() - size[0], viewport.getSizeY() - size[1]);
        flags = ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize
                | ImGuiWindowFlags.NoBackground;
        ImGui.begin("Control buttons", flags);
        if (ImGui.button("+")) {
            // throw zoom in event
            Engine.getInstance().getWindow().throwEvent(new ZoomEvent(true));
        }
        if (ImGui.button("-")) {
            // throw zoom out event
            Engine.getInstance().getWindow().throwEvent(new ZoomEvent(false));
        }
        ImGui.end();

        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("File")) {
                if (ImGui.menuItem("New")) {
                    System.out.println("Not implemented yet");
                }
                if (ImGui.menuItem("Open")) {
                    Engine.getInstance().getWindow().throwEvent(new FileOpeningEvent());
                }
                if (ImGui.menuItem("Save")) {
                    System.out.println("Not implemented yet");
                }
                if (ImGui.menuItem("Exit")) {
                    Engine.getInstance().stop();
                }
                ImGui.endMenu();
            }
            if (ImGui.beginMenu("Edit")) {
                if (ImGui.menuItem("Cut")) {
                }
                if (ImGui.menuItem("Copy")) {
                }
                if (ImGui.menuItem("Paste")) {
                }
                ImGui.endMenu();
            }
            if (ImGui.beginMenu("View")) {
                if (ImGui.menuItem("Fullscreen")) {
                }
                ImGui.endMenu();
            }
            ImGui.endMainMenuBar();
        }

    }

    public String getSearchQuery() {
        return content.toString();
    }

}
