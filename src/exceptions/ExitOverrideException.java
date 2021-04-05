package exceptions;

/**
 * Thrown when the app has to be forcefully stoped by an internal process.
 * <p>
 * This exception should always be handled by the main thread at some point 
 * in the loop.
 */
public class ExitOverrideException extends RuntimeException {
    
    /**
     * default sVUID
     */
    private static final long serialVersionUID = 1L;

    public ExitOverrideException(int code) {
        super(String.format("Application was forcefully stoped with code %d", code));
    }
}
