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

    @Override
    public String toString(){
        String str = (this.taskStatus()) ? "X" : " ";
        return "[E][" + str + "] " + firstPart + " (from: " + secondPart + " to: " + thirdPart + " )";
    }
}
