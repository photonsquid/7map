package com.sevenmap.core.ui;

import com.sevenmap.core.Loadable;
import com.sevenmap.core.Props;
import com.sevenmap.core.ui.nodes.RuntimeMenuGui;
import io.github.spinel.elements.RootNode;
import io.github.spinel.elements.gui.GuiLayer;

public class UI extends Loadable {
    private RuntimeMenuGui runtimeMenus;
    private GuiLayer searchBar;

    private boolean isReactive = true;

    /**
     * Create a new UI and initialize its components.
     * 
     * @param engine The target engine
     */
    public UI(RootNode root, Props props) {
        super(props);
        runtimeMenus = new RuntimeMenuGui(this, "Runtime Menus", props.getTheme().equals("dark"));
        searchBar = new GuiLayer("Search bar");

        runtimeMenus.setParent(root);
        searchBar.setParent(root);

        runtimeMenus.hide();
        searchBar.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        new Style(props);
    }

    /**
     * Load map display UI.
     */
    public void ldMapDisplay() {
        runtimeMenus.show();
    }

    /**
     * Switch between non-reactive and reactive mode.
     * <p>
     * Toggles user input reactivity capabilities by setting up a layer on top of
     * the elements already on screen
     * <p/>
     */
    public void toggleReactivity() {
        isReactive = !isReactive;
    }

    /**
     * Returns true if the window reacts to user input.
     */
    public boolean isReactive() {
        return isReactive;
    }

    public RuntimeMenuGui getRuntimeMenus() {
        return runtimeMenus;
    }

    public GuiLayer getSearchBar() {
        return searchBar;
    }
}
