package com.sevenmap.core.ui.events;

import com.sevenmap.spinel.scheduling.events.Event;

public class FileLoadedEvent extends Event {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FileLoadedEvent;
    }
}
