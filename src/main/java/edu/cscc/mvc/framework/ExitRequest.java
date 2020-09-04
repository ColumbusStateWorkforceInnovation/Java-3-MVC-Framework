package edu.cscc.mvc.framework;

/**
 * This request is used to indicate that the application
 * should exit when it is received.
 */
public class ExitRequest extends Request {

    public ExitRequest() {
        super(null, null);
    }

    /**
     * See {@link Request#isExit()}
     * @return true always.
     */
    public boolean isExit() {
        return true;
    }
}
