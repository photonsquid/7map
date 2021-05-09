package com.sevenmap.ui.gfx;

import com.sevenmap.ui.Window;
import com.sevenmap.ui.elements.RootNode;

import org.lwjgl.glfw.GLFW;

import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class GuiRenderer extends RootNode {
    private final ImGuiImplGlfw imGuiGlfw;
    private final ImGuiImplGl3 imGuiGl3;
    private String glslVersion = "#version 460 core";
    private Window parentWindow;
    
    public GuiRenderer(Window window) {
        imGuiGlfw = new ImGuiImplGlfw();
        imGuiGl3 = new ImGuiImplGl3();
        parentWindow = window;
    }

    public void build() {
        ImGui.createContext();
        imGuiGlfw.init(parentWindow.getWindowElement(), true);
        imGuiGl3.init(glslVersion);
    }

    public void render() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        // temporary

        ImGui.begin("rknk");
        if (ImGui.button("button helloooo")) {
            System.out.println("boop");
        }
        ImGui.end();

        // temporary

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
    }
}
