public class Ui {
    public static void line() {
        System.out.println("=======================================");
    }
    public static void genericListMsg(){
        System.out.println("Here are the tasks in your list:\n");
    }
    public static void markMsg(){
        System.out.println("Alright, I've marked this task as done:\n");
    }
    public static void unMarkMsg(){
        System.out.println("Damn okay, I've marked this task as not done yet:\n");
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
    public static void echo(String input){
        line();
        System.out.println("added: '" + input + "'");
        line();
    }

}
