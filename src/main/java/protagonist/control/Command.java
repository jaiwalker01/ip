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
    public static void hi() {
        Ui.greeting();
    }

    public static void bye() {
        Ui.goodbye();
    }

    /**
     * Prints the tasks in the task list
     * @param tasklist
     */
    public static void printList(TaskList tasklist) {
        Ui.emptyLine();
        Ui.printLine();
        Ui.printGenericListMessage();
        System.out.println(tasklist);
        Ui.printLine();
    }

    /**
     * Marks a task from task list
     * @param tasklist
     * @param maybeIndex index of task to be marked
     * @throws ProtagonistException
     */
    public static void mark(TaskList tasklist, String maybeIndex)
            throws ProtagonistException {
        int index;

        try {
            index = Integer.parseInt(maybeIndex) - 1;
        } catch (NumberFormatException e) {
            throw new ProtagonistException("Invalid index");
        }

        if (index < 0 || index >= tasklist.size()) {
            throw new ProtagonistException("Invalid index");
        }

        tasklist.doTask(index);
        Ui.mark(tasklist, index);
    }

    /**
     * Unmarks a task from task list
     * @param tasklist
     * @param maybeIndex index of task to be unmarked
     * @throws ProtagonistException
     */
    public static void unmark(TaskList tasklist, String maybeIndex)
            throws ProtagonistException {

        int index;
        try {
            index = Integer.parseInt(maybeIndex) - 1;
        } catch (NumberFormatException e) {
            throw new ProtagonistException("Invalid index");
        }

        if (index < 0 || index >= tasklist.size()) {
            throw new ProtagonistException("Invalid index");
        }

        tasklist.undoTask(index);
        Ui.unMark(tasklist, index);
    }

    /**
     * Deletes task with the given index number if possible.
     * If index number is invalid, error is thrown
     * @param tasklist
     * @param index index of task to be deleted
     * @throws ProtagonistException
     */
    public static void deleteTask(TaskList tasklist, String index)
            throws ProtagonistException {

        int i;
        try {
            i = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new ProtagonistException("Invalid index");
        }

        if (i < 0 || i >= tasklist.size()) {
            throw new ProtagonistException("Invalid index");
        }

        Task task = tasklist.getTask(i);
        tasklist.removeTask(i);
        Ui.emptyLine();
        Ui.printLine();
        Ui.deleteTaskMsg();
        Ui.printTask(task);
        Ui.numOfTasks(tasklist);
        Ui.printLine();
    }

    /**
     * Finds tasks in the list with matching keyword
     * @param keyword String input
     * @param taskList task list of user
     * @throws ProtagonistException
     */
    public static void findTasksInTaskList(String keyword, TaskList taskList)
            throws ProtagonistException {

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new ProtagonistException("Usage: find <keyword>");
        }

        TaskList filtered = taskList.findTaskByKeyword(keyword);

        if (filtered.size() == 0) {
            Ui.printCannotFindTasks(keyword);
            return;
        }

        Ui.printCanFindTasks(keyword, filtered);
    }
}
