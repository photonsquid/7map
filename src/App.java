import org.lwjgl.glfw.GLFW;

import exceptions.ExitOverrideException;
import ui.Task;
import ui.Window;
import ui.gfx.Mesh;
import ui.gfx.Vertex;
import ui.gfx.Renderer;
import ui.math.Vector3f;

public class App implements Runnable {

    private Thread main; // main thread
    private Window window;
    private Renderer renderer;
    private final int[] SIZE = {1600, 900};

    public Mesh mesh = new Mesh(new Vertex[] {
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f)),
        new Vertex(new Vector3f(0.5f, 0.5f, 0.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, 0.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f))
    }, 
    new int[] {
        0, 1, 2,
        0, 3, 2
    });


    public void start() {
        main = new Thread(this, "main");
        main.start();
    }

    /**
     * App boot up sequence.
     */
    public void init() {
        System.out.println("Booting up application...");
        renderer = new Renderer();
        window = new Window(SIZE[0], SIZE[1], "7map test application");
        window.setBackgroundColor(0.1f, 0.1f, 0.1f);
        window.create();

        mesh.build();

        // schedule window closing when escape is pressed
        Task closeOnEsc = window.onKeyDown(GLFW.GLFW_KEY_ESCAPE, () -> {
            throw new ExitOverrideException(0);
        });

        Task fullscreen = window.onKeyDown(GLFW.GLFW_KEY_F11, () -> {
            window.setFullscreen(!window.isFullscreen());
        });

        // Task dummy = window.scheduleTask(() -> {
        //     System.out.println("Heyo");
        // });
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
        main.interrupt();
    }

    private void update() {
        window.update();
    }

    private void render() {
        renderer.render(mesh);
        window.swap();
    }

    public static void main(String[] args) {
        new App().start();
    }

}
