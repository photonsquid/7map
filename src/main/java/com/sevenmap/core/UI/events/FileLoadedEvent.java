package com.sevenmap.core.ui.events;

import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.scheduling.events.Event;

public class FileLoadedEvent extends Event {

    public FileLoadedEvent(Engine engine) {
        super(engine);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FileLoadedEvent;
    }
}
