package ui;

import exceptions.InitException;
import org.lwjgl.glfw.GLFW;

public class Window {
    private int[] size = new int[2];
    private String title;
    private long window;

    public Window(int width, int height, String title) {
        this.size[0] = width;
        this.size[1] = height;
        this.title = title;

    }

    public void create() {
        if (!GLFW.glfwInit()) { // glfw not initialized
            throw new InitException("Illegal attempt to create Window class while GLFW hasn't been initialized yet");
        }

        window = GLFW.glfwCreateWindow(size[0], size[1], title, 0, 0); // actually create the window element

        if (window == 0) {
            throw new InitException("Window was not properly initialized");
        }

        
    }
}
