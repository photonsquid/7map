package com.sevenmap.spinel.elements.gui;

import com.sevenmap.exceptions.IncorrectChildTypeException;
import com.sevenmap.spinel.elements.Node;

import imgui.ImGui;
import imgui.ImGuiViewport;

public class GuiNode extends Node {
    protected Runnable logic;
    protected ImGuiViewport viewport;

    /**
     * Create a new GuiNode object with a chosen name.
     * 
     * @param name the given name
     */
    public GuiNode(String name) {
        this.name = name;
        viewport = ImGui.getMainViewport();
    }

    /**
     * Create a new GuiNode object with a default name.
     */
    public GuiNode() {
        this(GuiNode.class.getClass().getSimpleName());
    }

    /**
     * Draw the Node and its children. Empty by default, must be therefore overriden
     * in subclass definition.
     */
    public void draw() {
        compute();
    }

    public void addLogic(Runnable logic) {
        this.logic = logic;
    }

    /**
     * Computer Layer logic and draw its children.
     * <p>
     * Override this function if you need to create your own Layer subclass
     * (inheriting from GuiLayer), by default it iterates on the Layer's children
     * and draws them
     * <p/>
     */
    public void compute() {
        if (logic != null) {
            logic.run();
        }
        shownChildren.forEach((Node node) -> ((GuiNode) node).draw()); // do stuff
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compatibilityCheck(Node child) {
        if (!(child instanceof GuiNode)) {
            throw new IncorrectChildTypeException(
                    "GuiNode element can only receive children of types GuiNode or lower.");
        }
    }
}
