package com.sevenmap.spinel;

/**
 * An object whose update method is called on each frame
 */
public abstract class FrameObject {
    /**
     * Initialize all components inside the object.
     * <p>
     * Must necessarily be executed after calling the constructor
     * </p>
     */
    public void create() {
        
    }

    /**
     * Update the object.
     * <p>
     * Must be called on each frame while active
     * </p>
     */
    public void update() {

    }

    /**
     * Destroy the object and all of its components
     * properly.
     */
    public void destroy() {

    }
}
