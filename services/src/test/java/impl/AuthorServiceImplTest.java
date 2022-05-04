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
        Author authorFromDB = authorService.findById(author.getId());
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
                .firstName("Aaa")
                .secondName("Bbb")
                .surname("Ccc")
                .country("Ddd")
                .build();
        authorService.updateAuthor(authorToUpdate);
    }

    @Test
    void shouldDeleteAuthorInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        authorService.deleteAuthor(author.getId());
    }

    @Test
    void shouldFindAuthorByIdInDatabaseByService() throws ServiceException {
        Author author = MockUtils.createAuthor();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        Author authorFromDB = authorService.findById(author.getId());
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
        Author author2 = Author.builder()
                .firstName("Yanka")
                .secondName("Bbb")
                .surname("Ccc")
                .country("Ddd")
                .build();
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(author);
        authorService.addAuthor(author2);
        List<Author> authors = authorService.findAuthorByName(FIRST_NAME);
        System.out.println(authors);
    }
}