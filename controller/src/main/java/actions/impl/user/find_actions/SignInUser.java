package actions.impl.user.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.UserDto;
import exceptions.ServiceException;
import impl.UserServiceImpl;
import interfaces.UserService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static actions.impl.constants.Constants.USER_LOGIN;
import static actions.impl.constants.Constants.USER_PASSWORD;

/**
 * @author Alex Mikhalevich
 * @created 2022-09-30 12:38
 */
public class SignInUser implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String login = req.getParameter(USER_LOGIN);
        String password = req.getParameter(USER_PASSWORD);
        try {
            UserService userService = new UserServiceImpl();
            UserDto userDto = userService.findUserByLogin(login);
            HttpSession httpSession = req.getSession();
            if (password.equals(userDto.getPassword())) {
                httpSession.setAttribute("user", userDto);
                req.setAttribute("successSignIn", MessageManager.getProperty("message.success-sign-in"));
                page = PageManager.getProperty("page.index");
            } else {
                req.setAttribute("errorSignIn", MessageManager.getProperty("message.error-sign-in"));
                page = PageManager.getProperty("page.login");
            }
        } catch (ServiceException | NoResultException e) {
            req.setAttribute("errorSignIn", MessageManager.getProperty("message.error-sign-in"));
            page = PageManager.getProperty("page.login");
        }
        return page;
    }
}
