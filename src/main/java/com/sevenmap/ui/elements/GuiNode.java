package com.sevenmap.ui.elements;

/**
 * Unfinished, please use GuiLayer instead.
 */
public class GuiNode extends Node {
    
    public GuiNode() {
        name = this.getClass().getSimpleName();
    }

    public GuiNode(String name) {
        this.name = name;
    }

    /**
     * Draw the Node and its children. Empty by default, must be therefore overriden
     * in subclass definition.
     */
    public void draw() {
        // this method should contain your code
    }
}
