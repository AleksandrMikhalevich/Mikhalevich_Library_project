package actions.impl.genre.find_actions;

import actions.interfaces.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 12:57
 */
public class FindGenreById implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
