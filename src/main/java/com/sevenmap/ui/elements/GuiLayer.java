package com.sevenmap.ui.elements;

import imgui.ImGui;

public class GuiLayer extends GuiNode {
    private Runnable logic;

    public GuiLayer() {
        name = this.getClass().getSimpleName();
    }

    public GuiLayer(String name) {
        this.name = name;
    }

    public void addLogic(Runnable logic) {
        this.logic = logic;
    }

    /**
     * Draw the Layer and its children.
     */
    @Override
    public void draw() {
        ImGui.begin("layer");
        compute();
        ImGui.end();
    }

    /**
     * Computer Layer logic and draw its children.
     * <p>
     * Override this function if you need to create your own Layer subclass (inheriting
     * from GuiLayer),
     * by default it iterates on the Layer's children and draws them
     * <p/>
     */
    public void compute() {
        if (logic != null) {
            logic.run();
        }
        shownChildren.forEach((Node node) -> ((GuiNode) node).draw()); // do stuff
    }

    @Override
    public void compatibilityCheck(Node child) {
        // TODO
    }
}
