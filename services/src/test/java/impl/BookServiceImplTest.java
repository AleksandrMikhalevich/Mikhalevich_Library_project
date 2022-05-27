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
        Book bookFromDB = bookService.findById(book.getId());
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
                .title("Aaa")
                .language("Bbb")
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing("1900")
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
                .build();
        bookService.updateBook(bookToUpdate);
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
    }

    @Test
    void shouldFindBookByIdInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        BookService bookService = new BookServiceImpl();
        bookService.addBook(book);
        Book bookFromDB = bookService.findById(book.getId());
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
        List<Book> books = bookService.findBookByName(BOOK_TITLE);
        System.out.println(books);
    }
}