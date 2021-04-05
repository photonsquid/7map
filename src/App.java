import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import exceptions.ExitOverrideException;
import ui.Window;

public class App implements Runnable {

    private Thread app; // main thread
    private Window window;
    private final int[] SIZE = {1600, 900};

    public void start() {
        app = new Thread(this, "app");
        app.start();
    }

    public void init() {
        System.out.println("Booting up application...");
        window = new Window(SIZE[0], SIZE[1], "7map test application");
        window.create();

        // Runnable close = action(() -> {
        //     throw new ExitOverrideException("");
        // });
    }

    /**
     * Implementation of the Interface-specific method Runnable.run
     */
    public void run() {
        init();
        while(!window.shouldClose()) {
            update();
            render();

            // closing condition
            if (window.getInput().isKeyDown(GLFW.GLFW_KEY_ESCAPE)) break;
        }
        window.destroy(); // absolutely necessary
    }

    private void update() {
        window.update();
    }

    private void render() {
        window.swap();
    }

    public static void main(String[] args) {
        new App().start();
    }

}
