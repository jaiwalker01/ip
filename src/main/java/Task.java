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
    public void taskDone(){
        isDone = true;
    }
    public void taskNotDone(){
        isDone = false;
    }
    public boolean taskStatus(){
        return isDone;
    }
    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        return "[" + str + "] " + name;
    }

}
