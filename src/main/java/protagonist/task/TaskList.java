package protagonist.task;

import java.util.ArrayList;
import java.util.List;

/**
 * List of {@link Task}
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        assertValidInsertTask(task);
        tasks.add(task);
    }

    public void removeTask(int index) {
        assertValidIndex(index);
        tasks.remove(index);
    }

    public Task getTask(int index) {
        assertValidIndex(index);
        return tasks.get(index);
    }

    public void doTask(int index) {
        assertValidIndex(index);
        tasks.get(index).markDone();
    }

    public void undoTask(int index) {
        assertValidIndex(index);
        tasks.get(index).markUndone();
    }

    private void assertValidIndex(int index) {
        assert index >= 0 && index < tasks.size()
                : "Index out of bounds: " + index + ", size of list is " + tasks.size();
    }

    private void assertValidInsertTask(Task task) {
        assert task != null : "Task to add must not be null";
    }

    /**
     * Creates a list of file-format tasks to be saved onto to .txt file
     * @return the String representation of the savable data
     */
    public String toSaveLines() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Create a task list of tasks starting with the keyword
     * @param keyword input keyword
     * @return filtered task list
     */
    public TaskList findTaskByKeyword(String keyword) {
        TaskList temporaryTaskList = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                temporaryTaskList.addTask(task);
            }
        }
        return temporaryTaskList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        return sb.toString();
    }

}
