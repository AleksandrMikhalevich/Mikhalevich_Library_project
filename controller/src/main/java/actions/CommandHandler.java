package actions;

import actions.enums.CommandName;
import actions.impl.EmptyRequest;
import actions.interfaces.Command;
import actions.utils.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 16:03
 */
public final class CommandHandler {

    /**
     * Method to get command from command-repository - from CommandName enumeration
     * @param req HttpServletRequest from browser
     * @return command for execution
     */
    public Command getCommand(HttpServletRequest req) {
        Command current = new EmptyRequest();
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandName currentEnum = CommandName.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            req.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrong-action"));
        }
        return current;
    }
}
