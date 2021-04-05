package ui;

import exceptions.InitError;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
    private int[] size = new int[2];
    private String title;
    private long windowElement;
    public Input input;

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
        // GLFW.glfwWindowShouldClose(windowElement);
        GLFW.glfwDestroyWindow(windowElement);
        GLFW.glfwTerminate();
    }
}
