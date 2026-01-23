public class Task {
    private String name;
    private int number;
    private boolean done = false;

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
        done = true;
    }
    public void taskNotDone(){
        done = false;
    }
    public boolean taskStatus(){
        return done;
    }
    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        return "[" + str + "] " + name;
    }

}
