package protagonist.io;

import protagonist.task.Task;
import protagonist.task.TaskList;

/**
 * Prints messages and error messages according to taskList or task inputs
 */
public class Ui {
    private static final int LINE_WIDTH = 45;
    private static final String LINE_CHAR = "-";

    /**
     * Prints a line of repeated characters
     */
    public static void printLine() {
        printLine(LINE_CHAR, LINE_WIDTH);
    }

    /**
     * Prints a line of repeated characters specified by input
     * @param text character to be repeated
     * @param num number of repetitions
     */
    public static void printLine(String text, int num) {
        System.out.println(String.valueOf(text).repeat(Math.max(0, num)));
    }

    public static void boxedPrint(Runnable body) {
        emptyLine();
        printLine();
        body.run();
        printLine();
    }

    public static void boxedPrint(String message) {
        boxedPrint(() -> System.out.println(message));
    }

    /**
     * Prints a message to indicate that the task list is printed
     */
    public static void printGenericListMessage() {
        System.out.println("Status report. Here's your current run:\n");
    }

    /**
     * Prints the 'marked task' message
     * @param tasklist input task list
     * @param index index number of task to be marked
     */
    public static void mark(TaskList tasklist, int index) {
        Task task = tasklist.getTask(index);
        boxedPrint(() -> System.out.println("Done. One less loose thread:\n\n" + task));
    }

    /**
     * Prints the 'unmarked task' message
     * @param tasklist input task list
     * @param index index number of task to be unmarked
     */
    public static void unmark(TaskList tasklist, int index) {
        Task task = tasklist.getTask(index);
        boxedPrint(() -> System.out.println("Status reverted, we're not done yet:\n\n" + task));
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
    public static void addedTaskMsg() {
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

        System.out.println(
//                          ".\n"
//                        + "         ____  ____   ___  _____  \n"
//                        + "         |  _  \\|  _  \\ /  _  \\|_   _| \n"
//                        + "         | |_)  |  |_)  | |  | |   |  |   \n"
//                        + "         |  __/|  _ <| |_| || |   \n"
//                        + "         |_|   |_| \\_\\\\___/ |_|   \n"
//                        + "-------------------------------------------\n"
//                        + "         P R O T A G O N I S T\n"
//                        + "         your tasks, your story\n"
//                        + "-------------------------------------------\n"
//                        + "\n"
//                        + "Status check. What's the objective?\n"
//                        + "\n"
//                        + "(type \\help to see available commands)\n"
                          "┌───────────────┐\n"
                        + "│       PROTAGONIST        │\n"
                        + "│   your tasks, your story  │\n"
                        + "└───────────────┘\n\n"
                        + "Status check. What's the objective?\n"
                        + "(type \\help to see available commands)"
        );



        emptyLine();
    }

    /**
     * Prints the goodbye message for Protagonist
     */
    public static void goodbye() {
        boxedPrint("Signing off. May our paths cross again.");
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
        boxedPrint(() -> {
            addedTaskMsg();
            System.out.println(task);
            System.out.println("You have " + totalTasks + " tasks in the list");
        });
    }

    /**
     * Prints the {@code toString()} format of task
     * @param task task to be printed
     */
    public static void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints list of tasks that match the input keyword
     * @param keyword input to search for task
     * @param tasklist task list of user
     */
    public static void printCanFindTasks(String keyword, TaskList tasklist) {
        boxedPrint(() -> {
            System.out.println("Target Acquired. Matching tasks: \n");
            System.out.println(tasklist);
        });
    }

    /**
     * Prints error when 0 tasks are found
     * @param keyword input to search for task
     */
    public static void printCannotFindTasks(String keyword) {
        boxedPrint("Objective not found. No tasks containing: " + keyword
                + "\nTry a different keyword.");
    }

    /**
     * Prints error for unknown command
     * @param input user input (unknown command)
     */
    public static void unknownCommand(String input) {
        boxedPrint(() -> System.out.println(
                "Unknown command: " + input + "\n\n"
                + "Please try using one of the following commands:\n\n"
                + "\\task     --> show all task commands\n"
                + "\\help     --> access available commands"
        ));
    }


    /**
     * Prints guide for command usage
     */
    public static void helpCommand() {
        boxedPrint(() -> System.out.println("""
            Available commands:

            \\task              --> show all task commands

            list               --> list all tasks
            find <keyword>     --> find task containing <keyword>
            mark <task no.>    --> mark a task as done
            unmark <task no.>  --> mark a task as NOT done
            delete <task no.>  --> delete a task
            bye                --> exit the chat
            """));
    }

    /**
     * Prints guide for task usage
     */
    public static void showTaskUsage() {
        boxedPrint(() -> System.out.println("""
            ToDo Task (generic task to do, no timing to follow)
            Usage --> todo <name of task>
    
            Deadline Task (task with a specific deadline)
            Usage --> deadline <name of task> /by <time>
    
            Event Task (task with a specific start and end)
            Usage --> event <name of task> /from <start time> /to <end time>
    
            <time> format:
            (following formats are parsed, others are kept as entered)
    
            YYYY-MM-DDThh:mm (e.g. 2026-01-19T14:20) --> 19 Jan 2026 2:20pm
            OR
            YYYY-MM-DD       (e.g. 2026-01-19)       --> 19 Jan 2026
            """));
    }


    /**
     * Prints an [ERROR] with the input message (usually an Error message)
     * @param msg error message to be printed
     */
    public static void showError(String msg) {
        boxedPrint("[ERROR] " + msg);
    }




}
