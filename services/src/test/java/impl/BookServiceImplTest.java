package impl;

import dto.BookDto;
import exceptions.ServiceException;
import mocks.MockUtils;
import interfaces.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static mocks.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 17:50
 */
class BookServiceImplTest {

    @BeforeEach
    void setUp() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        bookService.addBook(BOOK_TITLE, BOOK_LANGUAGE, BOOK_YEAR_OF_PUBLISHING, BOOK_RECEIPT_DATE, BOOK_AUTHORS_IDS,
                BOOK_GENRES_IDS, BOOK_PUBLISHER_ID);
    }

    @Test
    void shouldAddBookToDatabaseByService() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        BookDto bookDtoFromDB = bookService.findBookById(MockUtils.findIdOfBook());
        assertNotNull(bookDtoFromDB);
        assertEquals(BOOK_TITLE, bookDtoFromDB.getTitle(), "Book name is not equals");
        assertEquals(BOOK_LANGUAGE, bookDtoFromDB.getLanguage(), "Language is not equals");
        assertEquals(BOOK_YEAR_OF_PUBLISHING, bookDtoFromDB.getYearOfPublishing(), "Year of publishing is not equals");
        assertEquals(String.valueOf(BOOK_RECEIPT_DATE), String.valueOf(bookDtoFromDB.getReceiptDate()), "Year of publishing is not equals");
    }

    @Test
    void shouldUpdateBookInDatabaseByService() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        int id = MockUtils.findIdOfBook();
        bookService.updateBook(id, UPDATE_BOOK_TITLE, UPDATE_BOOK_LANGUAGE, UPDATE_BOOK_YEAR_OF_PUBLISHING, UPDATE_BOOK_RECEIPT_DATE, BOOK_AUTHORS_IDS,
                BOOK_GENRES_IDS, BOOK_PUBLISHER_ID);
        BookDto bookDtoFromDB = bookService.findBookById(id);
        assertNotNull(bookDtoFromDB);
        assertEquals(UPDATE_BOOK_TITLE, bookDtoFromDB.getTitle(), "Book name is not equals");
        assertEquals(UPDATE_BOOK_LANGUAGE, bookDtoFromDB.getLanguage(), "Language is not equals");
        assertEquals(UPDATE_BOOK_YEAR_OF_PUBLISHING, bookDtoFromDB.getYearOfPublishing(), "Year of publishing is not equals");
    }

    @Test
    void shouldDeleteBookInDatabaseByService() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        int id = MockUtils.findIdOfBook();
        bookService.deleteBook(id);
        BookDto bookDtoFromDB = bookService.findBookById(id);
        assertNull(bookDtoFromDB);
    }

    @Test
    void shouldFindBookByIdInDatabaseByService() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        BookDto bookDtoFromDB = bookService.findBookById(MockUtils.findIdOfBook());
        assertNotNull(bookDtoFromDB);
        assertEquals(BOOK_TITLE, bookDtoFromDB.getTitle(), "Book name is not equals");
        assertEquals(BOOK_LANGUAGE, bookDtoFromDB.getLanguage(), "Language is not equals");
        assertEquals(BOOK_YEAR_OF_PUBLISHING, bookDtoFromDB.getYearOfPublishing(), "Year of publishing is not equals");
    }

    @Test
    void shouldFindBookByNameInDatabaseByService() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        List<BookDto> bookDtoListFromDB = bookService.findBookByName(BOOK_TITLE);
        String title = null;
        for (BookDto bookDto : bookDtoListFromDB) {
            title = bookDto.getTitle();
        }
        assertEquals(BOOK_TITLE, title, "Book name is not equals");
    }

    @Test
    void shouldFindAllBooksInDatabaseByService() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        BookDto bookDtoFromDB = bookService.findBookById(MockUtils.findIdOfBook());
        List<BookDto> bookDtoListFromDB = bookService.findAllBooks();
        assertTrue(bookDtoListFromDB.contains(bookDtoFromDB));
    }

    @Test
    void sortAllBooksByName() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        String id1 = String.valueOf(MockUtils.findIdOfBook());
        BookDto bookDtoFromDB_1 = bookService.findBookById(MockUtils.findIdOfBook());
        bookService.addBook(UPDATE_BOOK_TITLE, UPDATE_BOOK_LANGUAGE, UPDATE_BOOK_YEAR_OF_PUBLISHING, BOOK_RECEIPT_DATE, BOOK_AUTHORS_IDS,
                BOOK_GENRES_IDS, BOOK_PUBLISHER_ID);
        List<BookDto> bookDtoListFromDB = bookService.findBookByName(UPDATE_BOOK_TITLE);
        String id2 = String.valueOf(0);
        for (BookDto bookDto : bookDtoListFromDB) {
            id2 = String.valueOf(bookDto.getId());
        }
        BookDto bookDtoFromDB_2 = bookService.findBookById(Integer.parseInt(id2));
        List<BookDto> bookDtoList = bookService.sortAllBooksByName(new String[]{id1, id2});
        assertEquals(bookDtoFromDB_2, bookDtoList.get(0));
        assertEquals(bookDtoFromDB_1, bookDtoList.get(1));
    }

    @Test
    void sortAllBooksByDate() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        String id1 = String.valueOf(MockUtils.findIdOfBook());
        BookDto bookDtoFromDB_1 = bookService.findBookById(MockUtils.findIdOfBook());
        bookService.addBook(UPDATE_BOOK_TITLE, UPDATE_BOOK_LANGUAGE, UPDATE_BOOK_YEAR_OF_PUBLISHING, UPDATE_BOOK_RECEIPT_DATE, BOOK_AUTHORS_IDS,
                BOOK_GENRES_IDS, BOOK_PUBLISHER_ID);
        List<BookDto> bookDtoListFromDB = bookService.findBookByName(UPDATE_BOOK_TITLE);
        String id2 = String.valueOf(0);
        for (BookDto bookDto : bookDtoListFromDB) {
            id2 = String.valueOf(bookDto.getId());
        }
        BookDto bookDtoFromDB_2 = bookService.findBookById(Integer.parseInt(id2));
        List<BookDto> bookDtoList = bookService.sortAllBooksByName(new String[]{id1, id2});
        assertEquals(bookDtoFromDB_2, bookDtoList.get(0));
        assertEquals(bookDtoFromDB_1, bookDtoList.get(1));
    }
}