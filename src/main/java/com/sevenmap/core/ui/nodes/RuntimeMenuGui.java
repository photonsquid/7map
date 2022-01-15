package com.sevenmap.core.ui.nodes;

import com.sevenmap.core.ui.UI;
import com.sevenmap.core.ui.events.DbSearchEvent;
import com.sevenmap.core.ui.events.FileLoadedEvent;
import com.sevenmap.core.ui.events.ToggleThemeEvent;
import com.sevenmap.core.ui.events.ZoomEvent;
import io.github.spinel.Engine;
import io.github.spinel.elements.gui.GuiNode;
import io.github.spinel.utils.FileChooser;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImString;

public class RuntimeMenuGui extends GuiNode {

    ImString content = new ImString("");
    private FileChooser fc;
    private UI parentUI;
    private boolean isDarkTheme;
    private String file;

    public RuntimeMenuGui(UI parentUI) {
        super();
        this.parentUI = parentUI;
    }

    public RuntimeMenuGui(UI parentUI, String name, Boolean isDarkTheme) {
        super(name);
        this.parentUI = parentUI;
        this.isDarkTheme = isDarkTheme;
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
                if (ImGui.menuItem("Open") && parentUI.isReactive()) {
                    fc = new FileChooser("*.osm");
                    parentUI.toggleReactivity(); // block user interaction
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
                if (ImGui.menuItem(isDarkTheme ? "Light theme" : "Dark theme")) {
                    isDarkTheme = !isDarkTheme;
                    Engine.getInstance().getWindow().throwEvent(new ToggleThemeEvent());
                }
                ImGui.endMenu();
            }
            ImGui.endMainMenuBar();
        }
        if (fc != null && fc.isDone() && !parentUI.isReactive()) {
            // disable overlay (interaction allowed)
            parentUI.toggleReactivity();
            file = fc.getFilePath();
            if (file != null) {
                Engine.getInstance().getWindow().throwEvent(new FileLoadedEvent());
            }
        }
    }

    public String getSearchQuery() {
        return content.toString();
    }

    public String getFilePath() {
        return file;
    }

}
