package protagonist.io;

import protagonist.task.Task;
import protagonist.task.TaskList;

public class Ui {
    public static void line() {
        line("=", 50);
    }

    public static void line(String text, int num){
        System.out.println(String.valueOf(text).repeat(Math.max(0, num)));
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
                """
                        ██████╗ ██████╗  ██████╗ ████████╗
                        ██╔══██╗██╔══██╗██╔═══██╗╚══██╔══╝
                        ██████╔╝██████╔╝██║   ██║   ██║  \s
                        ██╔═══╝ ██╔══██╗██║   ██║   ██║  \s
                        ██║     ██║  ██║╚██████╔╝   ██║  \s
                        ╚═╝     ╚═╝  ╚═╝ ╚═════╝    ╚═╝  \s
                        ───── P R O T A G O N I S T ──────
                             your tasks, your story
                        
                        Status check. What's the objective?
                        
                        (type \\help to see available commands)
                        """
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
                """
                        Available commands:
                        
                        \\task              --> show all task commands
                        
                        list               --> list all tasks
                        mark <task no.>    --> mark a task as done
                        unmark <task no.>  --> mark a task as NOT done
                        delete <task no.>  --> delete a task
                        bye                --> exit the chat""");
        line();
    }

    public static void showTaskUsage(){
        emptyLine();
        line();
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
        line();
    }

    public static void showError(String msg) {
        emptyLine();
        line();
        System.out.println("❌ " + msg);
        line();
    }



}
