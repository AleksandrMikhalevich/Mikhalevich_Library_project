package actions.impl.publisher.find_actions;

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

import static actions.impl.constants.Constants.SEARCH_PUBLISHER_BY_NAME_QUERY;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 15:22
 */
public class FindPublisherByName implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String name = req.getParameter(SEARCH_PUBLISHER_BY_NAME_QUERY);
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            List<PublisherDto> publisherList = publisherService.findPublisherByName(name);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("publisherList", publisherList);
            httpSession.setAttribute("searchPublisherResults", MessageManager.getProperty("message.search-results"));
            page = PageManager.getProperty("page.publishers");
        } catch (ServiceException e) {
            req.setAttribute("errorSearchPublisherResults", MessageManager.getProperty("message.search-error"));
            page = PageManager.getProperty("page.publishers");
        }
        return page;
    }
}
