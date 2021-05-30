package com.sevenmap.core;

import java.util.HashMap;

import com.sevenmap.core.Props.BUILD_TYPE;
import com.sevenmap.core.map.Map;
import com.sevenmap.core.ui.UI;
import com.sevenmap.core.ui.events.FileLoadedEvent;
import com.sevenmap.core.ui.events.ToggleThemeEvent;
import com.sevenmap.core.ui.events.ZoomEvent;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.math.Vector3f;
import com.sevenmap.spinel.utils.Color;

import org.lwjgl.glfw.GLFW;

public class Runtime {
    private final Float zoomCoef = 30f;
    private Engine engine;
    private UI gui;
    private Map map;
    private Props props;
    private HashMap<Integer, Runnable> keybinds = new HashMap<>();

    public void load(Props props) {
        this.props = props;

        engine = new Engine();
        gui = new UI(engine.getGuiRoot(), props);
        map = new Map(props);

        map.load();
        gui.load();
        gui.ldMapDisplay();
        armEvents();

        engine.getSceneRoot().tree();
        engine.getGuiRoot().tree();
        setup();
        engine.getCamera().setRot(new Vector3f(-90, 0, 0));
        engine.getCamera().setPos(new Vector3f(0, 10, 0));
        String backGroundColor = props.getStyles().findGuiStyle(props.getTheme()).getWindowbg();
        engine.getWindow().setBgColor(new Color(backGroundColor));
        engine.start();
    }

    private void setup() {

        // Déplacement
        keybinds.put(GLFW.GLFW_KEY_LEFT, () -> {
            Float zoom = engine.getCamera().getPos().getY() / zoomCoef;
            engine.getCamera()
                    .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceZ().divide(1 / zoom)));
        });
        keybinds.put(GLFW.GLFW_KEY_RIGHT, () -> {
            Float zoom = engine.getCamera().getPos().getY() / zoomCoef;
            engine.getCamera()
                    .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceZ().divide(1 / zoom)));
        });
        keybinds.put(GLFW.GLFW_KEY_UP, () -> {
            Float zoom = engine.getCamera().getPos().getY() / zoomCoef;
            engine.getCamera()
                    .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceY().divide(1 / zoom)));
        });
        keybinds.put(GLFW.GLFW_KEY_DOWN, () -> {
            Float zoom = engine.getCamera().getPos().getY() / zoomCoef;
            engine.getCamera()
                    .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceY().divide(1 / zoom)));
        });

        // Zoom
        keybinds.put(GLFW.GLFW_KEY_Q, () -> {
            Float zoom = engine.getCamera().getPos().getY() / zoomCoef;
            engine.getCamera()
                    .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceX().divide(1 / zoom)));
        });
        keybinds.put(GLFW.GLFW_KEY_W, () -> {
            Float zoom = engine.getCamera().getPos().getY() / zoomCoef;
            engine.getCamera()
                    .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceX().divide(1 / zoom)));
        });

        // stopping the engine
        keybinds.put(GLFW.GLFW_KEY_ESCAPE, () -> engine.stop());

        // apply key bindings
        for (java.util.Map.Entry<Integer, Runnable> entry : keybinds.entrySet()) {
            engine.getWindow().onKeyDown(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Arm all event triggers on startup.
     */
    private void armEvents() {
        engine.getWindow().onEvent(new FileLoadedEvent(), () -> {
            String fileName = gui.getRuntimeMenus().getFilePath();
            if (fileName != null) {
                // TODO: change it to @kingussopp logger
                System.out.printf("User chose file %s\n", fileName);
                gui.ldMapDisplay();
                // Setup fileName to props
                props.setMapFile(fileName);
                // Warning the map API that it will be necessary to reload map from file
                props.hasToBuild(BUILD_TYPE.FROM_FILE);
                // Reload map
                map.reload();
            }
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
        engine.getWindow().onEvent(new ToggleThemeEvent(), () -> {
            if (props.getTheme().equals("dark")) {
                props.setTheme("light");
            } else {
                props.setTheme("dark");
            }
            props.hasToBuild(BUILD_TYPE.FROM_FILE);
            String backGroundColor = props.getStyles().findGuiStyle(props.getTheme()).getWindowbg();
            engine.getWindow().setBgColor(new Color(backGroundColor));
            gui.load();
            map.reload();
        });
    }
}
