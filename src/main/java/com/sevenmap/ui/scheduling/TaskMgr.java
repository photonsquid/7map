package com.sevenmap.ui.scheduling;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.sevenmap.ui.FrameObject;
import com.sevenmap.ui.Input;

/**
 * A task manager utility to schedule tasks executed on certain events
 */
public class TaskMgr extends FrameObject {
    
    private Map<Event, List<Task>> tasks = new HashMap<>();
    private Input inputStream;
    private List<Event> events;

    public TaskMgr() {
        this.events = new ArrayList<>();
    }

    public TaskMgr(List<Event> defaultEvents) {
        this.events = defaultEvents;
    }

    public void create(Input inputStream) {
        this.inputStream = inputStream;
        for (Event event: events) {
            tasks.put(event, new ArrayList<>());
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
    public Task addTask(Event event, Runnable action) {
        events.add(event);
        tasks.putIfAbsent(event, new ArrayList<>());
        List<Task> eventTasks = tasks.get(event);
        Task taskAction = new Task(action, eventTasks);
        eventTasks.add(taskAction);
        return taskAction;
    }
    
    /**
     * Execute all active tasks.
     */
    private void runTasks() {
        for (Event event : events) {
            List<Task> eventTasks = tasks.get(event);
            if (event.isActive()) {
                eventTasks.forEach(Task::run);
            }
        }
    }
}
