package actions.impl.book.update_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import entities.Author;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import interfaces.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static actions.impl.constants.Constants.AUTHOR_IDS;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-08 16:22
 */
public class UpdateChosenAuthorsForExistingBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String[] author_ids = req.getParameterValues(AUTHOR_IDS);
        if (author_ids == null) {
            author_ids = new String[0];
        }
        try {
            AuthorService authorService = new AuthorServiceImpl();
            Set<Author> authorSet = authorService.chooseAuthorsToBook(author_ids);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("chosenAuthors", authorSet);
            page = PageManager.getProperty("page.book-update");
        } catch (ServiceException e) {
            req.setAttribute("errorAddChosenAuthors", MessageManager.getProperty("message.chosen-authors-add-error"));
            page = PageManager.getProperty("page.author-update");
        }
        return page;
    }
}
