package com.sevenmap.spinel.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sevenmap.spinel.FrameObject;
import com.sevenmap.spinel.scheduling.events.Event;

/**
 * A task manager utility to schedule tasks executed on certain events
 */
public class TaskMgr extends FrameObject {

    /**
     * A list of tasks which are executed on each frame if their respective Event is
     * flagged as active
     */
    private Map<Event, List<Task>> tasks = new HashMap<>();
    /**
     * A stack used to store awaiting internal tasks (in order to avoid Threading
     * conflicts)
     */
    private List<Runnable> stack = new ArrayList<Runnable>();
    /**
     * A list of events which acts like a hashmap for tasks (avoid iterating on all
     * tasks)
     */
    private List<Event> events;

    public TaskMgr() {
        this.events = new ArrayList<>();
    }

    public TaskMgr(List<Event> defaultEvents) {
        this.events = defaultEvents;
    }

    @Override
    public void create() {
        for (Event event : events) {
            tasks.put(event, new ArrayList<>());
        }
    }

    @Override
    public void update() {
        runTasks();
        runStack();
    }

    /**
     * Add an event to the [@code tasks] list.
     * 
     * @param event event type
     * @return associated {@code List<Task>} task ArrayList
     */
    private List<Task> add(Event event) {
        Event duplicate = findDuplicate(event);
        List<Task> eventTasks;
        if (duplicate == null) { // no duplicate event
            events.add(event);
            tasks.putIfAbsent(event, new ArrayList<>());
            eventTasks = tasks.get(event);
        } else { // duplicate event found
            eventTasks = tasks.get(duplicate);
            duplicate.setForcedActive(event.isForcedActive());
        }

        return eventTasks;
    }

    /**
     * Add a task to the {@code tasks} list.
     * 
     * @param event  event type
     * @param key    key code
     * @param action lambda runnable
     * @return generated {@code task} object
     */
    public Task add(Event event, Runnable action) {
        List<Task> eventTasks = add(event);
        Task taskAction = new Task(action, eventTasks);
        eventTasks.add(taskAction);
        return taskAction;
    }

    /**
     * Remove a specified task from the Task manager's records.
     * 
     * @param task the task which has to be removed
     * @return true if the record contained the specified task
     */
    public boolean remove(Task task) {
        for (Entry<Event, List<Task>> e : tasks.entrySet()) {
            if (e.getValue().remove(task)) {
                if (e.getValue().isEmpty())
                    tasks.remove(e.getKey());
                return true;
            }
        }
        return false;
    }

    /**
     * Flag and event as {@code forcedActive} during the next frame and add it if it
     * can't be found in the task records.
     * 
     * @param event event type
     */
    public void flagActive(Event event) {
        event.setForcedActive(true);
        add(event);
    }

    /**
     * Execute all active tasks.
     */
    private void runTasks() {
        for (Event event : events) {
            List<Task> eventTasks = tasks.get(event);
            if (event.isActive() || event.isForcedActive()) {
                eventTasks.forEach(Task::run);
                event.setForcedActive(false); // reset forcedActive flag
            }
        }
    }

    /**
     * Find a duplicate event.
     * <p>
     * This method assumes that the default equals() method from the Object class
     * has been overriden in the Event subclass, as comparing handles will always
     * result in this method not finding any duplicates.
     * </p>
     * 
     * @param event the potential duplicate
     * @return the already existing event in the {@link #tasks} Map, returns null if
     *         there is none
     */
    public Event findDuplicate(Event event) {
        for (Event e : tasks.keySet()) {
            if (e.equals(event))
                return e;
        }
        return null;
    }

    /**
     * Get the Task manager's task Map.
     * 
     * @return task map
     */
    public Map<Event, List<Task>> getTasks() {
        return tasks;
    }

    /**
     * Add Runnable awaiting task to stack.
     * 
     * @param awaiting the awaiting task, which will be executed on the next frame
     */
    public void stack(Runnable awaiting) {
        stack.add(awaiting);
    }

    public void runStack() {
        stack.forEach(Runnable::run);

        if (stack.size() != 0) {
            stack.clear();
        }
    }
}
