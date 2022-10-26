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

import static actions.impl.constants.Constants.SEARCH_USER_BY_NAME_QUERY;

/**
 * @author Alex Mikhalevich
 * @created 2022-10-05 19:12
 */
public class FindUserByName implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String login = req.getParameter(SEARCH_USER_BY_NAME_QUERY);
        try {
            UserService userService = new UserServiceImpl();
            List<UserDto> userList = userService.findUserByName(login);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userList", userList);
            httpSession.setAttribute("searchUserResults", MessageManager.getProperty("message.search-results"));
            page = PageManager.getProperty("page.users");
        } catch (ServiceException e) {
            req.setAttribute("errorSearchUserResults", MessageManager.getProperty("message.search-error"));
            page = PageManager.getProperty("page.users");
        }
        return page;
    }
}
