package ui;

/**
 * An object whose update method is called on each frame
 */
public abstract class FrameObject {
    /**
     * Initialize all components inside the object.
     * <p>
     * Must necessarily be executed after calling the constructor
     */
    public void create() {
        
    }

    /**
     * Update the object.
     * <p>
     * Must be called on each frame while active
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
