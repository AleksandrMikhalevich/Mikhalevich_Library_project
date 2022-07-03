import actions.CommandHandler;
import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import exceptions.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:28
 */
@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

    private final CommandHandler commandHandler = new CommandHandler();

    /**
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     * @throws ServletException from work with servlet
     * @throws IOException from work with servlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            executeRequest(req, resp);
        } catch (ServiceException e) {
            String page = PageManager.getProperty("page.error");
            resp.sendRedirect(req.getContextPath() + page);
        }

    }

    /**
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     * @throws ServletException from work with servlet
     * @throws IOException from work with servlet
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            executeRequest(req, resp);
        } catch (ServiceException e) {
            String page = PageManager.getProperty("page.error");
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

    /**
     * Method to execute command and direct to the right jsp page
     * @param req HttpServletRequest from browser
     * @param resp HttpServletResponse to browser
     * @throws ServletException from work with servlet
     * @throws IOException from work with servlet
     * @throws ServiceException from work with services
     */
    private void executeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String page;
        Command executionCommand = commandHandler.getCommand(req);
        page = executionCommand.execute(req);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = PageManager.getProperty("page.index");
            req.setAttribute("nullPage", MessageManager.getProperty("message.null-page"));
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

}
