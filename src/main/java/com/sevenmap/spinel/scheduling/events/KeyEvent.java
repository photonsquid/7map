package com.sevenmap.spinel.scheduling.events;

public class KeyEvent extends Event {
    private int key;

    /**
     * Create a new Key event.
     * 
     * @param button the keycode
     */
    public KeyEvent(int key) {
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
