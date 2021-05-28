package com.sevenmap.core;

import com.sevenmap.core.map.Map;
import com.sevenmap.core.ui.UI;
import com.sevenmap.core.ui.events.FileLoadedEvent;
import com.sevenmap.spinel.Engine;

import org.apache.commons.cli.CommandLine;

public class Runtime {
    private Engine engine;
    private UI gui;
    private Map map;

    public void load(CommandLine cl) {

        engine = new Engine();
        gui = new UI(engine.getGuiRoot());
        map = new Map();

        map.load(cl);
        gui.load(cl);
        gui.ldFileChooser();
        armEvents();

        engine.start();
    }

    /**
     * Arm all event triggers on startup.
     */
    private void armEvents() {
        engine.getWindow().onEvent(new FileLoadedEvent(engine), () -> {
            System.out.printf("User chose file %s", gui.getMapLoadingLayer().getFilename());
            gui.ldMapDisplay();
        });
    }
}
