package ui;

import exceptions.InitError;
import ui.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {

    public static enum eventType {
        KEY,
        BUTTON
    }
    
    private Map<eventType, Map<Integer, List<Task>>> tasks = new HashMap<>();
    private int[] size = new int[2];
    private String title;
    private long windowElement;
    private Input input;

    public Window(int width, int height, String title) {
        this.size[0] = width;
        this.size[1] = height;
        this.title = title;

    }

    public Input getInput() {
        return input;
    }
    
    public void create() {
        if (!GLFW.glfwInit()) { // glfw not initialized
            throw new InitError("Illegal attempt to create Window class while GLFW hasn't been initialized yet");
        }

        windowElement = GLFW.glfwCreateWindow(size[0], size[1], title, 0, 0); // actually create the window element
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
        // make the window the current context
        GLFW.glfwMakeContextCurrent(windowElement);
        // set up c
        GLFW.glfwSetKeyCallback(windowElement, input.getKeyCB());
        GLFW.glfwSetCursorPosCallback(windowElement, input.getCursorPosCB());
        GLFW.glfwSetMouseButtonCallback(windowElement, input.getMouseClickCB());

        // show the window element
        GLFW.glfwShowWindow(windowElement);

        GLFW.glfwSwapInterval(1);
    }

    /**
     * Update the {@code windowElement}. 
     * <p>
     * Should be called on each frame, as this method handles 
     * all the user inputs and logical procedures.
     */
    public void update() {
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
