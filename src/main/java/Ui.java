public class Ui {
    public static void line() {
        line("=", 40);
    }

    public static void line(String text, int num){
        String str = "";
        for (int i = 0; i < num; i++) {
            str += text;
        }
        System.out.println(str);
    }

    public static void genericListMsg(){
        System.out.println("Status report. Here's your current run:\n");
    }

    public static void mark(TaskList tasklist, int index){
        Task task = tasklist.getTask(index);
        emptyLine();
        line();
        System.out.println("Done. One less loose thread:\n\n" + task.toString());
        line();
    }

    public static void unMark(TaskList tasklist, int index){
        Task task = tasklist.getTask(index);
        emptyLine();
        line();
        System.out.println("Status reverted, we're not done yet:\n\n" + task.toString());
        line();
    }

    public static void numOfTasks(TaskList tasklist) {
        System.out.println("You have " + tasklist.size() + " tasks left in the list");
    }

    public static void addedTaskMsg(){
        System.out.println("Objective recorded. We'll keep the timeline stable.\nAdded:\n");
    }

    public static void emptyLine() {
        System.out.println();
    }

    public static void greeting() {
        line(".", 35);
        emptyLine();
        System.out.println(
                        "██████╗ ██████╗  ██████╗ ████████╗\n" +
                        "██╔══██╗██╔══██╗██╔═══██╗╚══██╔══╝\n" +
                        "██████╔╝██████╔╝██║   ██║   ██║   \n" +
                        "██╔═══╝ ██╔══██╗██║   ██║   ██║   \n" +
                        "██║     ██║  ██║╚██████╔╝   ██║   \n" +
                        "╚═╝     ╚═╝  ╚═╝ ╚═════╝    ╚═╝   \n" +
                        "───── P R O T A G O N I S T ──────\n" +
                        "     your tasks, your story\n\n" +
                        "Status check. What's the objective?"
        );

        line(".", 35);
        emptyLine();
    }

    public static void goodbye() {
        emptyLine();
        line();
        System.out.println("Signing off. May our paths cross again.");
        line();
    }

    public static void deleteTaskMsg() {
        System.out.println("Gone. One less thing to track\nRemoved:");
    }

    public static void showAdd(Task task, int totalTasks) {
        emptyLine();
        line();
        addedTaskMsg();
        System.out.println(task);
        System.out.println("You have " + totalTasks + " tasks in the list");
        line();
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }

    public static void unknownCommand(String input){
        emptyLine();
        line();
        System.out.println("Unknown command: " + input +
                "\n\nPlease try using one of the following commands:" +
                "\ntodo <task>                     --> to log generic tasks to do" +
                "\ndeadline <task> /by <time/date> --> to log tasks with deadlines" +
                "\nevent <task> /from <start time/date> /to <end time/date> --> self-explanatory" +
                "\nlist                            --> to list all tasks" +
                "\nmark <task no.>                 --> to mark a task as done" +
                "\nunmark <task no.>               --> to mark a task as NOT done" +
                "\ndelete <task no.>               --> to delete a task" +
                "\nbye                             --> to exit the chat");
        line();
    }

    public static void showError(String msg) {
        emptyLine();
        line();
        System.out.println("❌ " + msg);
        line();
    }



}
