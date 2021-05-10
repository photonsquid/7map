package com.sevenmap.ui.elements;

public class GuiItem extends GuiNode {

    public GuiItem() {
        name = this.getClass().getSimpleName();
    }

    public GuiItem(String name) {
        this.name = name;
    }

    @Override
    public void draw() {
        // TODO
    }

    public void action() {
        // TODO
    }

}
