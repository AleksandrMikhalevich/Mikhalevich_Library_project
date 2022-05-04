package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static dao.impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-02 11:53
 */
class BookDaoImplTest {

    @Test
    void shouldCreateBookInDatabase() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.create(book);
        Book bookFromDB = bookDao.findById(book.getId());
        Assertions.assertNotNull(bookFromDB);
        Assertions.assertEquals(book, bookFromDB);
    }

    @Test
    void shouldFindBookInDatabaseById() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.create(book);
        Book bookFromDB = bookDao.findById(book.getId());
        Assertions.assertNotNull(bookFromDB);
        Assertions.assertNotNull(bookFromDB.getId());
        Assertions.assertEquals(BOOK_NAME, bookFromDB.getName(), "Book name is not equals");
        Assertions.assertEquals(LANGUAGE, bookFromDB.getLanguage(), "Language is not equals");
        Assertions.assertEquals(YEAR_OF_PUBLISHING, bookFromDB.getYearOfPublishing(), "Year of publishing is not equals");
    }

    @Test
    void shouldUpdateBookInDatabase() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.create(book);
        Book bookToUpdate = Book.builder()
                .name("Aaa")
                .language("Bbb")
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing("1900")
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
                .build();
        bookDao.update(bookToUpdate);
    }

    @Test
    void shouldDeleteBookFromDatabase() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.create(book);
        bookDao.delete(book.getId());
    }

    @Test
    void shouldFindBookByName() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.create(book);
        List<Book> books = bookDao.findByName(BOOK_NAME);
        System.out.println(books);
    }
}