package com.sevenmap.ui;

import com.sevenmap.exceptions.InitError;
import com.sevenmap.ui.math.Vector3f;
import com.sevenmap.ui.scheduling.Task;
import com.sevenmap.ui.scheduling.TaskMgr;
import com.sevenmap.ui.scheduling.events.ButtonEvent;
import com.sevenmap.ui.scheduling.events.Event;
import com.sevenmap.ui.scheduling.events.KeyEvent;
import com.sevenmap.ui.utils.Color;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class Window extends FrameObject {
    
    // functional attributes
    private String title;
    private long windowElement;
    private Input input;
    private TaskMgr taskManager = new TaskMgr();

    private int[] size = new int[2];
    private int[] posX = new int[1];
    private int[] posY = new int[1];

    // graphical attributes
    private Color bgColor = new Color(0, 0, 0);
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
        // initialize input
        input = new Input();
        // initialize task manager
        taskManager.create();
    }

    // getters and setters

    public Input getInput() {
        return input;
    }
    public Color getBgColor() {
        return bgColor;
    }
    public void setBgColor(double r, double g, double b) {
        bgColor.set(r, g, b);
    }
    public void setBgColor(Color color) {
        bgColor.set(color);
    }
    public boolean isFullscreen() {
        return isFullscreen;
    }
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
    public long getWindowElement() {
        return windowElement;
    }
    public void setWindowElement(long windowElement) {
        this.windowElement = windowElement;
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

        if (windowElement == 0) {
            throw new InitError("Window was not properly initialized");
        }

        // center the window
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(windowElement, (videoMode.width() - size[0]) / 2, (videoMode.height() - size[1]) / 2);
        GLFW.glfwMakeContextCurrent(windowElement); // make the window the current context
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        // transparency
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
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
        Vector3f color = bgColor.toVector3f();
        GL11.glClearColor(color.getX(), color.getY(), color.getY(), 1.0f);
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
        return taskManager.add(new KeyEvent(this, key), action);
    }

    /**
     * Schedule Runnable action executed on mouse button press interrupt.
     * @param button key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task onButtonDown(int button, Runnable action) {
        return taskManager.add(new ButtonEvent(this, button), action);
    }

    /**
     * Schedule Runnable action executed on event activity.
     * @param event the triggering event
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task onEvent(Event event, Runnable action) {
        return taskManager.add(event, action);
    }

    public Task scheduleTask(Runnable action) {
        return taskManager.add(new Event(this), action);
    }
}
