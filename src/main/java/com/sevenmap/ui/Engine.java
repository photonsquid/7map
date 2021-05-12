package com.sevenmap.ui;

import com.sevenmap.exceptions.ExitOverrideException;
import com.sevenmap.ui.elements.Camera;
import com.sevenmap.ui.gfx.GuiRenderer;
import com.sevenmap.ui.gfx.SceneRenderer;
import com.sevenmap.ui.gfx.Shader;
import com.sevenmap.ui.math.Vector3f;
import com.sevenmap.ui.utils.Color;

public class Engine implements Runnable {
    private Thread main;
    private Window window;

    // default settings
    private int[] windowSize = {1920, 1080};
    private Color bgColor = new Color(0.1d, 0.1d, 0.1d);
    private String title = "default title";
    private Shader shader = new Shader("shaders/Vertex.glsl", "shaders/Fragment.glsl");
    private SceneRenderer sceneRoot = new SceneRenderer(shader);
    private GuiRenderer guiRoot;
    private Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), (float) windowSize[0] / (float) windowSize[1]);

    // stats : update time duration, render time duration
    private long[] stats = new long[2];
    /**
     * Create a new Engine object which can be started up using {@link #start()}
     */
    public Engine() {
        main = new Thread(this, this.getClass().getSimpleName());
        window = new Window(windowSize[0], windowSize[1], title);
        guiRoot = new GuiRenderer(window);
        camera.setParent(sceneRoot);
    }

    /**
     * Boot up the engine and initialize all of its components.
     */
    public void start() {
        main.start();
    }

    /**
     * Stop the engine.
     */
    public void stop() {
        throw new ExitOverrideException(0);
    }

    public void run() {
        init();
        try {
            while(!window.shouldClose()) {
                long time = System.nanoTime();
                update();
                stats[0] = System.nanoTime() - time;
                time = System.nanoTime();
                render();
                stats[1] = System.nanoTime() - time;
            }
        } catch (ExitOverrideException e) {
            System.out.println(e.getMessage());
        }
        stopInternals();
    }

    private void init() {
        System.out.printf("[%s] Booting up application...%n", Engine.class.getSimpleName());
        
        // initialize essentials
        window.setBgColor(bgColor);
        window.create();

        // build meshes
        sceneRoot.buildAll();

        // initialize ImGui
        guiRoot.build();

        // create shaders
        shader.create();
    }

    private void update() {
        window.update();
    }

    private void render() {
        sceneRoot.render(camera);
        guiRoot.render();
        window.swap();
    }

    /**
     * Clean shutdown
     */
    private void stopInternals() {
        main.interrupt();

        // Hulk smash
        window.destroy(); 
        shader.destroy();
        sceneRoot.destroy();
        guiRoot.destroy();
    }

    /**
     * Get the window associated to the engine instance.
     * @return {@link Window} object
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Get the camera associated to the engine instance.
     * @return camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Get the engine's speed stats.
     * @return stat array - this javadoc needs to be improved
     */
    public long[] getStats() {
        return stats;
    }

    /**
     * Get the scene renderer associated to the engine instance.
     * @return sceneRoot renderer
     */
    public SceneRenderer getSceneRoot() {
        return sceneRoot;
    }

    /**
     * Get the gui renderer associated to the engine instance.
     * @return guiRoot renderer
     */
    public GuiRenderer getGuiRoot() {
        return guiRoot;
    }
}
