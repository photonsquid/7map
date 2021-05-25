package com.sevenmap.spinel.scheduling.events;

import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.Window;

public abstract class Event {
    private static Integer lastID = 0;
    private boolean forcedActive = false;
    protected final String id;
    protected Window windowInstance;

    public Event(Window window) {
        id = String.format("E%s", lastID++);
        this.windowInstance = window;
    }

    public Event(Engine engine) {
        id = String.format("E%s", lastID++);
        this.windowInstance = engine.getWindow();
    }

    /**
     * By default, and empty Event is never active.
     * 
     * @return true if the event is currently active
     */
    public boolean isActive() {
        return false;
    }

    /**
     * Events can be flaged as active if thrown via the {@code Window.throwEvent}
     * method. This method returns true if the event is {@code forcedActive}.
     * 
     * @return forcedActive state
     */
    public boolean isForcedActive() {
        return forcedActive;
    }

    /**
     * Flag this event as {@code forcedActive} which means it will be executed on
     * the next frame call, regardless of what isActive() returns.
     * 
     * @param state the new boolean state for forcedActive (true in most use cases)
     */
    public void setForcedActive(boolean state) {
        forcedActive = state;
    }

    /**
     * Get the event's unique ID.
     * <p>
     * An event's ID matches the following format : {@code ^E\d+$}
     * </p>
     * 
     * @return the event's ID
     */
    public String getID() {
        return id;
    }

    @Override
    public abstract boolean equals(Object obj);
}
