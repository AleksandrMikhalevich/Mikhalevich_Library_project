package actions.impl.author.delete_actions;

import actions.impl.constants.Constants;
import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Author;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import interfaces.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:10
 */
public class DeleteAuthor implements Command {

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
            authorService.deleteAuthor(id);
            List<Author> authorList = authorService.findAllAuthors();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorList", authorList);
            req.setAttribute("successDeleteAuthor", MessageManager.getProperty("message.author-delete"));
            page = PageManager.getProperty("page.authors");
        } catch (ServiceException e) {
            req.setAttribute("errorDeleteAuthor", MessageManager.getProperty("message.author-delete-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
