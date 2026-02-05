package protagonist.exception;

/**
 * Represents unknown command exception in Protagonist
 */
public class UnknownCommandException extends ProtagonistException {
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
