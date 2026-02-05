package protagonist.control;

import protagonist.task.TaskList;
import protagonist.io.Ui;
import protagonist.exception.ProtagonistException;
import protagonist.task.Task;

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

    public static void printList(TaskList tasklist) {
        Ui.emptyLine();
        Ui.printLine();
        Ui.printGenericListMessage();
        System.out.println(tasklist);
        Ui.printLine();
    }

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

    public static void delete(TaskList tasklist, String index)
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
