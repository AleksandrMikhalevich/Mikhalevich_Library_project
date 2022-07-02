package impl;

import dto.AuthorDto;
import dto.PublisherDto;
import exceptions.ServiceException;
import mocks.MockUtils;
import interfaces.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static mocks.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:31
 */
class AuthorServiceImplTest {

    @BeforeEach
    void setUp() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        authorService.addAuthor(AUTHOR_SURNAME, AUTHOR_FIRST_NAME, AUTHOR_SECOND_NAME, AUTHOR_COUNTRY, AUTHOR_PUBLISHERS_IDS);
    }

    @Test
    void shouldAddAuthorToDatabaseByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        AuthorDto authorDtoFromDB = authorService.findAuthorById(MockUtils.findIdOfAuthor());
        assertNotNull(authorDtoFromDB);
        assertEquals(AUTHOR_SURNAME, authorDtoFromDB.getSurname(), "Surname is not equals");
        assertEquals(AUTHOR_FIRST_NAME, authorDtoFromDB.getFirstName(), "First name is not equals");
        assertEquals(AUTHOR_SECOND_NAME, authorDtoFromDB.getSecondName(), "Second name is not equals");
        assertEquals(AUTHOR_COUNTRY, authorDtoFromDB.getCountry(), "Country is not equals");
    }

    @Test
    void shouldUpdateAuthorInDatabaseByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        int id = MockUtils.findIdOfAuthor();
        authorService.updateAuthor(id, UPDATE_AUTHOR_SURNAME, UPDATE_AUTHOR_FIRST_NAME, UPDATE_AUTHOR_SECOND_NAME, UPDATE_AUTHOR_COUNTRY, AUTHOR_PUBLISHERS_IDS);
        AuthorDto authorDtoFromDB = authorService.findAuthorById(id);
        assertNotNull(authorDtoFromDB);
        assertEquals(UPDATE_AUTHOR_SURNAME, authorDtoFromDB.getSurname(), "Surname is not equals");
        assertEquals(UPDATE_AUTHOR_FIRST_NAME, authorDtoFromDB.getFirstName(), "First name is not equals");
        assertEquals(UPDATE_AUTHOR_SECOND_NAME, authorDtoFromDB.getSecondName(), "Second name is not equals");
        assertEquals(UPDATE_AUTHOR_COUNTRY, authorDtoFromDB.getCountry(), "Country is not equals");
    }

    @Test
    void shouldDeleteAuthorInDatabaseByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        int id = MockUtils.findIdOfAuthor();
        authorService.deleteAuthor(id);
        AuthorDto authorDtoFromDB = authorService.findAuthorById(id);
        assertNull(authorDtoFromDB);
    }

    @Test
    void shouldFindAuthorByIdInDatabaseByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        AuthorDto authorDtoFromDB = authorService.findAuthorById(MockUtils.findIdOfAuthor());
        assertNotNull(authorDtoFromDB);
        assertEquals(AUTHOR_SURNAME, authorDtoFromDB.getSurname(), "Surname is not equals");
        assertEquals(AUTHOR_FIRST_NAME, authorDtoFromDB.getFirstName(), "First name is not equals");
        assertEquals(AUTHOR_SECOND_NAME, authorDtoFromDB.getSecondName(), "Second name is not equals");
        assertEquals(AUTHOR_COUNTRY, authorDtoFromDB.getCountry(), "Country is not equals");
    }

    @Test
    void shouldFindAuthorByNameInDatabaseByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        List<AuthorDto> authorDtoListFromDB = authorService.findAuthorByName(AUTHOR_SURNAME);
        String surname = null;
        for (AuthorDto authorDto : authorDtoListFromDB) {
            surname = authorDto.getSurname();
        }
        assertEquals(AUTHOR_SURNAME, surname, "Surname is not equals");
    }

    @Test
    void shouldFindAllAuthorsInDatabaseByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        AuthorDto authorDtoFromDB = authorService.findAuthorById(MockUtils.findIdOfAuthor());
        List<AuthorDto> authorDtoListFromDB = authorService.findAllAuthors();
        assertTrue(authorDtoListFromDB.contains(authorDtoFromDB));
    }

    @Test
    void shouldChooseAuthorsToBookByService() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        AuthorDto authorDtoFromDB_1 = authorService.findAuthorById(MockUtils.findIdOfAuthor());
        String id1 = String.valueOf(MockUtils.findIdOfAuthor());
        authorService.addAuthor(AUTHOR_SURNAME, UPDATE_AUTHOR_FIRST_NAME, UPDATE_AUTHOR_SECOND_NAME, UPDATE_AUTHOR_COUNTRY, AUTHOR_PUBLISHERS_IDS);
        AuthorDto authorDtoFromDB_2 = authorService.findAuthorById(MockUtils.findIdOfAuthor());
        String id2 = String.valueOf(MockUtils.findIdOfAuthor());
        Set<AuthorDto> authorDtoSetFromDB = authorService.chooseAuthorsToBook(new String[]{id1, id2});
        assertTrue(authorDtoSetFromDB.contains(authorDtoFromDB_1));
        assertTrue(authorDtoSetFromDB.contains(authorDtoFromDB_2));
    }

    @Test
    void shouldGetAuthorSetOfPublishers() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        String id = String.valueOf(MockUtils.findIdOfAuthor());
        Set<PublisherDto> publisherDtoSetFromDB = authorService.getAuthorSetOfPublishers(new String[]{id});
        assertNotNull(publisherDtoSetFromDB);
    }

    @Test
    void shouldSortAllAuthorsBySurname() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        String id1 = String.valueOf(MockUtils.findIdOfAuthor());
        AuthorDto authorDtoFromDB_1 = authorService.findAuthorById(MockUtils.findIdOfAuthor());
        authorService.addAuthor(UPDATE_AUTHOR_SURNAME, UPDATE_AUTHOR_FIRST_NAME, UPDATE_AUTHOR_SECOND_NAME, UPDATE_AUTHOR_COUNTRY, AUTHOR_PUBLISHERS_IDS);
        List<AuthorDto> authorDtoListFromDB = authorService.findAuthorByName(UPDATE_AUTHOR_SURNAME);
        String id2 = String.valueOf(0);
        for (AuthorDto authorDto : authorDtoListFromDB) {
            id2 = String.valueOf(authorDto.getId());
        }
        AuthorDto authorDtoFromDB_2 = authorService.findAuthorById(Integer.parseInt(id2));
        List<AuthorDto> authorDtoList = authorService.sortAllAuthorsBySurname(new String[]{id1, id2});
        assertEquals(authorDtoFromDB_2, authorDtoList.get(0));
        assertEquals(authorDtoFromDB_1, authorDtoList.get(1));
    }
}