package actions.impl.publisher.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Genre;
import entities.Publisher;
import exceptions.ServiceException;
import impl.GenreServiceImpl;
import impl.PublisherServiceImpl;
import interfaces.GenreService;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:23
 */
public class FindAllPublishers implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            List<Publisher> publisherList = publisherService.findAllPublishers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("publisherList", publisherList);
            page = PageManager.getProperty("page.publishers");
        } catch (ServiceException e) {
            req.setAttribute("errorPublisherList", MessageManager.getProperty("message.publisher-list-error"));
            page = PageManager.getProperty("page.main");
        }
        return page;
    }
}
