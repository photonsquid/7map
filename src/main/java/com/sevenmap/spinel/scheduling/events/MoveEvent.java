package com.sevenmap.spinel.scheduling.events;

import org.lwjgl.glfw.GLFW;

public class MoveEvent extends Event {

    @Override
    public boolean isActive() {
        return windowInstance.getInput().isKeyDown(GLFW.GLFW_KEY_W)
                || windowInstance.getInput().isKeyDown(GLFW.GLFW_KEY_A)
                || windowInstance.getInput().isKeyDown(GLFW.GLFW_KEY_S)
                || windowInstance.getInput().isKeyDown(GLFW.GLFW_KEY_D);
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.parseInt(id.substring(1, id.length() - 1));
        return result;
    }

}
