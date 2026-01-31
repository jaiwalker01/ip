import java.util.Scanner;

public class Protagonist {
    public static void main(String[] args) {
        TaskList tasklist = new TaskList();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Command.hi();
        while(isRunning) {
            try {
                String input = scanner.nextLine();
                isRunning = Parser.parse(input, tasklist);
            } catch (UnknownCommandException e) {
                Ui.unknownCommand(e.getMessage());
            } catch (ProtagonistException e) {
                Ui.showError(e.getMessage());
            }

        }
    }

}
