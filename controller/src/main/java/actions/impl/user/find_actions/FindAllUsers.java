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
 * @created 2022-10-05 19:14
 */
public class FindAllUsers implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            UserService userService = new UserServiceImpl();
            List<UserDto> userList = userService.findAllUsers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userList", userList);
            httpSession.removeAttribute("sortUserResults");
            httpSession.removeAttribute("searchUserResults");
            page = PageManager.getProperty("page.users");
        } catch (ServiceException e) {
            req.setAttribute("errorUserList", MessageManager.getProperty("message.user-list-error"));
            page = PageManager.getProperty("page.main");
        }
        return page;
    }
}
