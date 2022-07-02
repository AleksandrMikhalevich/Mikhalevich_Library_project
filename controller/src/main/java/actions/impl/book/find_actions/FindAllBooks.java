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

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:46
 */
public class FindAllBooks implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            BookService bookService = new BookServiceImpl();
            List<BookDto> bookList = bookService.findAllBooks();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("bookList", bookList);
            httpSession.removeAttribute("sortBookResults");
            httpSession.removeAttribute("searchBookResults");
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorBookList", MessageManager.getProperty("message.book-list-error"));
            page = PageManager.getProperty("page.main");
        }
        return page;
    }
}
