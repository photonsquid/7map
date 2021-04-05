package ui;

import java.util.List;

public class Task implements Runnable {
    
    private Runnable action;
    private static int lastId = 0;
    private int id;
    private List<Task> parent;

    public Task(Runnable action, List<Task> parent) {
        lastId++;
        id = lastId;
        this.action = action;
        this.parent = parent;
    }

    public void run() {
        action.run();
    }

    public void remove() {
        parent.remove(this);
    }
}
