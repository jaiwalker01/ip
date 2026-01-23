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
    public static void mark(Tasklist tasklist, String maybeIndex, String input) {
        try {
            int x = Integer.parseInt(maybeIndex);
            if (x < tasklist.totalTasks() ) {
                tasklist.doTask(x - 1);
                Ui.emptyLine();
                Ui.line();
                Ui.markMsg();
                tasklist.getTask(x - 1).printTask();
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
                Ui.markMsg();
                tasklist.getTask(x - 1).printTask();
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
}
