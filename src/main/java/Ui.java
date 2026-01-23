public class Ui {
    public static void line() {
        System.out.println("=======================================");
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
        line();
        System.out.println("Goodbye. May our paths cross again.");
        line();
    }

    public static void echo(String input){
        line();
        System.out.println(input);
        line();
    }

}
