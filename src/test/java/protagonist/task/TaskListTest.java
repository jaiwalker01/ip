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

    @Test
    public void mark_noChangeInSize(){
        TaskList tasks = new TaskList();

        tasks.addTask(new ToDo("C"));
        tasks.addTask(new ToDo("D"));
        assertEquals(2, tasks.size());

        int initialSize = tasks.size();

        tasks.doTask(0);
        tasks.doTask(1);

        assertEquals(initialSize, tasks.size());
    }

    @Test
    public void unmark_noChangeInSize() {
        TaskList tasks = new TaskList();

        tasks.addTask(new ToDo("C"));
        tasks.addTask(new ToDo("D"));
        assertEquals(2, tasks.size());

        int initialSize = tasks.size();

        tasks.undoTask(0);
        tasks.undoTask(1);

        assertEquals(initialSize, tasks.size());
    }

    @Test
    public void find_findsAllTasksWithPrefix(){
        TaskList tasks = new TaskList();

        tasks.addTask(new ToDo("Homework 1"));
        tasks.addTask(new ToDo("Homework 2"));
        tasks.addTask(new ToDo(" Home work"));
        tasks.addTask(new ToDo("Ho me works"));
        tasks.addTask(new ToDo("Hoe Repairs"));
        tasks.addTask(new ToDo("Home Decor"));
        tasks.addTask(new ToDo("Home Repairs"));

        TaskList filtered = tasks.findTaskByKeyword("Home");

        assertEquals(5, filtered.size());
    }
}
