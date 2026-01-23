import java.util.Scanner;

public class Protagonist {
    public static void main(String[] args) {
        Ui.greeting();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if(input.equals("bye")){
                Ui.emptyLine();
                Ui.goodbye();
                break;
            }
            Ui.emptyLine();
            Ui.echo(input);
        }
    }

}
