package actions.impl.publisher.find_actions;

import actions.interfaces.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:21
 */
public class FindPublisherById implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
