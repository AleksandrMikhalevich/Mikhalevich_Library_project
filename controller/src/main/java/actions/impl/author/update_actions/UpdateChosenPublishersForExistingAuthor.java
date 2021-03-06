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
import java.util.Set;

import static actions.impl.constants.Constants.PUBLISHERS_IDS;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-15 21:21
 */
public class UpdateChosenPublishersForExistingAuthor implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String[] publishers_ids = req.getParameterValues(PUBLISHERS_IDS);
        if (publishers_ids == null) {
            publishers_ids = new String[0];
        }
        try {
            PublisherService publisherService = new PublisherServiceImpl();
            Set<PublisherDto> publisherSet = publisherService.choosePublishersToAuthor(publishers_ids);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("updatedChosenPublishersToAuthor", publisherSet);
            page = PageManager.getProperty("page.author-update");
        } catch (ServiceException e) {
            req.setAttribute("errorUpdateChosenPublishers", MessageManager.getProperty("message.chosen-publishers-add-error"));
            page = PageManager.getProperty("page.author-update");
        }
        return page;
    }
}
