package actions.impl.book.find_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import entities.Book;
import exceptions.ServiceException;
import impl.BookServiceImpl;
import interfaces.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:39
 */
public class FindBookByName implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String name = req.getParameter(SEARCH_BOOK_BY_NAME_QUERY);
        try {
            BookService bookService = new BookServiceImpl();
            List<Book> bookList = bookService.findBookByName(name);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("bookList", bookList);
            req.setAttribute("searchBookResults", MessageManager.getProperty("message.search-results"));
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorSearchBookResults", MessageManager.getProperty("message.search-error"));
            page = PageManager.getProperty("page.books");
        }
        return page;
    }
}
