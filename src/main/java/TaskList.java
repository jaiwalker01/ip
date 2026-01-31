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

    @Override
    public String toString() {
        String str = "";

        for (Task task: tasks) {
            str +=  task.getNumber() + "." + task.toString() + "\n";
        }

        return str;
    }
}
