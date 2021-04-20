package com.sevenmap.ui.scheduling;

import com.sevenmap.ui.Engine;
import com.sevenmap.ui.Window;

public class KeyEvent extends Event {
    private int key;

    /**
     * Create a new Key event.
     * @param engine the window instance the event should be bound to
     * @param button the keycode
     */
    public KeyEvent(Window window, int key) {
        super(window);
        this.key = key;
    }

    /**
     * Create a new Key event.
     * @param engine the engine instance containing the window the event should be bound to
     * @param button the keycode
     */
    public KeyEvent(Engine engine, int key) {
        super(engine);
        this.key = key;
    }

    @Override
    public boolean isActive() {
        return windowInstance.getInput().isKeyDown(key);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + key;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyEvent other = (KeyEvent) obj;
        if (key != other.key)
            return false;
        return true;
    }

    
}
