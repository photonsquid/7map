package exceptions;

public class InitException extends Exception {

    /**
     * just so vs code can happily stop throwing warnings for no fucking reason
     */
    private static final long serialVersionUID = 1L;

    public InitException(String report) {
        super(String.format("[Execution stopped] %s", report));
    }
}
