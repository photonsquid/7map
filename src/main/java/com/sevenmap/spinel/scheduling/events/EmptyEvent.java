package com.sevenmap.spinel.scheduling.events;

/**
 * an {@code Event} which will always be active (usefull if you want a task to
 * always run).
 */
public class EmptyEvent extends Event {

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
