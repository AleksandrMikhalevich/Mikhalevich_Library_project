package actions.impl.book.update_actions;

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
import static actions.impl.constants.Constants.BOOK_RECEIPT_DATE;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:38
 */
public class UpdateBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(BOOK_ID));
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
            bookService.updateBook(id, title, language, yearOfPublishing, Date.valueOf(receiptDate),
                    authorIds, genreIds, publisherId);
            List<Book> bookList = bookService.findAllBooks();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("bookList", bookList);
            req.setAttribute("successUpdateBook", MessageManager.getProperty("message.book-update"));
            page = PageManager.getProperty("page.books");
        } catch (ServiceException e) {
            req.setAttribute("errorUpdateBook", MessageManager.getProperty("message.book-update-error"));
            page = PageManager.getProperty("page.books");
        }
        return page;
    }
}

