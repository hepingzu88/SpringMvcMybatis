package yyd.yun.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Thrown if a User does not have permission to access a particular method.
 */
public class UnauthorizedException extends Exception {

    private Throwable nestedThrowable = null;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException( String msg ) {
        super(msg);
    }

    public UnauthorizedException(Throwable nestedThrowable) {
        this.nestedThrowable = nestedThrowable;
    }

    public UnauthorizedException(String msg, Throwable nestedThrowable) {
        super(msg);
        this.nestedThrowable = nestedThrowable;
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (nestedThrowable != null) {
            nestedThrowable.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if (nestedThrowable != null) {
            nestedThrowable.printStackTrace(ps);
        }
    }

    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if (nestedThrowable != null) {
            nestedThrowable.printStackTrace(pw);
        }
    }
}
