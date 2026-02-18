package protagonist.control;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import protagonist.exception.ProtagonistException;
import protagonist.task.TaskList;
import protagonist.task.ToDo;

public class CommandTest {

    @Test
    public void mark_validIndex_success() throws ProtagonistException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("A"));

        // should not throw
        assertDoesNotThrow(() -> Command.mark(tasks, "1"));
    }

    @Test
    public void mark_nonNumericIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("A"));

        // should throw
        assertThrows(ProtagonistException.class, () -> Command.mark(tasks, "a"));
    }

    @Test
    public void mark_zeroIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("A"));

        assertThrows(ProtagonistException.class, () -> Command.mark(tasks, "0"));
    }

    @Test
    public void mark_negativeIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("A"));

        assertThrows(ProtagonistException.class, () -> Command.mark(tasks, "-1"));
    }

    @Test
    public void mark_indexTooLarge_throwsException() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("A"));

        assertThrows(ProtagonistException.class, () -> Command.mark(tasks, "5"));
    }
}
