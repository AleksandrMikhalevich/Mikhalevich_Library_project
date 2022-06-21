package actions.impl;

import actions.interfaces.Command;
import actions.utils.PageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 16:14
 */
public class EmptyRequest implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        return PageManager.getProperty("page.index");
    }
}
