public class Ui {
    public static void line() {
        System.out.println("=======================================");
    }
    public static void line(String text, int num){
        String str = "";
        for (int i = 0; i < num; i++) {
            str += text;
        }
        System.out.println(str);
    }
    public static void genericListMsg(){
        System.out.println("Here are the tasks in your list:\n");
    }
    public static void markMsg(){
        System.out.println("Alright, I've marked this task as done:\n");
    }
    public static void unMarkMsg(){
        System.out.println("Okay, I've marked this task as not done yet:\n");
    }
    public static void addedTaskMsg(){
        System.out.println("Alright, I've added this task:\n");
    }
    public static void emptyLine() {
        System.out.println("");
    }
    public static void greeting() {
        line();
        System.out.println("Greetings from The Protagonist\nHow may I assist you?");
        line();
    }
    public static void goodbye() {
        emptyLine();
        line();
        System.out.println("Goodbye. May our paths cross again.");
        line();
    }
    public static void deleteTaskMsg() {
        System.out.println("Alright, I've deleted this task from your list:\n");
    }
    public static void echo(String input){
        line();
        System.out.println("added: '" + input + "'");
        line();
    }
    public static void deleteError(int length, String input){
        emptyLine();
        line("X", 40);
        System.out.println("Unable to delete task\n" +
                "Please check if index of task is correct\n" +
                "You have entered: " + input + "\n" +
                "There are " + length + " tasks in your list!!!\n");
        line("X",40);
    }

}
