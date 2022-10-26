package actions.impl.user.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Alex Mikhalevich
 * @created 2022-09-30 17:29
 */
public class SignOutUser implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("user");
        req.setAttribute("successSignOut", MessageManager.getProperty("message.success-sign-out"));
        return PageManager.getProperty("page.index");
    }
}
