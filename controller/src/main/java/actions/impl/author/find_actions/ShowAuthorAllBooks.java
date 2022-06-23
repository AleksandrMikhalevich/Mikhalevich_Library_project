package actions.impl.author.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.AuthorDto;
import entities.Author;
import entities.Book;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import interfaces.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

import static actions.impl.constants.Constants.AUTHOR_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:45
 */
public class ShowAuthorAllBooks implements Command {

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
            AuthorDto author = authorService.findAuthorById(id);
            req.setAttribute("author", author);
            page = PageManager.getProperty("page.authorAllBooks");
        } catch (ServiceException e) {
            req.setAttribute("errorShowAuthorBooks", MessageManager.getProperty("message.show-author-books-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
