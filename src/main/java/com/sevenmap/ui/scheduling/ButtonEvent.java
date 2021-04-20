package com.sevenmap.ui.scheduling;

import com.sevenmap.ui.Engine;
import com.sevenmap.ui.Window;

public class ButtonEvent extends Event {
    private int button;

    /**
     * Create a new Button event.
     * @param window the window instance the event should be bound to
     * @param button the button keycode
     */
    public ButtonEvent(Window window, int button) {
        super(window);
        this.button = button;
    }

    /**
     * Create a new Button event.
     * @param engine the engine instance containing the window the event should be bound to
     * @param button the button keycode
     */
    public ButtonEvent(Engine engine, int button) {
        super(engine);
        this.button = button;
    }

    public int getButton() {
        return button;
    }

    @Override
    public boolean isActive() {
        return windowInstance.getInput().isButtonDown(button);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + button;
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
        ButtonEvent other = (ButtonEvent) obj;
        if (button != other.button)
            return false;
        return true;
    }

   
    

}
