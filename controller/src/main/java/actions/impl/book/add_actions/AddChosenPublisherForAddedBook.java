package actions.impl.book.add_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import entities.Publisher;
import exceptions.ServiceException;
import impl.PublisherServiceImpl;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static actions.impl.constants.Constants.PUBLISHER_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-09 14:07
 */
public class AddChosenPublisherForAddedBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int publisher_id;
        if (req.getParameter(PUBLISHER_ID) == null) {
            publisher_id = 0;
        } else {
            publisher_id = Integer.parseInt(req.getParameter(PUBLISHER_ID));
        }
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            Publisher publisher = publisherService.findPublisherById(publisher_id);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("chosenPublisherToAdd", publisher);
            page = PageManager.getProperty("page.book-add");
        } catch (ServiceException e) {
            req.setAttribute("errorAddChosenPublisher", MessageManager.getProperty("message.chosen-publisher-add-error"));
            page = PageManager.getProperty("page.book-add");
        }
        return page;
    }
}
