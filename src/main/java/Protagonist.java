import java.util.Scanner;

public class Protagonist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskList tasklist;

        try {
            tasklist = Storage.load();
        } catch (ProtagonistException e) {
            Ui.showError(e.getMessage());
            tasklist = new TaskList();
        }

        Command.hi();

        while(isRunning) {
            try {
                String input = scanner.nextLine();
                isRunning = Parser.parse(input, tasklist);
                Storage.saveToFile(tasklist);
            } catch (UnknownCommandException e) {
                Ui.unknownCommand(e.getMessage());
            } catch (ProtagonistException e) {
                Ui.showError(e.getMessage());
            }

        }
    }

}
