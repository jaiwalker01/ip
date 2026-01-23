public class Deadline extends Task{
    private String firstPart;
    private String secondPart;

    public Deadline(String name, String firstPart, String secondPart) {
        super(name);
        this.firstPart = firstPart;
        this.secondPart = secondPart;
    }

    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        return "[D][" + str + "] " + firstPart + " (by: " + secondPart + ")";
    }



}
