package com.sevenmap;

import org.lwjgl.glfw.GLFW;

import com.sevenmap.exceptions.ExitOverrideException;
import com.sevenmap.ui.Engine;
import com.sevenmap.ui.elements.Item;
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

    private Mesh mesh = new Mesh(new Vertex[] {
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)), // texture coordinates must be defined counter clockwise
        new Vertex(new Vector3f(0.5f, 0.5f, 0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, 0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(1.0f, 1.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, 0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 0.0f)),
        new Vertex(new Vector3f(-0.5f, 0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(0.5f, 0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f))
    
    },
    
    new int[] {
        0, 1, 2,
        0, 3, 2,
        0, 1, 4,
        5, 1, 4,
        5, 4, 7,
        5, 6, 7,
        6, 7, 3,
        6, 2, 3,
        2, 5, 6,
        2, 5, 1,
        7, 3, 4,
        3, 4, 0
    },
    new Material("textures/logo.png"));

    private Item testElement = new Item(new Vector3f(0, 0, -1.0f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    public void start() {
        init();
    }

    /**
     * App boot up sequence.
     */
    public void init() {
        testElement.setParent(engine.getRoot());
        
        // schedule movement macros
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_A, () -> {
            engine.getCamera().setPos(engine.getCamera().getPos().getX() - 0.05f, engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ());
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_D, () -> {
            engine.getCamera().setPos(engine.getCamera().getPos().getX() + 0.05f, engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ());
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_W, () -> {
            engine.getCamera().setPos(engine.getCamera().getPos().getX(), engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ() - 0.05f);
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_S, () -> {
            engine.getCamera().setPos(engine.getCamera().getPos().getX(), engine.getCamera().getPos().getY(), engine.getCamera().getPos().getZ() + 0.05f);
        });

        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_E, () -> {
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() + 1f);
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_Q, () -> {
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() - 1f);
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_UP, () -> {
            engine.getCamera().setRot(engine.getCamera().getRot().getX() + 1f, engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ());
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_DOWN, () -> {
            engine.getCamera().setRot(engine.getCamera().getRot().getX() - 1f, engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ());
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_LEFT, () -> {
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY() + 1f, engine.getCamera().getRot().getZ());
        });
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_RIGHT, () -> {
            engine.getCamera().setRot(engine.getCamera().getRot().getX(), engine.getCamera().getRot().getY() - 1f, engine.getCamera().getRot().getZ());
        });

        // schedule engine.getWindow() closing when escape is pressed
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_ESCAPE, () -> {
            throw new ExitOverrideException(0);
        });

        // fullscreen
        engine.getWindow().onKeyDown(GLFW.GLFW_KEY_F11, () -> {
            engine.getWindow().setFullscreen(!engine.getWindow().isFullscreen());
        });

        // event showcase
        engine.getWindow().onEvent(new MoveEvent(engine), () -> {
            // System.out.println(String.format("Movement detected, Camera has id %s", engine.getCamera().getID()));
        });

        engine.start(); 
    }

    public static void main(String[] args) {
        new App().start();
    }

}
