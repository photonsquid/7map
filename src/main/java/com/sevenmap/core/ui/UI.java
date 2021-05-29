package com.sevenmap.core.ui;

import com.sevenmap.core.Loadable;
import com.sevenmap.core.Props;
import com.sevenmap.core.ui.nodes.FileChooserGui;
import com.sevenmap.core.ui.nodes.RuntimeMenuGui;
import com.sevenmap.spinel.elements.RootNode;
import com.sevenmap.spinel.elements.gui.GuiLayer;

public class UI extends Loadable {
    private FileChooserGui mapLoadingLayer;
    private RuntimeMenuGui runtimeMenus;
    private GuiLayer searchBar;

    private boolean isReactive = true;
    private Props props;

    /**
     * Create a new UI and initialize its components.
     * 
     * @param engine The target engine
     */
    public UI(RootNode root, Props props) {
        super(props);
        new Style();
        mapLoadingLayer = new FileChooserGui(this, "Map Loading");
        runtimeMenus = new RuntimeMenuGui("Runtime Menus");
        searchBar = new GuiLayer("Search bar");

        mapLoadingLayer.setParent(root);
        runtimeMenus.setParent(root);
        searchBar.setParent(root);

        mapLoadingLayer.hide();
        runtimeMenus.hide();
        searchBar.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
    }

    /**
     * Load file chooser UI.
     */
    public void ldFileChooser() {
        hideAll();
        mapLoadingLayer.show();
    }

    /**
     * Load map display UI.
     */
    public void ldMapDisplay() {
        mapLoadingLayer.hide();
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

    private void hideAll() {
        mapLoadingLayer.hide();
        runtimeMenus.hide();
        searchBar.hide();
    }

    public FileChooserGui getMapLoadingLayer() {
        return mapLoadingLayer;
    }

    public RuntimeMenuGui getRuntimeMenus() {
        return runtimeMenus;
    }

    public GuiLayer getSearchBar() {
        return searchBar;
    }
}
