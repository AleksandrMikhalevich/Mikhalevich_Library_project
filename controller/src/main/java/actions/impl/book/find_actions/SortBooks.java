package actions.impl.book.find_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import dto.BookDto;
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
 * @created 2022-06-12 21:33
 */
public class SortBooks implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            BookService bookService = new BookServiceImpl();
            HttpSession httpSession = req.getSession();
            if (req.getParameter(BOOKS_SORTING).equals("sort_by_date")) {
                List<BookDto> bookListSortByDate = bookService.sortAllBooksByDate();
                httpSession.setAttribute("bookList", bookListSortByDate);
            } else {
                List<BookDto> bookListSortByName = bookService.sortAllBooksByName();
                httpSession.setAttribute("bookList", bookListSortByName);
            }
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorSortBooks", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.books");
        }
        return page;
    }
}
