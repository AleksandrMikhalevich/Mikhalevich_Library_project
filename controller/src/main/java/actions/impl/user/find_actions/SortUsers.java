package actions.impl.user.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.UserDto;
import exceptions.ServiceException;
import impl.UserServiceImpl;
import interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-10-13 20:19
 */
public class SortUsers implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String[] users_ids = req.getParameterValues("users_ids");
        if (users_ids == null) {
            users_ids = new String[0];
        }
        try {
            UserService userService = new UserServiceImpl();
            List<UserDto> userList = userService.sortAllUsersByName(users_ids);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userList", userList);
            httpSession.setAttribute("sortUserResults", 0);
            page = PageManager.getProperty("page.users");
        } catch (ServiceException e) {
            req.setAttribute("errorSortUsers", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.users");
        }
        return page;
    }
}
