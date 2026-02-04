package protagonist.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task){
        tasks.add(task);
        task.setNum(tasks.size());
    }

    public void removeTask(int index){
        tasks.remove(index);
        for (int i = index; i < tasks.size(); i++){
            tasks.get(i).setNum(i + 1); // renumber from index onwards
        }
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public void doTask(int index){
        tasks.get(index).taskDone();
    }

    public void undoTask(int index){
        tasks.get(index).taskNotDone();
    }

    public String toSaveLines() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            if (!sb.isEmpty()) {
                sb.append("\n");
            }
            sb.append(task.getNumber())
                    .append(".")
                    .append(task);
        }

        return sb.toString();
    }

    public TaskList findByKeyword(String keyword) {
        TaskList temporaryTaskList = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                temporaryTaskList.add(task);
            }
        }
        return temporaryTaskList;
    }

}
