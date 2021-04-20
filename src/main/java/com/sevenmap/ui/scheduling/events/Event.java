package com.sevenmap.ui.scheduling.events;

import com.sevenmap.ui.Engine;
import com.sevenmap.ui.Window;

public class Event {
    private static Integer lastID = 0;
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
     * By default, and empty Event is always active.
     * @return true if the event is currently active
     */
    public boolean isActive() {
        return true;
    }
    /**
     * Get the event's unique ID.
     * <p>
     * An event's ID matches the following format : {@code ^E\d+$}
     * </p>
     * @return the event's ID
     */
    public String getID() {
        return id;
    }
    
}
