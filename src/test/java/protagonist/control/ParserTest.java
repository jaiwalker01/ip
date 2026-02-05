package protagonist.control;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import protagonist.exception.ProtagonistException;
import protagonist.task.TaskList;

public class ParserTest {

    @Test
    public void emptyInput_throws() {
        TaskList tasks = new TaskList();

        try {
            Parser.parse("   ", tasks);
            fail("Expected ProtagonistException for empty input");
        } catch (ProtagonistException e) {
            assertTrue(e.getMessage().toLowerCase().contains("cannot be empty"));
        }
    }

    @Test
    public void todo_addsTask() throws Exception {
        TaskList tasks = new TaskList();

        boolean keepRunning = Parser.parse("todo read a book", tasks);

        assertTrue(keepRunning);
        assertEquals(1, tasks.size());
    }

    @Test
    public void deadlineMissingBy_throws() {
        TaskList tasks = new TaskList();

        try {
            Parser.parse("deadline submit report", tasks);
            fail("Expected ProtagonistException when /by is missing");
        } catch (ProtagonistException e) {
            // ignore
        }
    }

    @Test
    public void deadlineValid_addsTask() throws Exception {
        TaskList tasks = new TaskList();

        boolean keepRunning = Parser.parse("deadline submit report /by tomorrow", tasks);

        assertTrue(keepRunning);
        assertEquals(1, tasks.size());
    }

    @Test
    public void eventMissingParts_throws() {
        TaskList tasks = new TaskList();

        try {
            Parser.parse("event project meeting /from 2pm", tasks);
            fail("Expected ProtagonistException when /to is missing");
        } catch (ProtagonistException e) {
            // ignore
        }
    }

    @Test
    public void eventValid_addsTask() throws Exception {
        TaskList tasks = new TaskList();

        boolean keepRunning = Parser.parse("event project meeting /from 2pm /to 4pm", tasks);

        assertTrue(keepRunning);
        assertEquals(1, tasks.size());
    }
}
