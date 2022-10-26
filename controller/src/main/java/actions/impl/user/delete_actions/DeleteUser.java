package actions.impl.user.delete_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import exceptions.ServiceException;
import impl.UserServiceImpl;
import interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static actions.impl.constants.Constants.USER_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-10-05 12:37
 */
public class DeleteUser implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(USER_ID));
        try {
            UserService userService = new UserServiceImpl();
            userService.deleteUser(id);
            HttpSession httpSession = req.getSession();
            httpSession.removeAttribute("user");
            req.setAttribute("successDeleteUser", MessageManager.getProperty("message.success-delete-user"));
            page = PageManager.getProperty("page.index");
        } catch (ServiceException e) {
            req.setAttribute("errorDeleteUser", MessageManager.getProperty("message.error-delete-user"));
            page = PageManager.getProperty("page.user-account");
        }
        return page;
    }
}
