package protagonist.task;

public class Task {

    private final String rawString;
    private int number;
    private boolean isDone = false;

    public Task(String rawString){
        this.rawString = rawString;
    }

    public void setNum(int k){
        this.number = k;
    }

    public int getNumber(){
        return this.number;
    }

    public String getDescription(){
        return this.rawString;
    }

    public void taskDone(){
        isDone = true;
    }

    public void taskNotDone(){
        isDone = false;
    }

    public boolean taskStatus(){
        return isDone;
    }

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
