package com.sevenmap.ui.scheduling;

import java.util.List;

public class Task implements Runnable {
    
    private Runnable action;
    private List<Task> parentList;
    private static Integer lastId = 0;
    private String id;

    public Task(Runnable action, List<Task> parentList) {
        id = String.format("T%s", lastId++);
        this.action = action;
        this.parentList = parentList;
    }

    public void run() {
        action.run();
    }

    /**
     * Delete the {@link Task} object from the task tree.
     */
    public void remove() {
        parentList.remove(this);
    }

    /**
     * Get the task's unique ID.
     * <p>
     * A task's ID matches the following format : {@code ^T\d+$}
     * </p>
     * @return the task's ID
     */
    public String getID() {
        return id;
    }
}
