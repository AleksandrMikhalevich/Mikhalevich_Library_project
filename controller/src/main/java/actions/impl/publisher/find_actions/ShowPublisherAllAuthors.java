package actions.impl.publisher.find_actions;

import actions.impl.constants.Constants;
import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Publisher;
import exceptions.ServiceException;
import impl.PublisherServiceImpl;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static actions.impl.constants.Constants.PUBLISHER_ID;
import static actions.impl.constants.Constants.PUBLISHER_NAME;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 17:13
 */
public class ShowPublisherAllAuthors implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(PUBLISHER_ID));
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            Publisher publisher = publisherService.findPublisherById(id);
            req.setAttribute("publisher", publisher);
            page = PageManager.getProperty("page.publisherAllAuthors");
        } catch (ServiceException e) {
            req.setAttribute("errorShowPublisherAuthors", MessageManager.getProperty("message.show-publisher-authors-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}
