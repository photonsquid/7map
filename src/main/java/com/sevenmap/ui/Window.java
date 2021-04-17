package com.sevenmap.ui;

import com.sevenmap.exceptions.InitError;
import com.sevenmap.ui.math.Matrix4f;
import com.sevenmap.ui.math.Vector3f;
import com.sevenmap.ui.Input.eventType;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window extends FrameObject {

    
    
    // functional attributes
    private String title;
    private long windowElement;
    private Input input;
    private TaskMgr taskManager = new TaskMgr(); // unused
    private Matrix4f projector;

    private float fov = 70.0f; // projection matrix parameters
    private float[] nearfar = {0.15f, 10_000.0f};
    private int[] size = new int[2];
    private int[] posX = new int[1];
    private int[] posY = new int[1];

    // graphical attributes
    private Vector3f bgColor = new Vector3f(0, 0, 0);
    private GLFWWindowSizeCallback sizeCB;
    private boolean isResized;
    private boolean isFullscreen;

    /**
     * Create a new window element from scratch
     * @param width width of the window (px)
     * @param height height of the window (px)
     * @param title title (displayed on top)
     */
    public Window(int width, int height, String title) {
        this.size[0] = width;
        this.size[1] = height;
        this.title = title;
        projector = Matrix4f.project((float) width / (float) height, fov, nearfar[0], nearfar[1]);
    }

    // getters and setters

    public Input getInput() {return input;}
    public Vector3f getBgColor() {return bgColor;}
    public Matrix4f getProjector() {return projector;}
    public void setBgColor(float r, float g, float b) {bgColor.set(r, g, b);}
    public void setProjector(Matrix4f projector) {this.projector = projector;}

    public boolean isFullscreen() {return isFullscreen;}
    public void setFullscreen(boolean value) {
        isFullscreen = value;
        isResized = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(windowElement, posX, posY);
            GLFW.glfwSetWindowMonitor(windowElement, GLFW.glfwGetPrimaryMonitor(), 0, 0, size[0], size[1], 0);
        } else {
            GLFW.glfwSetWindowMonitor(windowElement, 0, posX[0], posY[0], size[0], size[1], 0);
        }
    }

    // other methods
    
    /**
     * Create the window and initialize its components
     */
    @Override
    public void create() {
        if (!GLFW.glfwInit()) { // glfw not initialized
            throw new InitError("Illegal attempt to create Window class while GLFW hasn't been initialized yet");
        }

        windowElement = GLFW.glfwCreateWindow(size[0], size[1], title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0); // actually create the window element
        input = new Input();

        if (windowElement == 0) {
            throw new InitError("Window was not properly initialized");
        }

        // initialize task manager
        taskManager.create(input);

        // center the window
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(windowElement, (videoMode.width() - size[0]) / 2, (videoMode.height() - size[1]) / 2);
        GLFW.glfwMakeContextCurrent(windowElement); // make the window the current context
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        // set up callbacks
        createCallBacks();

        // show the window element
        GLFW.glfwShowWindow(windowElement);

        GLFW.glfwSwapInterval(1);
    }

    /**
     * Set up all the defined CallBacks inside JWGL.
     */
    private void createCallBacks() {
        sizeCB = new GLFWWindowSizeCallback(){
            @Override
            public void invoke(long window, int width, int height) {
                size[0] = width;
                size[1] = height;
                isResized = true;
            }
        };
        GLFW.glfwSetKeyCallback(windowElement, input.getKeyCB());
        GLFW.glfwSetCursorPosCallback(windowElement, input.getCursorPosCB());
        GLFW.glfwSetMouseButtonCallback(windowElement, input.getMouseClickCB());
        GLFW.glfwSetWindowSizeCallback(windowElement, sizeCB);
        GLFW.glfwSetScrollCallback(windowElement, input.getMouseScrollCB());
    }

    /**
     * Update the {@code windowElement}. 
     * <p>
     * Should be called on each frame, as this method handles 
     * all the user inputs and graphical updating routines.
     * </p>
     */
    @Override
    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, size[0], size[1]); // update the viewport
            isResized = false;
        }
        GL11.glClearColor(bgColor.getX(), bgColor.getY(), bgColor.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GLFW.glfwPollEvents();

        taskManager.update();
    }

    /**
     * Swap buffers inside the window.
     */
    public void swap() {
        GLFW.glfwSwapBuffers(windowElement);
    }

    /**
     * @see {@code GLFW.glfwWindowShouldClose()}
     */
    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(windowElement);
    }

    /**
     * Destroy window element and its dependencies.
     */
    @Override
    public void destroy() {
        input.destroy();
        taskManager.destroy();
        sizeCB.free();
        GLFW.glfwDestroyWindow(windowElement);
        GLFW.glfwTerminate();
    }
    
    /**
     * Schedule Runnable action executed on key press interrupt.
     * @param key key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task onKeyDown(int key, Runnable action) {
        return taskManager.addTask(eventType.KEY, key, action);
    }

    /**
     * Schedule Runnable action executed on mouse button press interrupt.
     * @param key key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task onButtonDown(int key, Runnable action) {
        return taskManager.addTask(eventType.BUTTON, key, action);
    }

    public Task scheduleTask(Runnable action) {
        return taskManager.addTask(eventType.NONE, 0, action);
    }
}
