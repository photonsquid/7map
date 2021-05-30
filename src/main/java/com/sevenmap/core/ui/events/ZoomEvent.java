package com.sevenmap.core.ui.events;

import com.sevenmap.spinel.scheduling.events.Event;

public class ZoomEvent extends Event {
    private boolean in;

    /**
     * Create a new ZoomEvent which should be thrown when the user tries to zoom in
     * or out.
     * 
     * @param in true if the required action is zooming in, false if it is zooming
     *           out
     */
    public ZoomEvent(boolean in) {
        this.in = in;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ZoomEvent) && (this.in == ((ZoomEvent) obj).in);
    }

    /**
     * Get the zooming direction.
     * 
     * @return true if the required action is zooming in, false if it is zooming out
     */
    public boolean getDirection() {
        return in;
    }
}
