package com.sevenmap.core;

import com.sevenmap.core.Props.BUILD_TYPE;
import com.sevenmap.core.map.Map;
import com.sevenmap.core.ui.UI;
import com.sevenmap.core.ui.events.FileLoadedEvent;
import com.sevenmap.core.ui.events.ZoomEvent;
import com.sevenmap.spinel.Engine;

public class Runtime {
    private Engine engine;
    private UI gui;
    private Map map;
    private Props props;

    public void load(Props props) {
        this.props = props;

        engine = new Engine();
        gui = new UI(engine.getGuiRoot(), props);
        map = new Map(props);

        map.load();
        gui.load();
        gui.ldFileChooser();
        armEvents();

        engine.start();
    }

    /**
     * Arm all event triggers on startup.
     */
    private void armEvents() {
        engine.getWindow().onEvent(new FileLoadedEvent(), () -> {
            String fileName = gui.getMapLoadingLayer().getFilePath();

            // TODO: change it to @kingussopp logger
            System.out.printf("User chose file %s", fileName);
            gui.ldMapDisplay();

            // Setup fileName to props
            props.MapFile(fileName);
            // Warning the map API that it will be necessary to reload map from file
            props.hasToBuild(BUILD_TYPE.FROM_FILE);
            // Reload map
            map.load();
        });
        engine.getWindow().onEvent(new ZoomEvent(true), () -> {

            // TODO: change it to @kingussopp logger
            System.out.println("Zooming in");
            // change current state of the App
            props.zommIn();
            // update map
            map.update();
        });
        engine.getWindow().onEvent(new ZoomEvent(false), () -> {

            // TODO: change it to @kingussopp logger
            System.out.println("Zooming out");

            // change current state of the App
            props.zommOut();
            // update map
            map.update();
        });
    }
}
