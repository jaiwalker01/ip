import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    private List<Task> tasks;
    private static int taskNumber = 1;

    public Tasklist() {
        this.tasks = new ArrayList<>(100);
    }

    public int totalTasks() {
        return taskNumber;
    }

    public void add(Task task){
        tasks.add(task);
        task.num(taskNumber);
        taskNumber++;
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
            str +=  task.toString() + "\n";
        }

        return str;
    }
}
