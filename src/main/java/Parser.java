/*
takes in a string input and dishes out the correct command respective to the input
*/
public class Parser {
    public static Boolean parse(String input, Tasklist tasklist) {
        String inp = input.trim();
        String[] parts = inp.split("\\s+"); // splits on whitespaces

        String result = Parser.combiner(parts, 1, parts.length -1 );

        if (inp.equals("bye")) {
            Command.bye();
            return false;
        } else if (inp.equals("list")) {
            Command.printList(tasklist);
        } else if (parts[0].equals("mark") && parts.length == 2) {
            Command.mark(tasklist, parts[1], input);
        } else if (parts[0].equals("unmark") && parts.length == 2) {
            Command.unmark(tasklist, parts[1], input);
        } else if (parts[0].equals("todo") && parts.length >= 2){
            ToDo todoTask = new ToDo(result);
            tasklist.add(todoTask);
            Ui.emptyLine();
            Ui.line();
            Ui.addedTaskMsg();
            Command.printTask(todoTask);
            Command.numOfTasks(tasklist);
            Ui.emptyLine();
            Ui.line();
        } else if (parts[0].equals("deadline")) {
            int index = 0;
            for (int i = 1; i < parts.length; i++){
                if (parts[i].equals("/by")){
                    index = i;
                    break;
                }
            }

            if (index != 0){
                String firstPart = Parser.combiner(parts, 1, index - 1);
                String secondPart = Parser.combiner(parts, index + 1, parts.length - 1);
                Deadline deadline = new Deadline(input, firstPart, secondPart);
                tasklist.add(deadline);
                Ui.emptyLine();
                Ui.line();
                Ui.addedTaskMsg();
                Command.printTask(deadline);
                Command.numOfTasks(tasklist);
                Ui.emptyLine();
                Ui.line();
            } else {
                Command.unKnownCommand(input);
            }
        } else if (parts[0].equals("event")) {
            int indexFrom = 0;
            int indexTo = 0;
            for (int i = 1; i < parts.length; i++){
                if (parts[i].equals("/from")){
                    indexFrom = i;
                } else if (parts[i].equals("/to")){
                    indexTo = i;
                    break;
                }
            }

            if (indexFrom != 0 && indexTo != 0 && indexFrom + 1 < indexTo && indexTo != parts.length - 1){
                String firstPart = Parser.combiner(parts, 1, indexFrom - 1);
                String secondPart = Parser.combiner(parts, indexFrom + 1, indexTo - 1);
                String thirdPart = Parser.combiner(parts, indexTo + 1, parts.length - 1);
                Event event = new Event(input, firstPart, secondPart, thirdPart);
                tasklist.add(event);
                Ui.emptyLine();
                Ui.line();
                Ui.addedTaskMsg();
                Command.printTask(event);
                Command.numOfTasks(tasklist);
                Ui.emptyLine();
                Ui.line();
            } else {
                Command.unKnownCommand(input);
            }
        } else {
            Command.unKnownCommand(input);
        }
        return true;
    }

    // helper function for joining strings from a list of string
    // [a, b, c, d, e]  --> combiner(list, 1, 3) --> b c d
    public static String combiner(String[] inputList, int start, int end){
        StringBuilder sb = new StringBuilder();
        String result = "";
        for (int i = start; i <= Math.min(end, inputList.length - 1); i++) {
            if (i >= start) {
                sb.append(inputList[i]);
            }

            result = sb.toString();
            if (i == inputList.length - 1) {
                break;
            }

            sb.append(" ");
        }
        return result;
    }
}
