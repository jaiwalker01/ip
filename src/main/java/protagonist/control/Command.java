package protagonist.control;

import protagonist.exception.ProtagonistException;
import protagonist.io.Ui;
import protagonist.task.Task;
import protagonist.task.TaskList;

/**
 * Executes command instructions  {@link Parser}.
 * Validates indexing of {@link TaskList} and throws a {@link ProtagonistException} for invalid indexes.
 */
public class Command {
    private static final String USAGE_FIND = "Usage: find <keyword>";
    private static final String INVALID_INDEX_ERR = "Invalid index";

    public static void hi() {
        Ui.greeting();
    }

    public static void bye() {
        Ui.goodbye();
    }

    private static int parseAndValidateIndex(String maybeIndex, TaskList tasklist)
            throws ProtagonistException {
        int index;
        try {
            index = Integer.parseInt(maybeIndex) - 1; // user input is 1-based
        } catch (NumberFormatException e) {
            throw new ProtagonistException(INVALID_INDEX_ERR);
        }

        if (index < 0 || index >= tasklist.size()) {
            throw new ProtagonistException(INVALID_INDEX_ERR);
        }

        return index;
    }

    /**
     * Prints the tasks in the task list
     * @param tasklist task list
     */
    public static void printList(TaskList tasklist) {
        Ui.boxedPrint(() -> {
            Ui.printGenericListMessage();
            System.out.println(tasklist);
        });
    }


    /**
     * Marks a task from task list
     * @param tasklist task list
     * @param maybeIndex index of task to be marked
     */
    public static void mark(TaskList tasklist, String maybeIndex) throws ProtagonistException {
        int index = parseAndValidateIndex(maybeIndex, tasklist);
        tasklist.doTask(index);
        Ui.mark(tasklist, index);
    }

    /**
     * Unmarks a task from task list
     * @param tasklist task list
     * @param maybeIndex index of task to be unmarked
     * @throws ProtagonistException when task cannot be unmarked
     */
    public static void unmark(TaskList tasklist, String maybeIndex) throws ProtagonistException {
        int index = parseAndValidateIndex(maybeIndex, tasklist);
        tasklist.undoTask(index);
        Ui.unmark(tasklist, index);
    }

    /**
     * Deletes task with the given index number if possible.
     * If index number is invalid, error is thrown
     * @param taskList task list
     * @param maybeIndex index of task to be deleted
     * @throws ProtagonistException when task cannot be deleted
     */
    public static void deleteTask(TaskList taskList, String maybeIndex) throws ProtagonistException {
        int index = parseAndValidateIndex(maybeIndex, taskList);

        Task task = taskList.getTask(index);
        taskList.removeTask(index);

        Ui.boxedPrint(() -> {
            Ui.deleteTaskMsg();
            Ui.printTask(task);
            Ui.numOfTasks(taskList);
        });
    }


    /**
     * Finds tasks in the list with matching keyword
     * @param keyword String input
     * @param taskList task list of user
     */
    public static void findTasksInTaskList(String keyword, TaskList taskList)
            throws ProtagonistException {

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new ProtagonistException(USAGE_FIND);
        }

        TaskList filtered = taskList.findTaskByKeyword(keyword);

        if (filtered.size() == 0) {
            Ui.printCannotFindTasks(keyword);
        } else {
            Ui.printCanFindTasks(keyword, filtered);
        }

    }
}
