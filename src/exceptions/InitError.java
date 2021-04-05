package exceptions;

/**
 * Thrown when an error was encountered during the initialization sequence.
 */
public class InitError extends Error {

    /**
     * just so vs code can happily stop throwing warnings for no fucking reason
     */
    private static final long serialVersionUID = 1L;

    public InitError(String report) {
        super(String.format("[Execution stopped] %s", report));
    }
}
