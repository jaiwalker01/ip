public class Ui {
    public static void line() {
        line("=", 50);
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
                        "Status check. What's the objective?\n\n" +
                        "(type \\help to see available commands)\n"
        );

        emptyLine();
    }

    public static void goodbye() {
        emptyLine();
        line();
        System.out.println("Signing off. May our paths cross again.");
        line();
    }

    public static void deleteTaskMsg() {
        System.out.println("Gone. One less thing to track\nRemoved:\n");
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
        System.out.println("Unknown command: " + input + "\n\n" +
                "Please try using one of the following commands:\n\n" +
                "\\task     --> show all task commands\n" +
                "\\help     --> access available commands"
        );
        line();
    }

    public static void helpCommand(){
        emptyLine();
        line();
        System.out.println(
                "Available commands:\n\n" +
                "\\task              --> show all task commands\n\n" +
                "list               --> list all tasks\n" +
                "mark <task no.>    --> mark a task as done\n" +
                "unmark <task no.>  --> mark a task as NOT done\n" +
                "delete <task no.>  --> delete a task\n" +
                "bye                --> exit the chat");
        line();
    }

    public static void showTaskUsage(){
        emptyLine();
        line();
        System.out.println("ToDo Task (generic task to do, no timing to follow)\n" +
                "Usage --> todo <name of task> \n\n" +

                "Deadline Task (task with a specific deadline)\n" +
                "Usage --> deadline <name of task> /by <time>\n\n" +

                "Event Task (task with a specific start and end)\n" +
                "Usage --> event <name of task> /from <start time> /to <end time>\n\n" +

                "<time> format:\n" +
                "(following formats are parsed, others are kept as entered)\n\n" +
                "YYYY-MM-DDThh:mm (e.g. 2026-01-19T14:20) --> 19 Jan 2026 2:20pm\n" +
                "OR\n" +
                "YYYY-MM-DD       (e.g. 2026-01-19)       --> 19 Jan 2026"
                );
        line();
    }

    public static void showError(String msg) {
        emptyLine();
        line();
        System.out.println("❌ " + msg);
        line();
    }



}
