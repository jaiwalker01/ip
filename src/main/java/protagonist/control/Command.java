package protagonist.control;

import protagonist.task.TaskList;
import protagonist.io.Ui;
import protagonist.exception.ProtagonistException;
import protagonist.task.Task;

public class Command {
    public static void hi() {
        Ui.greeting();
    }
    public static void bye() {
        Ui.goodbye();
    }
    public static void printList(TaskList tasklist) {
        Ui.emptyLine();
        Ui.line();
        Ui.genericListMsg();
        System.out.println(tasklist);
        Ui.line();
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
        Ui.line();
        Ui.deleteTaskMsg();
        Ui.printTask(task);
        Ui.numOfTasks(tasklist);
        Ui.emptyLine();
        Ui.line();
    }
}
