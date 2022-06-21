package actions.impl.publisher.find_actions;

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

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:24
 */
public class SortPublishers implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            List<Publisher> publisherList = publisherService.sortAllPublishersByName();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("publisherList", publisherList);
            page = PageManager.getProperty("page.publishers");
        } catch (ServiceException e) {
            req.setAttribute("errorSortPublishers", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}