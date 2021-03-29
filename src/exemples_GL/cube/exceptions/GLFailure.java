package exceptions;

public class GLFailure extends RuntimeException {

    /**
     * default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new glFailure exception.
     * @param failure description of the operation which failed
     */
    public GLFailure(String failure) {
        super(String.format("OpenGL failed to execute : %s", failure));
    }
}
