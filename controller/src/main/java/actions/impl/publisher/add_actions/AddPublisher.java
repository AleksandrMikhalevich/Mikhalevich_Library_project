package actions.impl.publisher.add_actions;

import actions.impl.constants.Constants;
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

import static actions.impl.constants.Constants.PUBLISHER_NAME;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:17
 */
public class AddPublisher implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String name = req.getParameter(PUBLISHER_NAME);
        String country = req.getParameter(Constants.PUBLISHER_COUNTRY);
        String city = req.getParameter(Constants.PUBLISHER_CITY);
        String street = req.getParameter(Constants.PUBLISHER_STREET);
        String house = req.getParameter(Constants.PUBLISHER_HOUSE);
        String zipcode = req.getParameter(Constants.PUBLISHER_ZIPCODE);
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            publisherService.addPublisher(name, country, city, street, house, zipcode);
            List<Publisher> publisherList = publisherService.findAllPublishers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("publisherList", publisherList);
            req.setAttribute("successAddPublisher", MessageManager.getProperty("message.publisher-add"));
            page = PageManager.getProperty("page.publishers");
        } catch (ServiceException e) {
            req.setAttribute("errorAddPublisher", MessageManager.getProperty("message.publisher-add-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}
