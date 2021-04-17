import org.lwjgl.glfw.GLFW;

import exceptions.ExitOverrideException;
import ui.Window;
import ui.elements.Camera;
import ui.elements.Item;
import ui.gfx.Material;
import ui.gfx.Mesh;
import ui.gfx.Vertex;
import ui.gfx.Renderer;
import ui.gfx.Shader;
import ui.math.Vector2f;
import ui.math.Vector3f;


/**
 * Main app class
 */
public class App implements Runnable {

    public static final String PATH = "./src/main/java/resources/";

    private Thread main; // main thread
    private Window window;
    private Renderer renderer;
    private final int[] windowSize = {1920, 1080};

    // testing code ##########################

    private Mesh mesh = new Mesh(new Vertex[] {
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)), // texture coordinates must be defined counter clockwise
        new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(1.0f, 1.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0f, 0.0f, 1.0f), new Vector2f(0.0f, 1.0f))
    }, 
    
    new int[] {
        0, 1, 2,
        0, 3, 2
    },
    new Material(PATH + "textures/ropes.jpg"));

    private Item testElement = new Item(new Vector3f(0, 0, -1.0f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    private Shader testShader = new Shader(PATH + "shaders/Vertex.glsl", PATH + "shaders/Fragment.glsl");

    private Camera testCam = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
    
    // ###############################

    public void start() {
        main = new Thread(this, "main");
        main.start();
    }

    /**
     * App boot up sequence.
     */
    public void init() {
        System.out.println("Booting up application...");

        window = new Window(windowSize[0], windowSize[1], "7map test application");
        renderer = new Renderer(window, testShader);
        window.setBgColor(0.1f, 0.1f, 0.1f);
        window.create();

        // testing code goes here #################
        mesh.build();

        testShader.create();

        // schedule movement macros
        window.onKeyDown(GLFW.GLFW_KEY_A, () -> {
            testCam.setPos(testCam.getPos().getX() - 0.05f, testCam.getPos().getY(), testCam.getPos().getZ());
        });
        window.onKeyDown(GLFW.GLFW_KEY_D, () -> {
            testCam.setPos(testCam.getPos().getX() + 0.05f, testCam.getPos().getY(), testCam.getPos().getZ());
        });
        window.onKeyDown(GLFW.GLFW_KEY_W, () -> {
            testCam.setPos(testCam.getPos().getX(), testCam.getPos().getY(), testCam.getPos().getZ() - 0.05f);
        });
        window.onKeyDown(GLFW.GLFW_KEY_S, () -> {
            testCam.setPos(testCam.getPos().getX(), testCam.getPos().getY(), testCam.getPos().getZ() + 0.05f);
        });

        window.onKeyDown(GLFW.GLFW_KEY_E, () -> {
            testCam.setRot(testCam.getRot().getX(), testCam.getRot().getY(), testCam.getRot().getZ() + 1f);
        });
        window.onKeyDown(GLFW.GLFW_KEY_Q, () -> {
            testCam.setRot(testCam.getRot().getX(), testCam.getRot().getY(), testCam.getRot().getZ() - 1f);
        });
        window.onKeyDown(GLFW.GLFW_KEY_UP, () -> {
            testCam.setRot(testCam.getRot().getX() + 1f, testCam.getRot().getY(), testCam.getRot().getZ());
        });
        window.onKeyDown(GLFW.GLFW_KEY_DOWN, () -> {
            testCam.setRot(testCam.getRot().getX() - 1f, testCam.getRot().getY(), testCam.getRot().getZ());
        });
        window.onKeyDown(GLFW.GLFW_KEY_LEFT, () -> {
            testCam.setRot(testCam.getRot().getX(), testCam.getRot().getY() + 1f, testCam.getRot().getZ());
        });
        window.onKeyDown(GLFW.GLFW_KEY_RIGHT, () -> {
            testCam.setRot(testCam.getRot().getX(), testCam.getRot().getY() - 1f, testCam.getRot().getZ());
        });


        // ############################


        // schedule window closing when escape is pressed
        window.onKeyDown(GLFW.GLFW_KEY_ESCAPE, () -> {
            throw new ExitOverrideException(0);
        });

        // fullscreen
        window.onKeyDown(GLFW.GLFW_KEY_F11, () -> {
            window.setFullscreen(!window.isFullscreen());
        });

    }

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
        stop(); // absolutely necessary
    }

    private void update() {
        window.update();
    }

    private void render() {
        renderer.render(testElement, testCam);
        window.swap();
    }

    /**
     * Clean shutdown
     */
    private void stop() {
        window.destroy(); 
        main.interrupt();

        // testing code goes here #################

        testShader.destroy();
        testElement.destroy();

        // ######################
    }

    public static void main(String[] args) {
        new App().start();
    }

}
