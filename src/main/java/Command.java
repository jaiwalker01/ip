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
        System.out.println("Unknown command: " + input +
                "\n\nPlease try using one of the following commands:" +
                "\ntodo <task>                     --> to log generic tasks to do" +
                "\ndeadline <task> /by <time/date> --> to log tasks with deadlines" +
                "\nevent <task> /from <start time/date> /to <end time/date> --> self-explanatory" +
                "\nlist                            --> to list all tasks" +
                "\nmark <task no.>                 --> to mark a task as done" +
                "\nunmark <task no.>               --> to mark a task as NOT done" +
                "\ndelete <task no.>               --> to delete a task" +
                "\nbye                             --> to exit the chat");
        Ui.line();
    }
    public static void byError(String input){
        Ui.emptyLine();
        Ui.line();
        System.out.println("Unknown command: " + input +
                "\n\nPlease use '/by' to log deadline tasks:" +
                "\ndeadline <task> /by <time/date> --> to log tasks with deadlines");
        Ui.line();
    }
    public static void fromToError(String input){
        Ui.emptyLine();
        Ui.line();
        System.out.println("Unknown command: " + input +
                "\n\nPlease use '/from' and '/to as follows to log events:" +
                "\nevent <task> /from <start time/date> /to <end time/date> --> self-explanatory");
        Ui.line();
    }
    public static void delete(Tasklist tasklist, String index, String input) {
        try {
            int x = Integer.parseInt(index);
            if (x < tasklist.totalTasks() && x > 0) {
                Task task = tasklist.getTask(x - 1);
                tasklist.removeTask(x - 1);
                Ui.emptyLine();
                Ui.line();
                Ui.deleteTaskMsg();
                Command.printTask(task);
                Command.numOfTasks(tasklist);
                Ui.emptyLine();
                Ui.line();
            } else {
                Command.deleteError(tasklist, index);
            }
        } catch (NumberFormatException e) {
//          System.out.println("Invalid marking: " + maybeIndex);
            Command.addToList(tasklist, input);
            return;
        }

    }
    public static void deleteError(Tasklist tasklist, String input) {
        Ui.deleteError(tasklist.totalTasks(), input);

    }
}
