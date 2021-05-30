package com.sevenmap.spinel;

import com.sevenmap.exceptions.IllegalInstanceException;
import com.sevenmap.spinel.elements.geom.Camera;
import com.sevenmap.spinel.gfx.GuiRenderer;
import com.sevenmap.spinel.gfx.SceneRenderer;
import com.sevenmap.spinel.gfx.Shader;
import com.sevenmap.spinel.math.Vector3f;
import com.sevenmap.spinel.scheduling.events.ExitOverrideEvent;
import com.sevenmap.spinel.utils.Color;

public class Engine implements Runnable {
    private static Engine instance;

    private Thread main;
    private Window window;

    // default settings
    private int[] windowSize = { 1920, 1080 };
    private Color bgColor = new Color(0.1d, 0.1d, 0.1d);
    private String title = "default title";
    private Shader shader = new Shader("shaders/Vertex.glsl", "shaders/Fragment.glsl");
    private SceneRenderer sceneRoot = new SceneRenderer(shader);
    private GuiRenderer guiRoot;
    private Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0),
            (float) windowSize[0] / (float) windowSize[1]);

    // stats : update time duration, render time duration
    private long[] stats = new long[2];

    // Threading
    private boolean paused;

    /**
     * Create a new Engine object which can be started up using {@link #start()}
     */
    public Engine() {
        if (instance != null) {
            throw new IllegalInstanceException(this.getClass().getSimpleName());
        } else {
            instance = this;
        }
        main = new Thread(this, this.getClass().getSimpleName());
        window = new Window(windowSize[0], windowSize[1], title);
        guiRoot = new GuiRenderer(window);
        camera.setParent(sceneRoot);
        // initialize window background color
        window.setBgColor(bgColor);
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
        window.throwEvent(new ExitOverrideEvent());
    }

    public void run() {
        init();
        window.onEvent(new ExitOverrideEvent(), () -> {
            window.setShouldClose(true);
        });
        while (!window.shouldClose()) {
            if (paused) {
                try {
                    synchronized (main) {
                        main.wait();
                    }
                } catch (InterruptedException e) {
                    System.out.println("[Threading fatal error] Failed to pause main thread");
                    break;
                }
            }
            long time = System.nanoTime();
            update();
            stats[0] = System.nanoTime() - time;
            time = System.nanoTime();
            render();
            stats[1] = System.nanoTime() - time;
        }
        stopInternals();
    }

    private void init() {
        System.out.printf("[%s] Booting up application...%n", Engine.class.getSimpleName());

        // initialize essentials
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
     * Pause the engine's main thread. (mostly for internal use)
     * <p>
     * Use this carefully
     * <p/>
     */
    public void pause() {
        paused = true;
    }

    /**
     * Resume the engine's main thread. (mostly for internal use)
     * <p>
     * Use this carefully
     * <p/>
     */
    public void resume() {
        paused = false;
        main.notifyAll();
    }

    /**
     * Get the window associated to the engine instance.
     * 
     * @return {@link Window} object
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Get the camera associated to the engine instance.
     * 
     * @return camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Get the engine's speed stats.
     * 
     * @return stat array - this javadoc needs to be improved
     */
    public long[] getStats() {
        return stats;
    }

    /**
     * Get the scene renderer associated to the engine instance.
     * 
     * @return sceneRoot renderer
     */
    public SceneRenderer getSceneRoot() {
        return sceneRoot;
    }

    /**
     * Get the gui renderer associated to the engine instance.
     * 
     * @return guiRoot renderer
     */
    public GuiRenderer getGuiRoot() {
        return guiRoot;
    }

    public boolean isRunning() {
        return main.isAlive();
    }

    /**
     * Get the active Engine instance.
     * 
     * @return active engine instance
     */
    public static Engine getInstance() {
        return instance;
    }

}
