package com.sevenmap.spinel.scheduling.events;

import com.sevenmap.spinel.Engine;

public class FileLoadedEvent extends Event {

    public FileLoadedEvent(Engine engine) {
        super(engine);
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
