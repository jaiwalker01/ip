package protagonist.io;

import protagonist.task.Task;
import protagonist.task.TaskList;

/**
 * Prints messages and error messages according to taskList or task inputs
 */
public class Ui {

    /**
     * Prints a line of repeated characters
     */
    public static void printLine() {
        printLine("=", 50);
    }

    /**
     * Prints a line of repeated characters specified by input
     * @param text character to be repeated
     * @param num number of repetitions
     */
    public static void printLine(String text, int num){
        System.out.println(String.valueOf(text).repeat(Math.max(0, num)));
    }

    /**
     * Prints a message to indicate that the task list is printed
     */
    public static void printGenericListMessage(){
        System.out.println("Status report. Here's your current run:\n");
    }

    /**
     * Prints the 'marked task' message
     * @param tasklist input task list
     * @param index index number of task to be marked
     */
    public static void mark(TaskList tasklist, int index){
        Task task = tasklist.getTask(index);
        emptyLine();
        printLine();
        System.out.println("Done. One less loose thread:\n\n" + task.toString());
        printLine();
    }

    /**
     * Prints the 'unmarked task' message
     * @param tasklist input task list
     * @param index index number of task to be unmarked
     */
    public static void unMark(TaskList tasklist, int index){
        Task task = tasklist.getTask(index);
        emptyLine();
        printLine();
        System.out.println("Status reverted, we're not done yet:\n\n" + task.toString());
        printLine();
    }

    /**
     * Prints number of tasks left
     * @param tasklist input task list
     */
    public static void numOfTasks(TaskList tasklist) {
        System.out.println("You have " + tasklist.size() + " tasks left in the list");
    }

    /**
     * Prints an 'added task' message
     */
    public static void addedTaskMsg(){
        System.out.println("Objective recorded. We'll keep the timeline stable.\nAdded:\n");
    }

    /**
     * Prints an empty line.
     * Equivalent to {@code System.out.println()}
     */
    public static void emptyLine() {
        System.out.println();
    }

    /**
     * Prints the greeting message for Protagonist
     */
    public static void greeting() {
        printLine(".", 44);
        System.out.println(
                          "          ____  ____   ___ _____  \n"
                        + "         |  _ \\|  _ \\ / _ \\_   _| \n"
                        + "         | |_) | |_) | | | || |   \n"
                        + "         |  __/|  _ <| |_| || |   \n"
                        + "         |_|   |_| \\_\\\\___/ |_|   \n"
                        + "-------------------------------------------\n"
                        + "         P R O T A G O N I S T\n"
                        + "         your tasks, your story\n"
                        + "-------------------------------------------\n"
                        + "\n"
                        + "Status check. What's the objective?\n"
                        + "\n"
                        + "(type \\help to see available commands)\n"
        );



        emptyLine();
    }

    /**
     * Prints the goodbye message for Protagonist
     */
    public static void goodbye() {
        emptyLine();
        printLine();
        System.out.println("Signing off. May our paths cross again.");
        printLine();
    }

    /**
     * Prints the 'delete task' message
     */
    public static void deleteTaskMsg() {
        System.out.println("Gone. One less thing to track\nRemoved:\n");
    }

    /**
     * Prints the 'added task' message.
     * Prints how many tasks are left in task list.
     * @param task the task that is added
     * @param totalTasks number of tasks left in task list
     */
    public static void showAdd(Task task, int totalTasks) {
        emptyLine();
        printLine();
        addedTaskMsg();
        System.out.println(task);
        System.out.println("You have " + totalTasks + " tasks in the list");
        printLine();
    }

    /**
     * Prints the {@code toString()} format of task
     * @param task task to be printed
     */
    public static void printTask(Task task) {
        System.out.println(task);
    }


    public static void printCanFindTasks(String keyword, TaskList tasklist) {
        emptyLine();
        printLine();
        System.out.println("Target Acquired. Matching tasks: \n");
        System.out.println(tasklist);
        printLine();
    }

    public static void printCannotFindTasks(String keyword) {
        emptyLine();
        printLine();
        System.out.println("Objective not found. No tasks containing: " + keyword +
                "\nTry a different keyword.");
        printLine();

    }

    /**
     * Prints error for unknown command
     * @param input user input (unknown command)
     */
    public static void unknownCommand(String input){
        emptyLine();
        printLine();
        System.out.println("Unknown command: " + input + "\n\n" +
                "Please try using one of the following commands:\n\n" +
                "\\task     --> show all task commands\n" +
                "\\help     --> access available commands"
        );
        printLine();
    }

    /**
     * Prints guide for command usage
     */
    public static void helpCommand(){
        emptyLine();
        printLine();
        System.out.println(
                """
                        Available commands:
                        
                        \\task              --> show all task commands
                        
                        list               --> list all tasks
                        find <keyword>     --> find task containing <keyword>
                        mark <task no.>    --> mark a task as done
                        unmark <task no.>  --> mark a task as NOT done
                        delete <task no.>  --> delete a task
                        bye                --> exit the chat""");
        printLine();
    }

    /**
     * Prints guide for task usage
     */
    public static void showTaskUsage(){
        emptyLine();
        printLine();
        System.out.println("""
                        protagonist.task.ToDo protagonist.task.Task (generic task to do, no timing to follow)
                        Usage --> todo <name of task>\s
                        
                        protagonist.task.Deadline protagonist.task.Task (task with a specific deadline)
                        Usage --> deadline <name of task> /by <time>
                        
                        protagonist.task.Event protagonist.task.Task (task with a specific start and end)
                        Usage --> event <name of task> /from <start time> /to <end time>
                        
                        <time> format:
                        (following formats are parsed, others are kept as entered)
                        
                        YYYY-MM-DDThh:mm (e.g. 2026-01-19T14:20) --> 19 Jan 2026 2:20pm
                        OR
                        YYYY-MM-DD       (e.g. 2026-01-19)       --> 19 Jan 2026"""
                );
        printLine();
    }

    /**
     * Prints an [ERROR] with the input message (usually an Error message)
     * @param msg error message to be printed
     */
    public static void showError(String msg) {
        emptyLine();
        printLine();
        System.out.println("[ERROR] " + msg);
        printLine();
    }



}
