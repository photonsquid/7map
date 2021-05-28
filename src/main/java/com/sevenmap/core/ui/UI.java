package com.sevenmap.core.ui;

import com.sevenmap.core.Loadable;
import com.sevenmap.core.ui.nodes.FileChooserGui;
import com.sevenmap.spinel.elements.RootNode;
import com.sevenmap.spinel.elements.gui.GuiLayer;

import org.apache.commons.cli.CommandLine;

public class UI extends Loadable {
    private FileChooserGui mapLoadingLayer;
    private RuntimeMenuGui runtimeMenus;
    private GuiLayer searchBar;

    private boolean isReactive = true;
    private CommandLine clInput;

    /**
     * Create a new UI and initialize its components.
     * 
     * @param engine The target engine
     */
    public UI(RootNode root) {
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
    public void load(CommandLine cl) {
        clInput = cl;
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
