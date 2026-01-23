import java.util.Scanner;

public class Protagonist {
    public static void main(String[] args) {
        Tasklist tasklist = new Tasklist();

        Ui.greeting();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();
            tasklist.add(new Task(input));

            if(input.equals("bye")){
                Ui.emptyLine();
                Ui.goodbye();
                break;
            } else if (input.equals("list")){
                Ui.emptyLine();
                Ui.line();
                System.out.println(tasklist);
                Ui.line();
            } else {
                Ui.emptyLine();
                Ui.echo(input);
            }
        }
    }

}
