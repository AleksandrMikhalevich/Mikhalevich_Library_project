package actions.interfaces;

import exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:33
 */
public interface Command {

    /**
     * Common method to execute request from browser (Command pattern)
     */
    String execute(HttpServletRequest req);
}
