public class ToDo extends Task {
    public ToDo(String name){
        super(name);
    }
    @Override
    public String toFileFormat() {
        // T | <0/1> | <name>
        String done = this.taskStatus() ? "1" : "0";
        return "T | " + done + " | " + this.getName();
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
