package protagonist.io;

import protagonist.exception.ProtagonistException;
import protagonist.task.Deadline;
import protagonist.task.Event;
import protagonist.task.Task;
import protagonist.task.ToDo;
import protagonist.task.TaskList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * Local storage of the task list
 * Loads from storage to terminal
 * Stores to storage from terminal
 *
 */
public class Storage {

    private static final String DIR_NAME = "data";
    private static final String FILE_PATH = DIR_NAME + "/protagonist.txt";

    /**
     * Loads tasks from disk
     * Returns empty protagonist.task.TaskList is the file doesn't exist
     */
    public static TaskList load() throws ProtagonistException {
        TaskList taskList = new TaskList();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return taskList;
        }

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    Task task = parseLine(line);
                    taskList.addTask(task);
                } catch (ProtagonistException e) {
                    // ignore
                }
            }
        } catch (IOException e) {
            throw new ProtagonistException("Disruption in the system, failed to load data: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves all tasks to the disk by overwriting the file
     */
    public static void saveToFile(TaskList taskList) throws ProtagonistException {
        File dir = new File(DIR_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write(taskList.toSaveLines());
        } catch (IOException e) {
            throw new ProtagonistException("Disruption in the system, failed to write data: " + e.getMessage());
        }
    }

    /**
     * Takes an input in the format of the taskList that is stored in the storage file.
     * Return the task created by the input.
     *
     * @param line is each line on the storage file containing the taskList
     * @return {@link Task}  is the task created 'line'.
     * @throws ProtagonistException
     */
    private static Task parseLine(String line) throws ProtagonistException {
        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            throw new ProtagonistException("Corrupted save file line: " + line);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;

            case "D":
                if (parts.length < 4) {
                    throw new ProtagonistException("Invalid data entry: " + line);
                }
                task = new Deadline(description, description, parts[3]);
                break;

            case "E":
                if (parts.length < 5) {
                    throw new ProtagonistException("Invalid data entry: " + line);
                }
                task = new Event(description, description, parts[3], parts[4]);
                break;

            default:
                throw new ProtagonistException("Unknown task type in file: " + line);
        }

        if (isDone) {
            task.taskDone();
        }

        return task;
    }
}






























