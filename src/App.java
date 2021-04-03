import ui.Window;

public class App implements Runnable {

    private Thread app; // main thread
    private static Window window;
    private static final int WIDTH = 1600, HEIGHT = 900;

    public void start() {
        app = new Thread(this, "app");
        app.start();
    }

    public static void init() {
        System.out.println("Booting up application...");
        window = new Window(WIDTH, HEIGHT, "7map test");
    }

    /**
     * Implementation of the Interface-specific method Runnable.run
     */
    public void run() {
        init();
        while(!glfwWindowShouldClose(window)) {
            update();
            render();
        }
    }

    private void update() {
        // TODO : application back end updating routine
    }

    private void render() {
        // TODO : application rendering routine
    }

    public static void main(String[] args) {
        new App().start();
    }

}
