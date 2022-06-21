package actions.impl.publisher.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Publisher;
import exceptions.ServiceException;
import impl.PublisherServiceImpl;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;

import static actions.impl.constants.Constants.PUBLISHER_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 17:12
 */
public class ShowPublisherAllBooks implements Command {

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
            page = PageManager.getProperty("page.publisherAllBooks");
        } catch (ServiceException e) {
            req.setAttribute("errorShowPublisherBooks", MessageManager.getProperty("message.show-publisher-books-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}
