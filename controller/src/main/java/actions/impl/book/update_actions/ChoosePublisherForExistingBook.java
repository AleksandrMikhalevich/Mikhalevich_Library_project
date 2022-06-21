package actions.impl.book.update_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import dao.exceptions.DaoException;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.PublisherServiceImpl;
import interfaces.AuthorService;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static actions.impl.constants.Constants.AUTHOR_IDS;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-09 13:59
 */
public class ChoosePublisherForExistingBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String[] author_ids = req.getParameterValues(AUTHOR_IDS);
        if (author_ids == null) {
            try {
                PublisherService publisherService = new PublisherServiceImpl();
                List<Publisher> publisherList = publisherService.findAllPublishers();
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("changedPublishers", publisherList);
                page = PageManager.getProperty("page.choosePublisherForExistingBook");
            } catch (ServiceException e) {
                req.setAttribute("errorChoosePublisher", MessageManager.getProperty("message.choose-publisher-error"));
                page = PageManager.getProperty("page.book-update");
            }
        } else {
            try {
                AuthorService authorService = new AuthorServiceImpl();
                Set<Publisher> authorsPublishers = authorService.getAuthorSetOfPublishers(author_ids);
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("changedPublishers", authorsPublishers);
                page = PageManager.getProperty("page.choosePublisherForExistingBook");
            } catch (ServiceException e) {
                req.setAttribute("errorChoosePublisher", MessageManager.getProperty("message.choose-publisher-error"));
                page = PageManager.getProperty("page.book-update");
            }
        }
        return page;
    }
}
