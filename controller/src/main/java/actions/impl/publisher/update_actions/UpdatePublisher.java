package actions.impl.publisher.update_actions;

import actions.impl.constants.Constants;
import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.PublisherDto;
import entities.Publisher;
import exceptions.ServiceException;
import impl.PublisherServiceImpl;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static actions.impl.constants.Constants.*;
import static actions.impl.constants.Constants.PUBLISHER_ID;
import static actions.impl.constants.Constants.PUBLISHER_NAME;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:19
 */
public class UpdatePublisher implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(PUBLISHER_ID));
        String name = req.getParameter(PUBLISHER_NAME);
        String country = req.getParameter(PUBLISHER_COUNTRY);
        String city = req.getParameter(PUBLISHER_CITY);
        String street = req.getParameter(PUBLISHER_STREET);
        String house = req.getParameter(PUBLISHER_HOUSE);
        String zipcode = req.getParameter(PUBLISHER_ZIPCODE);
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            publisherService.updatePublisher(id, name, country, city, street, house, zipcode);
            List<PublisherDto> publisherList = publisherService.findAllPublishers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("publisherList", publisherList);
            req.setAttribute("successUpdatePublisher", MessageManager.getProperty("message.publisher-update"));
            page = PageManager.getProperty("page.publishers");
        } catch (ServiceException e) {
            req.setAttribute("errorUpdatePublisher", MessageManager.getProperty("message.publisher-update-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}
