package actions.impl.book.add_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import entities.Book;
import exceptions.ServiceException;
import impl.BookServiceImpl;
import interfaces.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:38
 */
public class AddBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String title = req.getParameter(BOOK_TITLE);
        String language = req.getParameter(BOOK_LANGUAGE);
        String yearOfPublishing = req.getParameter(BOOK_YEAR_OF_PUBLISHING);
        String receiptDate = req.getParameter(BOOK_RECEIPT_DATE);
        String[] author_ids = req.getParameterValues(AUTHOR_IDS);
        if (author_ids == null) {
            author_ids = new String[0];
        }
        int[] authorIds = Arrays.stream(author_ids).mapToInt(Integer::parseInt).toArray();
        String[] genre_ids = req.getParameterValues(GENRE_IDS);
        if (genre_ids == null) {
            genre_ids = new String[0];
        }
        int[] genreIds = Arrays.stream(genre_ids).mapToInt(Integer::parseInt).toArray();
        int publisherId;
        if (req.getParameter(PUBLISHER_ID) == null || req.getParameter(PUBLISHER_ID).isEmpty()) {
            publisherId = 0;
        } else {
            publisherId = Integer.parseInt(req.getParameter(PUBLISHER_ID));
        }
        try {
            BookService bookService = new BookServiceImpl();
            bookService.addBook(title, language, yearOfPublishing, Date.valueOf(receiptDate),
                    authorIds, genreIds, publisherId);
            List<Book> bookList = bookService.findAllBooks();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("bookList", bookList);
            req.setAttribute("successAddBook", MessageManager.getProperty("message.book-add"));
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorAddBook", MessageManager.getProperty("message.book-add-error"));
            page = PageManager.getProperty("path.page.books");
        }
        return page;
    }
}
