package dao.exceptions;

import java.io.Serial;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:27
 */
public class DaoException extends Exception {

    @Serial
    private static final long serialVersionUID = 3123313928017732762L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}
