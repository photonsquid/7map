package com.sevenmap.ui.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sevenmap.ui.FrameObject;
import com.sevenmap.ui.Input;
import com.sevenmap.ui.scheduling.events.Event;

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
    public Task add(Event event, Runnable action) {
        Event duplicate = findDuplicate(event);
        List<Task> eventTasks;
        if (duplicate == null) { // no duplicate event
            events.add(event);
            tasks.putIfAbsent(event, new ArrayList<>());
            eventTasks = tasks.get(event);
        } else { // duplicate event found
            eventTasks = tasks.get(duplicate);
        }
        Task taskAction = new Task(action, eventTasks);
        eventTasks.add(taskAction);
        return taskAction;
        
    }

    /**
     * Remove a specified task from the Task manager's records.
     * @param task the task which has to be removed
     * @return true if the record contained the specified task
     */
    public boolean remove(Task task) {
        for (Entry<Event,List<Task>> e : tasks.entrySet()) {
            if (e.getValue().remove(task)) {
                if (e.getValue().isEmpty()) tasks.remove(e.getKey());
                return true;
            }
        }
        return false;
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

    /**
     * Find a duplicate event.
     * <p>
     * This method assumes that the default equals() method from the 
     * Object class has been overriden in the Event subclass, as comparing
     * handles will always result in this method not finding any duplicates.
     * </p>
     * @param event the potential duplicate
     * @return the already existing event in the {@link #tasks} Map, returns
     * null if there is none
     */
    public Event findDuplicate(Event event) {
        for (Event e : tasks.keySet()) {
            if (e.equals(event)) return e;
        }
        return null;
    }

    /**
     * Get the Task manager's task Map.
     * @return task map
     */
    public Map<Event, List<Task>> getTasks() {
        return tasks;
    }
}
