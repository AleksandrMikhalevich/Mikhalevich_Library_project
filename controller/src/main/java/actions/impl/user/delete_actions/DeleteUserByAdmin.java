package actions.impl.user.delete_actions;

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

import static actions.impl.constants.Constants.USER_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-10-13 20:02
 */
public class DeleteUserByAdmin implements Command {

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
            List<UserDto> userList = userService.findAllUsers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userList", userList);
            req.setAttribute("successDeleteUser", MessageManager.getProperty("message.success-delete-user"));
            page = PageManager.getProperty("page.users");
        } catch (ServiceException e) {
            req.setAttribute("errorDeleteUser", MessageManager.getProperty("message.error-delete-user"));
            page = PageManager.getProperty("page.users");
        }
        return page;
    }
}
