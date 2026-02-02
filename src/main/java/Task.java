public class Task {
    private final String name;
    private int number;
    private boolean isDone = false;

    public Task(String name){
        this.name = name;
    }

    public void setNum(int k){
        this.number = k;
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
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
        return "T | " + done + " | " + name;
    }

    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        return "[" + str + "] " + name;
    }

}
