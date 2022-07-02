package actions.impl.author.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.AuthorDto;
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
        String[] authors_ids = req.getParameterValues("authors_ids");
        if (authors_ids == null) {
            authors_ids = new String[0];
        }
        try {
            AuthorService authorService = new AuthorServiceImpl();
            List<AuthorDto> authorListSortBySurname = authorService.sortAllAuthorsBySurname(authors_ids);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorList", authorListSortBySurname);
            httpSession.setAttribute("sortAuthorsResults", 0);
            page = PageManager.getProperty("page.authors");
        } catch (ServiceException e) {
            req.setAttribute("errorSortAuthors", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
