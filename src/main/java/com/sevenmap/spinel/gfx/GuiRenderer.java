package com.sevenmap.spinel.gfx;

import com.sevenmap.spinel.Window;
import com.sevenmap.spinel.elements.Node;
import com.sevenmap.spinel.elements.RootNode;
import com.sevenmap.spinel.elements.gui.GuiNode;

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

    /**
     * Create a new GuiRenderer object.
     */
    public GuiRenderer(Window window) {
        name = this.getClass().getSimpleName();
        imGuiGlfw = new ImGuiImplGlfw();
        imGuiGl3 = new ImGuiImplGl3();
        parentWindow = window;
        ImGui.createContext();
    }

    /**
     * Initialize renderer components.
     */
    public void build() {
        imGuiGlfw.init(parentWindow.getWindowElement(), true);
        imGuiGl3.init(glslVersion);
    }

    /**
     * Render all children.
     */
    public void render() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        // draw children
        shownChildren.forEach((Node node) -> ((GuiNode) node).draw());

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
