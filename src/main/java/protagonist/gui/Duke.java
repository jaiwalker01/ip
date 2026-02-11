package protagonist.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import protagonist.control.Command;
import protagonist.control.Parser;
import protagonist.exception.ProtagonistException;
import protagonist.exception.UnknownCommandException;
import protagonist.io.Storage;
import protagonist.io.Ui;
import protagonist.task.TaskList;

public class Duke {
    private final TaskList taskList;
    private boolean isRunning = true;

    public Duke() {
        TaskList loaded;

        try {
            loaded = Storage.load();
        } catch (ProtagonistException e) {
            loaded = new TaskList();
        }
        this.taskList = loaded;
    }

    /**
     * Call once when GUI starts for the greeting bubble
     * @return greeting message
     */
    public String getWelcomeResponse() {
        return captureOutput(() -> Command.hi());
    }

    /**
     * One user input -> one parse step -> returns whatever the Ui/Command printed.
     * @param input input command/instruction
     * @return output message
     */
    public String getResponse(String input) {
        if (!isRunning) {
            return "";
        }

        String out = captureOutput(() -> {
            try {
                isRunning = Parser.parse(input, taskList);
                Storage.saveToFile(taskList);
            } catch (UnknownCommandException e) {
                Ui.unknownCommand(e.getMessage());
            } catch (ProtagonistException e) {
                Ui.showError(e.getMessage());
            }
        });

        return out.isBlank() ? "(no output)" : out;
    }

    public boolean shouldExit() {
        return !isRunning;
    }

    private String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        try {
            System.setOut(ps);
            System.setErr(ps);
            action.run();
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
            ps.flush();
        }

        return baos.toString().trim();
    }
}
