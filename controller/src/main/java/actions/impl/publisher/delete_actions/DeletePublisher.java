package actions.impl.publisher.delete_actions;

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

import static actions.impl.constants.Constants.GENRE_ID;
import static actions.impl.constants.Constants.PUBLISHER_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:18
 */
public class DeletePublisher implements Command {

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
            publisherService.deletePublisher(id);
            List<Publisher> publisherList = publisherService.findAllPublishers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("publisherList", publisherList);
            req.setAttribute("successDeletePublisher", MessageManager.getProperty("message.publisher-delete"));
            page = PageManager.getProperty("page.publishers");
        } catch (ServiceException e) {
            req.setAttribute("errorDeletePublisher", MessageManager.getProperty("message.publisher-delete-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}
