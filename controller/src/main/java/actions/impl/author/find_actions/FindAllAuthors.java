package actions.impl.author.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.AuthorDto;
import entities.Author;
import entities.Book;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.BookServiceImpl;
import interfaces.AuthorService;
import interfaces.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 14:36
 */
public class FindAllAuthors implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            AuthorService authorService = new AuthorServiceImpl();
            List<AuthorDto> authorList = authorService.findAllAuthors();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorList", authorList);
            page = PageManager.getProperty("page.authors");
        } catch (ServiceException e) {
            req.setAttribute("errorAuthorList", MessageManager.getProperty("message.author-list-error"));
            page = PageManager.getProperty("page.main");
        }
        return page;
    }
}
