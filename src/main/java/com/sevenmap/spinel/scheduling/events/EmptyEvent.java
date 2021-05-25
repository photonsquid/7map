package com.sevenmap.spinel.scheduling.events;

import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.Window;

public class EmptyEvent extends Event {

    /**
     * Create a new {@code EmptyEvent} which will always be active (usefull if you
     * want a task to always run).
     * 
     * @param window parent {@code Window}
     */
    public EmptyEvent(Window window) {
        super(window);
    }

    /**
     * Create a new {@code EmptyEvent} which will always be active (usefull if you
     * want a task to always run).
     * 
     * @param window parent {@code Engine}
     */
    public EmptyEvent(Engine engine) {
        super(engine);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
