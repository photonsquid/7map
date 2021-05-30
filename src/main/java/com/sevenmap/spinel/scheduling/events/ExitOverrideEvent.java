package com.sevenmap.spinel.scheduling.events;

public class ExitOverrideEvent extends Event {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitOverrideEvent;
    }

}
