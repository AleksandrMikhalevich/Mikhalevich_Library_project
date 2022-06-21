package actions.impl.book.delete_actions;

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

import static actions.impl.constants.Constants.BOOK_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:39
 */
public class DeleteBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(BOOK_ID));
        try {
            BookService bookService = new BookServiceImpl();
            bookService.deleteBook(id);
            List<Book> bookList = bookService.findAllBooks();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("bookList", bookList);
            req.setAttribute("successDeleteBook", MessageManager.getProperty("message.book-delete"));
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorDeleteBook", MessageManager.getProperty("message.book-delete-error"));
            page = PageManager.getProperty("page.books");
        }
        return page;
    }
}
