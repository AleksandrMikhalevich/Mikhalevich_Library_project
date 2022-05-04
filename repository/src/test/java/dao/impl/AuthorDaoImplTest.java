package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Author;
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
        Author author = MockUtils.createAuthor();
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.create(author);
        Author authorFromDB = authorDao.findById(author.getId());
        Assertions.assertNotNull(authorFromDB);
        Assertions.assertEquals(author, authorFromDB);
    }

    @Test
    void shouldFindAuthorInDatabaseById() throws DaoException {
        Author author = MockUtils.createAuthor();
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.create(author);
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
        Author author = MockUtils.createAuthor();
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.create(author);
        Author authorToUpdate = Author.builder()
                .id(author.getId())
                .firstName("Aaa")
                .secondName("Bbb")
                .surname("Ccc")
                .country("Ddd")
                .build();
        authorDao.update(authorToUpdate);
    }

    @Test
    void shouldDeleteAuthorFromDatabase() throws DaoException {
        Author author = MockUtils.createAuthor();
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.create(author);
        authorDao.delete(author.getId());
    }

    @Test
    void shouldFindAuthorByName() throws DaoException {
        Author author = MockUtils.createAuthor();
        Author author2 = Author.builder()
                .firstName("Maxim")
                .secondName("Bbb")
                .surname("Ccc")
                .country("Ddd")
                .build();
        Dao<Author> authorDao = new AuthorDaoImpl();
        authorDao.create(author);
        authorDao.create(author2);
        List<Author> authors = authorDao.findByName(FIRST_NAME);
        System.out.println(authors);
    }
}