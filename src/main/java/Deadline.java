public class Deadline extends Task{
    private String firstPart;
    private String secondPart;

    public Deadline(String name, String firstPart, String secondPart) {
        super(name);
        this.firstPart = firstPart;
        this.secondPart = secondPart;
    }

    public String getDescription() {
        return firstPart;
    }

    public String getDeadline() {
        return secondPart;
    }

    @Override
    public String toFileFormat() {
        // T | <0/1> | <name>
        String done = this.taskStatus() ? "1" : "0";
        return "D | " + done + " | " + this.getDescription() + " | " + this.getDeadline();
    }

    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        return "[D][" + str + "] " + firstPart + " (by: " + secondPart + ")";
    }



}
