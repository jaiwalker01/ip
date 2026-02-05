package protagonist.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void add_increasesSize() {
        TaskList tasks = new TaskList();

        tasks.addTask(new ToDo("read book"));
        assertEquals(1, tasks.size());

        tasks.addTask(new ToDo("drink water"));
        assertEquals(2, tasks.size());
    }

    @Test
    public void remove_decreasesSize() {
        TaskList tasks = new TaskList();

        tasks.addTask(new ToDo("A"));
        tasks.addTask(new ToDo("B"));
        assertEquals(2, tasks.size());

        tasks.removeTask(0);

        assertEquals(1, tasks.size());
    }
}
