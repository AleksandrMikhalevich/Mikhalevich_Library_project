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
import java.util.ArrayList;
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
        String[] books_ids = req.getParameterValues("books_ids");
        if (books_ids == null) {
            books_ids = new String[0];
        }
        try {
            BookService bookService = new BookServiceImpl();
            HttpSession httpSession = req.getSession();
            if (req.getParameter(BOOKS_SORTING).equals("sort_by_date")) {
                List<BookDto> bookListSortByDate = bookService.sortAllBooksByDate(books_ids);
                httpSession.setAttribute("bookList", bookListSortByDate);
            } else {
                List<BookDto> bookListSortByName = bookService.sortAllBooksByName(books_ids);
                httpSession.setAttribute("bookList", bookListSortByName);
            }
            httpSession.setAttribute("sortBookResults", 0);
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorSortBooks", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.books");
        }
        return page;
    }
}
