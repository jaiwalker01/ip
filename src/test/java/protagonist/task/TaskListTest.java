package protagonist.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void add_increasesSize() {
        TaskList tasks = new TaskList();

        tasks.add(new ToDo("read book"));
        assertEquals(1, tasks.size());

        tasks.add(new ToDo("drink water"));
        assertEquals(2, tasks.size());
    }

    @Test
    public void remove_decreasesSize() {
        TaskList tasks = new TaskList();

        tasks.add(new ToDo("A"));
        tasks.add(new ToDo("B"));
        assertEquals(2, tasks.size());

        tasks.removeTask(0);

        assertEquals(1, tasks.size());
    }
}
