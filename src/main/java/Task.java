public class Task {
    private String name;
    private int number;
    private boolean done = false;

    public Task(String name){
        this.name = name;
    }
    public void num(int k){
        this.number = k;
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
        return number + ".[" + str + "] " + name;
    }

    public void printTask(){
        String str = (this.taskStatus()) ? "X" : " ";
        str = "[" + str + "] " + name;
        System.out.println(str);
    }
}
