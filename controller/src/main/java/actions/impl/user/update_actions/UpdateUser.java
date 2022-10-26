package actions.impl.user.update_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import exceptions.ServiceException;
import impl.UserServiceImpl;
import interfaces.UserService;

import javax.servlet.http.HttpServletRequest;

import static actions.impl.constants.Constants.*;
import static actions.impl.constants.Constants.USER_EMAIL;

/**
 * @author Alex Mikhalevich
 * @created 2022-10-04 19:33
 */
public class UpdateUser implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(USER_ID));
        String password = req.getParameter(USER_PASSWORD);
        String passwordConfirm = req.getParameter(USER_PASSWORD_CONFIRM);
        String email = req.getParameter(USER_EMAIL);
        try {
            UserService userService = new UserServiceImpl();
            if (!password.equals(passwordConfirm)) {
                req.setAttribute("errorPassword", MessageManager.getProperty("message.error-password"));
                page = PageManager.getProperty("page.user-account");
            } else {
                userService.updateUser(id, email, password);
                req.setAttribute("successUpdateUser", MessageManager.getProperty("message.success-update-user"));
                page = PageManager.getProperty("page.index");
            }
        } catch (ServiceException e) {
            req.setAttribute("errorUpdateUser", MessageManager.getProperty("message.error-update-user"));
            page = PageManager.getProperty("page.user-account");
        }
        return page;
    }
}
