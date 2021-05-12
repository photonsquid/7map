package com.sevenmap.ui.elements;

import com.sevenmap.exceptions.IncorrectChildTypeError;

public class GuiNode extends Node {
    protected Runnable logic;
    
    /**
     * Create a new GuiNode object with a default name.
     */
    public GuiNode() {
        name = this.getClass().getSimpleName();
    }

    /**
     * Create a new GuiNode object with a chosen name.
     * @param name the given name
     */
    public GuiNode(String name) {
        this.name = name;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void compatibilityCheck(Node child) {
        if (!(child instanceof GuiNode)) {
            throw new IncorrectChildTypeError("GuiNode element can only receive children of types GuiNode or lower.");
        }
    }
}
