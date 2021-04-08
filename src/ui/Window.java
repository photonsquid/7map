package ui;

import exceptions.InitError;
import ui.math.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {

    public enum eventType {
        KEY,
        BUTTON
    }
    
    // functional attributes
    private Map<eventType, Map<Integer, List<Task>>> tasks = new HashMap<>();
    private int[] size = new int[2];
    private String title;
    private long windowElement;
    private Input input;
    private int[] posX = new int[1];
    private int[] posY = new int[1];

    // graphical attributes
    private Vector3f bgColor = new Vector3f(0, 0, 0);
    private GLFWWindowSizeCallback sizeCB;
    private boolean isResized;
    private boolean isFullscreen;

    public Window(int width, int height, String title) {
        this.size[0] = width;
        this.size[1] = height;
        this.title = title;

    }

    // getters and setters

    public Input getInput() {return input;}

    public Vector3f getBgColor() {return bgColor;}
    public void setBackgroundColor(float r, float g, float b) {
        bgColor.set(r, g, b);
    }

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
    public void create() {
        if (!GLFW.glfwInit()) { // glfw not initialized
            throw new InitError("Illegal attempt to create Window class while GLFW hasn't been initialized yet");
        }

        windowElement = GLFW.glfwCreateWindow(size[0], size[1], title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0); // actually create the window element
        input = new Input();

        if (windowElement == 0) {
            throw new InitError("Window was not properly initialized");
        }

        // setup task map
        tasks.put(eventType.KEY, new HashMap<>());
        tasks.put(eventType.BUTTON, new HashMap<>());

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
     */
    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, size[0], size[1]); // update the viewport
            isResized = false;
        }
        GL11.glClearColor(bgColor.getX(), bgColor.getY(), bgColor.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GLFW.glfwPollEvents();
        runTasks();
    }

    /**
     * Swap buffers inside the window.
     */
    public void swap() {
        GLFW.glfwSwapBuffers(windowElement);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(windowElement);
    }

    /**
     * Destroy window element and its dependencies.
     */
    public void destroy() {
        input.destroy();
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
        return addTask(eventType.KEY, key, action);
    }

    /**
     * Schedule Runnable action executed on mouse button press interrupt.
     * @param key key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task onButtonDown(int key, Runnable action) {
        return addTask(eventType.BUTTON, key, action);
    }

    /**
     * Add a task to the {@code tasks} list.
     * @param event event type
     * @param key key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    private Task addTask(eventType event, int key, Runnable action) {
        Map<Integer, List<Task>> subTasks = tasks.get(event);
        if (!subTasks.containsKey(key)) { // TODO : Replace this with a call to "Map.computeIfAbsent()" 
            subTasks.put(key, new ArrayList<>());
        }
        Task taskAction = new Task(action, subTasks.get(key));
        subTasks.get(key).add(taskAction);
        return taskAction;
    }

    /**
     * Execute all active tasks.
     */
    private void runTasks() {
        for (eventType event : eventType.values()) {
            Map<Integer, List<Task>> subTasks = tasks.get(event);
            for (Map.Entry<Integer, List<Task>> entry: subTasks.entrySet()) {
                if (input.isDown(event, entry.getKey())) {
                    entry.getValue().forEach((Task taskAction) -> taskAction.run());
                }
            }
        }
    }
}
