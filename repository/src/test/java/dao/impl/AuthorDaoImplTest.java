package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Author;
import entities.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dao.impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:41
 */
class AuthorDaoImplTest {

    @Test
    void shouldCreateAuthorInDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Author author = MockUtils.createAuthor(publisher);
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.save(author);
        Author authorFromDB = authorDao.findById(author.getId());
        Assertions.assertNotNull(authorFromDB);
        Assertions.assertEquals(author, authorFromDB);
    }

    @Test
    void shouldFindAuthorInDatabaseById() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Author author = MockUtils.createAuthor(publisher);
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.save(author);
        Author authorFromDB = authorDao.findById(author.getId());
        Assertions.assertNotNull(authorFromDB);
        Assertions.assertNotNull(authorFromDB.getId());
        Assertions.assertEquals(FIRST_NAME, authorFromDB.getFirstName(), "First name is not equals");
        Assertions.assertEquals(SECOND_NAME, authorFromDB.getSecondName(), "Second name is not equals");
        Assertions.assertEquals(SURNAME, authorFromDB.getSurname(), "Surname is not equals");
        Assertions.assertEquals(AUTHOR_COUNTRY, authorFromDB.getCountry(), "Country is not equals");
    }

    @Test
    void shouldUpdateAuthorInDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Author author = MockUtils.createAuthor(publisher);
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.save(author);
        Author authorToUpdate = Author.builder()
                .id(author.getId())
                .firstName(UPDATE_AUTHOR_FIRST_NAME)
                .secondName(UPDATE_AUTHOR_SECOND_NAME)
                .surname(UPDATE_AUTHOR_SURNAME)
                .country(UPDATE_AUTHOR_COUNTRY)
                .build();
        authorDao.update(authorToUpdate);
        Author authorFromDB = authorDao.findById(authorToUpdate.getId());
        Assertions.assertEquals(authorToUpdate,authorFromDB);
    }

    @Test
    void shouldDeleteAuthorFromDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Author author = MockUtils.createAuthor(publisher);
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.save(author);
        authorDao.delete(author.getId());
        Author authorFromDB = authorDao.findById(author.getId());
        Assertions.assertNull(authorFromDB);
    }

    @Test
    void shouldFindAuthorInDatabaseByName() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Author author = MockUtils.createAuthor(publisher);
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.save(author);
        List<Author> listFromDB = authorDao.findByName(FIRST_NAME);
        Assertions.assertTrue(listFromDB.contains(author));
    }

    @Test
    void shouldFindAllAuthorsInDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Author author = MockUtils.createAuthor(publisher);
        Author author2 = MockUtils.createAuthor(publisher);
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.save(author);
        authorDao.save(author2);
        Author authorFromDB = authorDao.findById(author.getId());
        Author authorFromDB2 = authorDao.findById(author2.getId());
        List<Author> listFromDB = authorDao.findAll();
        Assertions.assertTrue(listFromDB.contains(authorFromDB));
        Assertions.assertTrue(listFromDB.contains(authorFromDB2));
    }
}