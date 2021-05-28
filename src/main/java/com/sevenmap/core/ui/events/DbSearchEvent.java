package com.sevenmap.core.ui.events;

import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.scheduling.events.Event;

public class DbSearchEvent extends Event {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DbSearchEvent;
    }
}
