package actions.impl.user.add_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import exceptions.ServiceException;
import impl.UserServiceImpl;
import interfaces.UserService;

import javax.servlet.http.HttpServletRequest;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-09-30 12:37
 */
public class AddUser implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page = PageManager.getProperty("page.index");
        String login = req.getParameter(USER_LOGIN);
        String password = req.getParameter(USER_PASSWORD);
        String passwordConfirm = req.getParameter(USER_PASSWORD_CONFIRM);
        String email = req.getParameter(USER_EMAIL);
        try {
            UserService userService = new UserServiceImpl();
            if (!password.equals(passwordConfirm)) {
                req.setAttribute("errorPassword", MessageManager.getProperty("message.error-password"));
                page = PageManager.getProperty("page.registration");
            }
            if (!userService.addUser(login, password, email)){
                req.setAttribute("errorLogin", MessageManager.getProperty("message.error-login"));
                page = PageManager.getProperty("page.registration");
            }
            req.setAttribute("successAddUser", MessageManager.getProperty("message.success-add-user"));
        } catch (ServiceException e) {
            req.setAttribute("errorAddUser", MessageManager.getProperty("message.error-add-user"));
            page = PageManager.getProperty("page.registration");
        }
        return page;
    }
}
