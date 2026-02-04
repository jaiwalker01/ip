package protagonist.control;

import protagonist.exception.ProtagonistException;
import protagonist.exception.UnknownCommandException;
import protagonist.task.Deadline;
import protagonist.task.Event;
import protagonist.task.Task;
import protagonist.task.ToDo;
import protagonist.io.Ui;
import protagonist.task.TaskList;

/**
 * Parses user input and dispatches commands that operate on the {@link TaskList}.
 * Validates command format and throws a {@link ProtagonistException} for invalid inputs.
 */
public class Parser {

    public static boolean parse(String input, TaskList tasklist)
            throws ProtagonistException {

        String trimmedInput = input.trim();

        if (trimmedInput.isEmpty()) {
            throw new ProtagonistException("protagonist.control.Command cannot be empty.");
        }

        String[] parts = trimmedInput.split("\\s+");
        String commandString = parts[0];

        switch (commandString) {

            case "bye":
                Command.bye();
                return false;

            case "\\help":
                Ui.helpCommand();
                return true;

            case "\\task":
                Ui.showTaskUsage();
                return true;

            case "list":
                Command.printList(tasklist);
                return true;

            case "mark":
                requireExactArgs(parts, 2, "mark <task number>");
                Command.mark(tasklist, parts[1]);
                return true;

            case "unmark":
                requireExactArgs(parts, 2, "unmark <task number>");
                Command.unmark(tasklist, parts[1]);
                return true;

            case "delete":
                requireExactArgs(parts, 2, "delete <task number>");
                Command.delete(tasklist, parts[1]);
                return true;

            case "todo":
                if (parts.length < 2) {
                    throw new ProtagonistException("Usage: todo <description>");
                }

                String todoDesc = combine(parts, 1, parts.length - 1);
                Task todo = new ToDo(todoDesc);
                tasklist.add(todo);
                Ui.showAdd(todo, tasklist.size());
                return true;

            case "deadline":
                return parseDeadline(parts, input, tasklist);

            case "event":
                return parseEvent(parts, input, tasklist);

            default:
                throw new UnknownCommandException(input);
        }
    }

    private static boolean parseDeadline(String[] parts, String input, TaskList tasklist)
            throws ProtagonistException {

        int byIndex = find(parts, "/by");

        if (byIndex == -1 || byIndex == 1 || byIndex == parts.length - 1) {
            throw new ProtagonistException(
                    "Bad Structure. Use: deadline <task> /by <date/time>");
        }

        String desc = combine(parts, 1, byIndex - 1);
        String by = combine(parts, byIndex + 1, parts.length - 1);

        Task deadline = new Deadline(input, desc, by);
        tasklist.add(deadline);
        Ui.showAdd(deadline, tasklist.size());

        return true;
    }

    private static boolean parseEvent(String[] parts, String input, TaskList tasklist)
            throws ProtagonistException {

        int fromIndex = find(parts, "/from");
        int toIndex = find(parts, "/to");

        if (fromIndex == -1 || toIndex == -1 ||
                fromIndex == 1 ||
                fromIndex + 1 >= toIndex ||
                toIndex == parts.length - 1) {

            throw new ProtagonistException(
                    "Format mismatch.Try: event <task> /from <start> /to <end>");
        }

        String desc = combine(parts, 1, fromIndex - 1);
        String from = combine(parts, fromIndex + 1, toIndex - 1);
        String to = combine(parts, toIndex + 1, parts.length - 1);

        Task event = new Event(input, desc, from, to);
        tasklist.add(event);
        Ui.showAdd(event, tasklist.size());

        return true;
    }

    private static void requireExactArgs(String[] parts, int count, String usage)
            throws ProtagonistException {

        if (parts.length != count) {
            throw new ProtagonistException("Usage: " + usage);
        }
    }

    private static int find(String[] parts, String target) {
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

    private static String combine(String[] parts, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(parts[i]);
            if (i < end) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
