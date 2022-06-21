package actions.impl.author.update_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Author;
import entities.Book;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.BookServiceImpl;
import interfaces.AuthorService;
import interfaces.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static actions.impl.constants.Constants.AUTHOR_ID;
import static actions.impl.constants.Constants.BOOK_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:15
 */
public class FindAuthorByIdToUpdate implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(AUTHOR_ID));
        try {
            AuthorService authorService = new AuthorServiceImpl();
            Author author = authorService.findAuthorById(id);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("author", author);
            page = PageManager.getProperty("page.author-update");
        } catch (ServiceException e) {
            req.setAttribute("errorFindAuthorToUpdate", MessageManager.getProperty("message.author-find-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
