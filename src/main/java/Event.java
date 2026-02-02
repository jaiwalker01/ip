public class Event extends Task{
    String firstPart;
    String secondPart;
    String thirdPart;
    public Event(String name, String firstPart, String secondPart, String thirdPart) {
        super(name);
        this.firstPart = firstPart;
        this.secondPart = secondPart;
        this.thirdPart = thirdPart;
    }

    public String getDescription() {
        return firstPart;
    }

    public String getEventStart() {
        return secondPart;
    }

    public String getEventEnd() {
        return thirdPart;
    }

    @Override
    public String toFileFormat() {
        // T | <0/1> | <name>
        String done = this.taskStatus() ? "1" : "0";
        return "E | " + done + " | " + this.getDescription() + " | " + this.getEventStart()
                + " | " + this.getEventEnd();
    }

    @Override
    public String toString(){
        String str = (this.taskStatus()) ? "X" : " ";
        return "[E][" + str + "] " + firstPart + " (from: " + secondPart + " to: " + thirdPart + " )";
    }
}
