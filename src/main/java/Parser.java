/*
takes in a string input and dishes out the correct command respective to the input
*/
public class Parser {
    public static Boolean parse(String input, Tasklist tasklist) {
        String inp = input.trim();
        String[] parts = inp.split("\\s+"); // splits on whitespaces

        if (inp.equals("bye")) {
            Command.bye();
            return false;
        } else if (inp.equals("list")) {
            Command.printList(tasklist);
        } else if (parts[0].equals("mark") && parts.length == 2) {
            Command.mark(tasklist, parts[1], input);
        } else if (parts[0].equals("unmark") && parts.length == 2) {
            Command.unmark(tasklist, parts[1], input);
        } else {
            Command.addToList(tasklist, input);
        }
        return true;
    }
}
