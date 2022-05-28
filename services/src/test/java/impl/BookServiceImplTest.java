package impl;

import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Publisher;
import exceptions.ServiceException;
import impl.mocks.MockUtils;
import interfaces.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 17:50
 */
class BookServiceImplTest {

    @Test
    void shouldAddBookToDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        Book bookFromDB = bookService.findBookById(book.getId());
        Assertions.assertNotNull(bookFromDB);
        Assertions.assertEquals(book, bookFromDB);
    }

    @Test
    void shouldUpdateBookInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        Book bookToUpdate = Book.builder()
                .id(book.getId())
                .title(UPDATE_BOOK_TITLE)
                .language(UPDATE_BOOK_LANGUAGE)
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing(UPDATE_BOOK_YEAR_OF_PUBLISHING)
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
                .build();
        bookService.updateBook(bookToUpdate);
        Book bookFromDB = bookService.findBookById(bookToUpdate.getId());
        Assertions.assertEquals(bookToUpdate,bookFromDB);
    }

    @Test
    void shouldDeleteBookInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        bookService.deleteBook(book.getId());
        Book bookFromDB = bookService.findBookById(book.getId());
        Assertions.assertNull(bookFromDB);
    }

    @Test
    void shouldFindBookByIdInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        Book bookFromDB = bookService.findBookById(book.getId());
        Assertions.assertNotNull(bookFromDB);
        Assertions.assertNotNull(bookFromDB.getId());
        Assertions.assertEquals(BOOK_TITLE, bookFromDB.getTitle(), "Book name is not equals");
        Assertions.assertEquals(LANGUAGE, bookFromDB.getLanguage(), "Language is not equals");
        Assertions.assertEquals(YEAR_OF_PUBLISHING, bookFromDB.getYearOfPublishing(), "Year of publishing is not equals");
    }

    @Test
    void shouldFindBookByNameInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        Book bookFromDB = bookService.findBookById(book.getId());
        List<Book> listFromDB = bookService.findBookByName(BOOK_TITLE);
        Assertions.assertTrue(listFromDB.contains(bookFromDB));
    }

    @Test
    void shouldFindAllBooksInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        Book bookFromDB = bookService.findBookById(book.getId());
        List<Book> listFromDB = bookService.findAllBooks();
        Assertions.assertTrue(listFromDB.contains(bookFromDB));
    }
}