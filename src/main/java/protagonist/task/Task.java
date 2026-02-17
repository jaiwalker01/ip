package protagonist.task;

/**
 * Task to be completed
 * to be marked as done or undone
 */
public class Task {

    private final String rawString;
    private boolean isDone = false;

    private static final String DONE = "1";
    private static final String NOT_DONE = "0";
    String done = isDone ? DONE : NOT_DONE;


    public Task(String description) {
        assert description != null : "Task description must not be null";
        assert !description.isBlank() : "Task description must not be blank";

        this.rawString = description;
    }

    public String getDescription() {
        return this.rawString;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts task representation into a format to be stored in a .txt file
     * @return the String representation of the to-be-stored format
     */
    public String toFileFormat() {
        // T | <0/1> | <name>
        return "T | " + done + " | " + rawString;
    }

    @Override
    public String toString() {
        String str = (this.isDone()) ? "X" : " ";
        return "[" + str + "] " + rawString;
    }

}
