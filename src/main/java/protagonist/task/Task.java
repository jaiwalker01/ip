package protagonist.task;

/**
 * Task to be completed
 * to be marked as done or undone
 */
public class Task {

    private final String rawString;
    private boolean isDone = false;

    public Task(String rawString) {
        this.rawString = rawString;
    }

    public String getDescription() {
        return this.rawString;
    }

    public void taskDone() {
        isDone = true;
    }

    public void taskNotDone() {
        isDone = false;
    }

    public boolean taskStatus() {
        return isDone;
    }

    /**
     * Converts task representation into a format to be stored in a .txt file
     * @return the String representation of the to-be-stored format
     */
    public String toFileFormat() {
        // T | <0/1> | <name>
        String done = isDone ? "1" : "0";
        return "T | " + done + " | " + rawString;
    }

    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        return "[" + str + "] " + rawString;
    }

}
