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

import static actions.impl.constants.Constants.SEARCH_AUTHOR_BY_NAME_QUERY;
import static actions.impl.constants.Constants.SEARCH_BOOK_BY_NAME_QUERY;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:17
 */
public class FindAuthorByName implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String name = req.getParameter(SEARCH_AUTHOR_BY_NAME_QUERY);
        try {
            AuthorService authorService = new AuthorServiceImpl();
            List<AuthorDto> authorList = authorService.findAuthorByName(name);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorList", authorList);
            httpSession.setAttribute("searchAuthorResults", MessageManager.getProperty("message.search-results"));
            page = PageManager.getProperty("page.authors");
        } catch (ServiceException e) {
            req.setAttribute("errorSearchAuthorResults", MessageManager.getProperty("message.search-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
