package com.sevenmap.core.ui.events;

import io.github.spinel.Engine;
import io.github.spinel.scheduling.events.Event;

public class DbSearchEvent extends Event {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DbSearchEvent;
    }
}
