import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import exceptions.ExitOverrideException;
import ui.Task;
import ui.Window;

public class App implements Runnable {

    private Thread app; // main thread
    private Window window;
    private final int[] SIZE = {1600, 900};

    public void start() {
        app = new Thread(this, "app");
        app.start();
    }

    /**
     * App boot up sequence.
     */
    public void init() {
        System.out.println("Booting up application...");
        window = new Window(SIZE[0], SIZE[1], "7map test application");
        window.create();

        // schedule window closing when escape is pressed
        Task closeOnEsc = window.onKeyDown(GLFW.GLFW_KEY_ESCAPE, () -> {
            throw new ExitOverrideException(0);
        });
        // testing remove() method
        // closeOnEsc.remove(); 
    }

    /**
     * Implementation of the Interface-specific method Runnable.run
     */
    public void run() {
        init();
        try {
            while(!window.shouldClose()) {
            update();
            render();
            }
        } catch (ExitOverrideException e) {
            System.out.println(e.getMessage());
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
