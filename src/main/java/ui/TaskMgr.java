package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import ui.Input.eventType;

/**
 * A task manager utility to schedule tasks executed on certain events
 */
public class TaskMgr extends FrameObject {
    
    private Map<eventType, Map<Integer, List<Task>>> tasks = new HashMap<>();
    private Input inputStream;

    public void create(Input inputStream) {
        this.inputStream = inputStream;
        for (eventType event: eventType.values()) {
            tasks.put(event, new HashMap<>());
        }
    }

    @Override
    public void update() {
        runTasks();
    }

    /**
     * Add a task to the {@code tasks} list.
     * @param event event type
     * @param key key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task addTask(eventType event, int key, Runnable action) {
        Map<Integer, List<Task>> subTasks = tasks.get(event);
        if (!subTasks.containsKey(key)) { // TODO : Replace this with a call to "Map.computeIfAbsent()" 
            subTasks.put(key, new ArrayList<>());
        }
        Task taskAction = new Task(action, subTasks.get(key));
        subTasks.get(key).add(taskAction);
        return taskAction;
    }
    
    /**
     * Execute all active tasks.
     */
    private void runTasks() {
        for (eventType event : eventType.values()) {
            Map<Integer, List<Task>> subTasks = tasks.get(event);
            for (Map.Entry<Integer, List<Task>> entry: subTasks.entrySet()) {
                if (inputStream.isDown(event, entry.getKey())) {
                    entry.getValue().forEach((Task taskAction) -> taskAction.run());
                }
            }
        }
    }
}
