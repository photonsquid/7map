package com.sevenmap.core;

import com.sevenmap.core.map.Map;
import com.sevenmap.core.ui.UI;
import com.sevenmap.core.ui.events.FileLoadedEvent;
import com.sevenmap.core.ui.events.ZoomEvent;
import com.sevenmap.spinel.Engine;

public class Runtime {
    private Engine engine;
    private UI gui;
    private Map map;

    public void load(Props props) {

        engine = new Engine();
        gui = new UI(engine.getGuiRoot());
        map = new Map();

        map.load(props);
        gui.load(props);
        gui.ldFileChooser();
        armEvents();

        engine.start();
    }

    /**
     * Arm all event triggers on startup.
     */
    private void armEvents() {
        engine.getWindow().onEvent(new FileLoadedEvent(), () -> {
            System.out.printf("User chose file %s", gui.getMapLoadingLayer().getFilename());
            gui.ldMapDisplay();
        });
        engine.getWindow().onEvent(new ZoomEvent(true), () -> {
            System.out.println("Zooming in");
        });
        engine.getWindow().onEvent(new ZoomEvent(false), () -> {
            System.out.println("Zooming out");
        });
    }
}
