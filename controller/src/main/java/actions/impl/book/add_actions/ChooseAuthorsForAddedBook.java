package actions.impl.book.add_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import dto.AuthorDto;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.PublisherServiceImpl;
import interfaces.AuthorService;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static actions.impl.constants.Constants.PUBLISHER_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-08 13:12
 */
public class ChooseAuthorsForAddedBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int publisher_id;
        if (req.getParameter(PUBLISHER_ID) == null) {
            try {
                AuthorService authorService = new AuthorServiceImpl();
                List<AuthorDto> authorList = authorService.findAllAuthors();
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("addedAuthors", authorList);
                page = PageManager.getProperty("page.chooseAuthorsForAddedBook");
            } catch (ServiceException e) {
                req.setAttribute("errorChooseAuthors", MessageManager.getProperty("message.choose-authors-error"));
                page = PageManager.getProperty("page.book-add");
            }
        } else {
            publisher_id = Integer.parseInt(req.getParameter(PUBLISHER_ID));
            try {
                PublisherService publisherService = new PublisherServiceImpl();
                Set<Author> publisherAuthors = publisherService.getPublisherSetOfAuthors(publisher_id);
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("addedAuthors", publisherAuthors);
                page = PageManager.getProperty("page.chooseAuthorsForAddedBook");
            } catch (ServiceException e) {
                req.setAttribute("errorChooseAuthors", MessageManager.getProperty("message.choose-authors-error"));
                page = PageManager.getProperty("page.book-add");
            }
        }
        return page;
    }
}
