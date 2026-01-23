public class Command {
    public static void hi() {
        Ui.greeting();
    }
    public static void bye() {
        Ui.goodbye();
    }
    public static void printList(Tasklist tasklist) {
        Ui.emptyLine();
        Ui.line();
        Ui.genericListMsg();
        System.out.println(tasklist);
        Ui.line();
    }
    public static void printTask(Task task) {
        System.out.println(task);
    }
    public static void mark(Tasklist tasklist, String maybeIndex, String input) {
        try {
            int x = Integer.parseInt(maybeIndex);
            if (x < tasklist.totalTasks() ) {
                tasklist.doTask(x - 1);
                Ui.emptyLine();
                Ui.line();
                Ui.markMsg();
                System.out.println(tasklist.getTask(x - 1));
                Ui.line();
            } else {
                Command.addToList(tasklist, input);
            }

        } catch (NumberFormatException e) {
//            System.out.println("Invalid marking: " + maybeIndex);
            Command.addToList(tasklist, input);
            return;
        }
    }
    public static void unmark(Tasklist tasklist, String maybeIndex, String input) {
        try {
            int x = Integer.parseInt(maybeIndex);
            if (x < tasklist.totalTasks() ) {
                tasklist.undoTask(x - 1);
                Ui.emptyLine();
                Ui.line();
                Ui.unMarkMsg();
                System.out.println(tasklist.getTask(x - 1));
                Ui.line();
            } else {
                Command.addToList(tasklist, input);
            }

        } catch (NumberFormatException e) {
//            System.out.println("Invalid marking: " + maybeIndex);
            Command.addToList(tasklist, input);
            return;
        }
    }
    public static void addToList(Tasklist tasklist, String input) {
        tasklist.add(new Task(input));
        Ui.emptyLine();
        Ui.echo(input);
    }
    public static void numOfTasks(Tasklist tasklist) {
        System.out.println("You have " + (tasklist.totalTasks() - 1) + " tasks in the list");
    }
    public static void unKnownCommand(String input){
        Ui.emptyLine();
        Ui.line();
        System.out.println("Unknown command: " + input);
        Ui.line();
    }
}
