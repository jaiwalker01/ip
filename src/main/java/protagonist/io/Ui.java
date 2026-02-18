package protagonist.io;

import protagonist.task.Task;
import protagonist.task.TaskList;

/**
 * Prints messages and error messages according to {@link TaskList} or task inputs.
 * This class is responsible for formatting and displaying output only.
 */
public class Ui {
    private static final int LINE_WIDTH = 45;
    private static final String LINE_CHAR = "-";

    private static final String LIST_HEADER = "Mission console online. Issue your command:\n";

    private static final String MARK_PREFIX = "Task cleared. Keep moving:\n\n";
    private static final String UNMARK_PREFIX = "Rollback confirmed. Objective remains active:\n\n";

    private static final String ADDED_HEADER = "Objective recorded. We'll keep the timeline stable.\nAdded:\n";
    private static final String DELETED_HEADER = "Erased. Objective removed:\n";

    private static final String FIND_FOUND_HEADER = "Target acquired. Matching tasks:\n";
    private static final String FIND_NOT_FOUND_MSG =
            "Objective not found. No tasks containing: %s\nTry a different keyword.";

    private static final String UNKNOWN_CMD_MSG =
            "Unrecognised directive: %s\n\n"
                    + "Try one of these:\n"
                    + "  \\help  -> view all commands\n"
                    + "  \\task  -> view task command formats";

    private static final String TASKS_LEFT_MSG = "You have %d tasks left in the list";
    private static final String TASKS_IN_LIST_MSG = "You have %d tasks in the list";

    private static final String GOODBYE_MSG = "Run terminated. Stay sharp.";

    private static final String HELP_TEXT = """
        Commands

        Help
          \\help              -> show all commands
          \\task              -> show task command formats

        Tasks
          list               -> list all tasks
          find <keyword>     -> find tasks containing <keyword>
          mark <task no.>    -> mark a task as done
          unmark <task no.>  -> mark a task as not done
          delete <task no.>  -> delete a task

        System
          bye                -> exit Protagonist
        """;

    private static final String TASK_USAGE_TEXT = """
        Task command formats

        todo <description>
          Adds a task with no date/time.

        deadline <description> /by <date|datetime>
          Example: deadline return book /by 2026-01-19
          Example: deadline submit report /by 2026-01-19T14:20

        event <description> /from <date|datetime> /to <date|datetime>
          Example: event team meeting /from 2026-01-19T14:00 /to 2026-01-19T15:00

        Accepted time formats
          YYYY-MM-DD         -> 19 Jan 2026
          YYYY-MM-DDThh:mm   -> 19 Jan 2026 2:20 PM
        """;
    /**
     * Prints a line of repeated characters
     */
    public static void printLine() {
        printLine(LINE_CHAR, LINE_WIDTH);
    }

    /**
     * Prints a line of repeated characters specified by input
     *
     * @param text character to be repeated
     * @param num number of repetitions
     */
    public static void printLine(String text, int num) {
        System.out.println(String.valueOf(text).repeat(Math.max(0, num)));
    }

    /**
     * Prints output wrapped in a simple box (blank line + top border + content + bottom border).
     *
     * @param body code that prints the boxed content
     */
    public static void boxedPrint(Runnable body) {
        emptyLine();
        printLine();
        body.run();
        printLine();
    }

    /**
     * Prints a single message wrapped in a simple box
     *
     * @param message message to print
     */
    public static void boxedPrint(String message) {
        boxedPrint(() -> System.out.println(message));
    }

    /**
     * Prints a task message (prefix + task) wrapped in a simple box
     *
     * @param prefix text shown before the task
     * @param task task to print
     */
    private static void boxedPrintTask(String prefix, Task task) {
        boxedPrint(() -> System.out.println(prefix + task));
    }

    /**
     * Prints a message to indicate that the task list is printed
     */
    public static void printGenericListMessage() {
        System.out.println(LIST_HEADER);
    }

    /**
     * Prints the 'marked task' message
     *
     * @param tasklist input task list
     * @param index index number of task to be marked
     */
    public static void mark(TaskList tasklist, int index) {
        Task task = tasklist.getTask(index);
        boxedPrintTask(MARK_PREFIX, task);
    }

    /**
     * Prints the 'unmarked task' message
     *
     * @param tasklist input task list
     * @param index index number of task to be unmarked
     */
    public static void unmark(TaskList tasklist, int index) {
        Task task = tasklist.getTask(index);
        boxedPrintTask(UNMARK_PREFIX, task);
    }

    /**
     * Prints number of tasks left
     *
     * @param tasklist task list
     */
    public static void numOfTasks(TaskList tasklist) {
        System.out.printf(TASKS_LEFT_MSG + "%n", tasklist.size());
    }

    /**
     * Prints an 'added task' message
     */
    public static void addedTaskMsg() {
        System.out.println(ADDED_HEADER);
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
        boxedPrint(GOODBYE_MSG);
    }

    /**
     * Prints the 'delete task' message
     */
    public static void deleteTaskMsg() {
        System.out.println(DELETED_HEADER);
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
            System.out.printf(TASKS_IN_LIST_MSG + "%n", totalTasks);
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
            System.out.println(FIND_FOUND_HEADER);
            System.out.println(tasklist);
        });
    }

    /**
     * Prints error when 0 tasks are found
     * @param keyword input to search for task
     */
    public static void printCannotFindTasks(String keyword) {
        boxedPrint(String.format(FIND_NOT_FOUND_MSG, keyword));
    }

    /**
     * Prints error for unknown command
     * @param input user input (unknown command)
     */
    public static void unknownCommand(String input) {
        boxedPrint(String.format(UNKNOWN_CMD_MSG, input));
    }

    /**
     * Prints guide for command usage
     */
    public static void helpCommand() {
        boxedPrint(() -> System.out.println(HELP_TEXT));
    }

    /**
     * Prints guide for task usage
     */
    public static void showTaskUsage() {
        boxedPrint(() -> System.out.println(TASK_USAGE_TEXT));
    }

    /**
     * Prints an [ERROR] with the input message (usually an Error message)
     * @param msg error message to be printed
     */
    public static void showError(String msg) {
        boxedPrint("[ERROR] " + msg);
    }




}
