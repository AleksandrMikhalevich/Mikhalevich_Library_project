package impl;

import entities.Author;
import exceptions.ServiceException;
import impl.mocks.MockUtils;
import interfaces.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:31
 */
class AuthorServiceImplTest {


    @Test
    void shouldAddAuthorToDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        Author authorFromDB = authorService.findAuthorById(author.getId());
        Assertions.assertNotNull(authorFromDB);
        Assertions.assertEquals(author, authorFromDB);
    }

    @Test
    void shouldUpdateAuthorInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        Author authorToUpdate = Author.builder()
                .id(author.getId())
                .firstName(UPDATE_AUTHOR_FIRST_NAME)
                .secondName(UPDATE_AUTHOR_SECOND_NAME)
                .surname(UPDATE_AUTHOR_SURNAME)
                .country(UPDATE_AUTHOR_COUNTRY)
                .build();
        authorService.updateAuthor(authorToUpdate);
        Author authorFromDB = authorService.findAuthorById(authorToUpdate.getId());
        Assertions.assertEquals(authorToUpdate,authorFromDB);
    }

    @Test
    void shouldDeleteAuthorInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        authorService.deleteAuthor(author.getId());
        Author authorFromDB = authorService.findAuthorById(author.getId());
        Assertions.assertNull(authorFromDB);
    }

    @Test
    void shouldFindAuthorByIdInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        Author authorFromDB = authorService.findAuthorById(author.getId());
        Assertions.assertNotNull(authorFromDB);
        Assertions.assertNotNull(authorFromDB.getId());
        Assertions.assertEquals(FIRST_NAME, authorFromDB.getFirstName(), "First name is not equals");
        Assertions.assertEquals(SECOND_NAME, authorFromDB.getSecondName(), "Second name is not equals");
        Assertions.assertEquals(SURNAME, authorFromDB.getSurname(), "Surname is not equals");
        Assertions.assertEquals(AUTHOR_COUNTRY, authorFromDB.getCountry(), "Country is not equals");
    }

    @Test
    void shouldFindAuthorByNameInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        List<Author> listFromDB = authorService.findAuthorByName(FIRST_NAME);
        Assertions.assertTrue(listFromDB.contains(author));
    }

    @Test
    void shouldFindAllAuthorsInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        Author author2 = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        authorService.addAuthor(author2);
        Author authorFromDB = authorService.findAuthorById(author.getId());
        Author authorFromDB2 = authorService.findAuthorById(author2.getId());
        List<Author> listFromDB = authorService.findAllAuthors();
        Assertions.assertTrue(listFromDB.contains(authorFromDB));
        Assertions.assertTrue(listFromDB.contains(authorFromDB2));
    }
}