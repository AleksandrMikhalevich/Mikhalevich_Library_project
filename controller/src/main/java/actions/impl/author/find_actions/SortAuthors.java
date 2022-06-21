package actions.impl.author.find_actions;

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

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:17
 */
public class SortAuthors implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            AuthorService authorService = new AuthorServiceImpl();
            List<Author> authorListSortBySurname = authorService.sortAllAuthorsBySurname();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorList", authorListSortBySurname);
            page = PageManager.getProperty("page.authors");
        } catch (ServiceException e) {
            req.setAttribute("errorSortAuthors", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
