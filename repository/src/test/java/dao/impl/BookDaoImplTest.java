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
        bookDao.save(book);
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
        bookDao.save(book);
        Book bookFromDB = bookDao.findById(book.getId());
        Assertions.assertNotNull(bookFromDB);
        Assertions.assertNotNull(bookFromDB.getId());
        Assertions.assertEquals(BOOK_TITLE, bookFromDB.getTitle(), "Book name is not equals");
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
        bookDao.save(book);
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
        bookDao.update(bookToUpdate);
        Book bookFromDB = bookDao.findById(bookToUpdate.getId());
        Assertions.assertEquals(bookToUpdate,bookFromDB);
    }

    @Test
    void shouldDeleteBookFromDatabase() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.save(book);
        bookDao.delete(book.getId());
        Book bookFromDB = bookDao.findById(book.getId());
        Assertions.assertNull(bookFromDB);
    }

    @Test
    void shouldFindBookInDatabaseByName() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.save(book);
        Book bookFromDB = bookDao.findById(book.getId());
        List<Book> listFromDB = bookDao.findByName(BOOK_TITLE);
        Assertions.assertTrue(listFromDB.contains(bookFromDB));
    }

    @Test
    void shouldFindAllBooksInDatabase() throws DaoException {
        Author author = MockUtils.createAuthor();
        Genre genre = MockUtils.createGenre();
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Book book = MockUtils.createBook(author, genre, publisher);
        Dao<Book> bookDao = new BookDaoImpl();
        bookDao.save(book);
        Book bookFromDB = bookDao.findById(book.getId());
        List<Book> listFromDB = bookDao.findAll();
        Assertions.assertTrue(listFromDB.contains(bookFromDB));
    }
}