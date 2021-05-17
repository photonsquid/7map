package com.sevenmap.spinel;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;


public class Input {
    
    private GLFWKeyCallback keyCB;
    private GLFWCursorPosCallback cursorPosCB;
    private GLFWMouseButtonCallback mouseClickCB;
    private GLFWScrollCallback mouseScrollCB;

    private boolean[] keyStates = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] mouseBStates = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private double[] mousePos = new double[2];
    private double[] scrollOffset = {0.0d, 0.0d};

    public Input() {
        keyCB = new GLFWKeyCallback(){ // invoke will be executed on any kb event
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keyStates[key] = (action != GLFW.GLFW_RELEASE);
            }
        };

        cursorPosCB = new GLFWCursorPosCallback(){
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mousePos[0] = xpos;
                mousePos[1] = ypos;
            }
        };

        mouseClickCB = new GLFWMouseButtonCallback(){
            @Override
            public void invoke(long window, int button, int action, int mods) {
                mouseBStates[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseScrollCB = new GLFWScrollCallback(){
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollOffset[0] += xoffset;
                scrollOffset[1] += yoffset;
            }
        };
    }

    // getters and setters 
    public double getMouseX() {
        return mousePos[0];
    }

    public double getMouseY() {
        return mousePos[1];
    }

    public double getScrollX() {
        return scrollOffset[0];
    }

    public double getScrollY() {
        return scrollOffset[1];
    }

    public double[] getMousePos() {
        return mousePos;
    }
    
    public GLFWKeyCallback getKeyCB() {
        return keyCB;
    }

    public GLFWCursorPosCallback getCursorPosCB() {
        return cursorPosCB;
    }

    public GLFWMouseButtonCallback getMouseClickCB() {
        return mouseClickCB;
    }

    public GLFWScrollCallback getMouseScrollCB() {
        return mouseScrollCB;
    }

    // other methods

    /**
     * Free all callbacks, thus destroying the input object.
     */
    public void destroy() {
        keyCB.free();
        cursorPosCB.free();
        mouseClickCB.free();
        mouseScrollCB.free();
    }

    /**
     * Determine if the provided key is pressed (using standard GLFW predefined keys recommended).
     * @param key key code 
     * @return true if the key is down
     */
    public boolean isKeyDown(int key) {
        return keyStates[key];
    }

    /**
     * Determine if the provided mouse button is pressed (using standard GLFW predefined buttons recommended).
     * @param button button code 
     * @return true if the button is down
     */
    public boolean isButtonDown(int button) {
        return mouseBStates[button];
    }

}
