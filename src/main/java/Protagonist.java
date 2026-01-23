import java.util.Scanner;

public class Protagonist {
    public static void main(String[] args) {
        Tasklist tasklist = new Tasklist();
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;

        Command.hi();
        while(bool){
            String input = scanner.nextLine();
            bool = Parser.parse(input, tasklist);
        }
    }

}
