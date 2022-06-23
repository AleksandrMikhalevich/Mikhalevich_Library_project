package actions.impl.book.delete_actions;

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

import static actions.impl.constants.Constants.BOOK_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:39
 */
public class FindBookByIdToDelete implements Command {

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
            BookDto book = bookService.findBookById(id);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("bookToDelete", book);
            page = PageManager.getProperty("page.book-delete");
        } catch (ServiceException e) {
            req.setAttribute("errorFindBookToDelete", MessageManager.getProperty("message.book-find-error"));
            page = PageManager.getProperty("path.page.books");
        }
        return page;
    }
}
