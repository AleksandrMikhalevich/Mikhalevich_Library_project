package actions.impl.author.update_actions;

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

/**
 * @author Alex Mikhalevich
 * @created 2022-06-15 14:13
 */
public class ChoosePublishersForUpdatedAuthor implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            List<PublisherDto> publisherList = publisherService.findAllPublishers();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("updatedPublishersToAuthor", publisherList);
            page = PageManager.getProperty("page.choosePublishersForExistingAuthor");
        } catch (ServiceException e) {
            req.setAttribute("errorChoosePublishers", MessageManager.getProperty("message.choose-publisher-error"));
            page = PageManager.getProperty("page.author-update");
        }
        return page;
    }
}
