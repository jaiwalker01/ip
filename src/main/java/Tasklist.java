import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    private List<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>(100);
    }

    public void add(Task task){
        tasks.add(task);
    }
    @Override
    public String toString() {
        String str = "List of tasks:\n";
        int k = 1;
        for (Task task: tasks) {
            str += k + ". " + task.toString() + "\n";
            k += 1;
        }
        return str;
    }
}
