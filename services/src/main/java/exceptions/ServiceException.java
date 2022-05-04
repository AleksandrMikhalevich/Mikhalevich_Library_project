package exceptions;

import java.io.Serial;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-02 14:32
 */
public class ServiceException extends Exception {

    @Serial
    private static final long serialVersionUID = 2538873761878469932L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
