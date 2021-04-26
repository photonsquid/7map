package com.sevenmap;

import org.lwjgl.glfw.GLFW;

import com.sevenmap.exceptions.ExitOverrideException;
import com.sevenmap.ui.Engine;
import com.sevenmap.ui.elements.Item;
import com.sevenmap.ui.elements.Node;
import com.sevenmap.ui.gfx.Material;
import com.sevenmap.ui.gfx.Mesh;
import com.sevenmap.ui.gfx.Vertex;
import com.sevenmap.ui.math.Vector2f;
import com.sevenmap.ui.math.Vector3f;
import com.sevenmap.ui.scheduling.events.MoveEvent;


/**
 * Main application class.
 */
public class App {

    private Engine engine = new Engine();

    // testing only ---------------------------------
    private Mesh mesh = new Mesh(new Vertex[] {
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)), // texture coordinates must be defined counter clockwise
        new Vertex(new Vector3f(0.5f, 0.5f, 0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, 0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(1.0f, 1.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, 0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 0.0f)),
        new Vertex(new Vector3f(-0.5f, 0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(0.5f, 0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(1.0f, 1.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 0.0f))
    
    },
    
    new int[] {
        0, 1, 2,
        0, 3, 2,
        4, 5, 6,
        4, 7, 6
    },
    new Material("textures/logo.png"));

    private Item testElement = new Item(new Vector3f(0, 0, -1.0f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    public void start() {
        init();
    }

    private Node parentTestNode = new Node(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
    
    // testing only ---------------------------------
    
    /**
     * App boot up sequence.
     */
    public void init() {

        testElement.setParent(parentTestNode);
        parentTestNode.setParent(engine.getRoot());
        
        // schedule movement macros
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_A, () -> 
            engine.getCamera().setPos(engine.getCamera().getPos().getX() - 0.05f, engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ())
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_D, () -> 
            engine.getCamera().setPos(engine.getCamera().getPos().getX() + 0.05f, engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ())
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_W, () -> 
            engine.getCamera().setPos(engine.getCamera().getPos().getX(), engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ() - 0.05f)
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_S, () -> 
            engine.getCamera().setPos(engine.getCamera().getPos().getX(), engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ() + 0.05f)
        );

        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_E, () -> 
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() + 1f)
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_Q, () -> 
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() - 1f)
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_UP, () -> 
            engine.getCamera().setRot(engine.getCamera().getRot().getX() + 1f, engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ())
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_DOWN, () -> 
            engine.getCamera().setRot(engine.getCamera().getRot().getX() - 1f, engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ())
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_LEFT, () -> 
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY() + 1f, engine.getCamera().getRot().getZ())
        );
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_RIGHT, () -> 
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY() - 1f, engine.getCamera().getRot().getZ())
        );

        // schedule engine.getWindow() closing when escape is pressed
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_ESCAPE, () -> {
            throw new ExitOverrideException(0);
        });

        // fullscreen
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_F11, () -> 
            engine.getWindow().setFullscreen(!engine.getWindow().isFullscreen())
        );

        // event showcase
        engine.getWindow().onEvent(new MoveEvent(engine), () -> {
            parentTestNode.setRot(testElement.getRot().add(new Vector3f(0, 0, 0.3f)));
        });

        engine.start(); 
    }

    public static void main(String[] args) {
        new App().start();
    }

}
