package impl;

import dto.AuthorDto;
import dto.PublisherDto;
import exceptions.ServiceException;
import interfaces.PublisherService;
import mocks.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static mocks.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 19:46
 */
class PublisherServiceImplTest {

    @BeforeEach
    void setUp() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(PUBLISHER_NAME, PUBLISHER_COUNTRY, PUBLISHER_CITY, PUBLISHER_STREET, PUBLISHER_HOUSE, PUBLISHER_ZIPCODE);
    }

    @Test
    void shouldAddPublisherInDatabaseByService() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        PublisherDto publisherDtoFromDB = publisherService.findPublisherById(MockUtils.findIdOfPublisher());
        assertNotNull(publisherDtoFromDB);
        assertEquals(PUBLISHER_NAME, publisherDtoFromDB.getName(), "Publisher name is not equals");
    }

    @Test
    void shouldUpdatePublisherInDatabaseByService() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        int id = MockUtils.findIdOfPublisher();
        publisherService.updatePublisher(id, UPDATE_PUBLISHER_NAME, PUBLISHER_COUNTRY, PUBLISHER_CITY, PUBLISHER_STREET, PUBLISHER_HOUSE, PUBLISHER_ZIPCODE);
        PublisherDto publisherDtoFromDB = publisherService.findPublisherById(id);
        assertNotNull(publisherDtoFromDB);
        assertEquals(UPDATE_PUBLISHER_NAME, publisherDtoFromDB.getName(), "Publisher name is not equals");
    }

    @Test
    void shouldDeletePublisherInDatabaseByService() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        int id = MockUtils.findIdOfPublisher();
        publisherService.deletePublisher(id);
        PublisherDto publisherDtoFromDB = publisherService.findPublisherById(id);
        assertNull(publisherDtoFromDB);
    }

    @Test
    void shouldFindPublisherByIdInDatabaseByService() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        PublisherDto publisherDtoFromDB = publisherService.findPublisherById(MockUtils.findIdOfPublisher());
        assertNotNull(publisherDtoFromDB);
        assertEquals(PUBLISHER_NAME, publisherDtoFromDB.getName(), "Publisher name is not equals");
    }

    @Test
    void shouldFindPublisherByNameInDatabaseByService() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        List<PublisherDto> publisherDtoListFromDB = publisherService.findPublisherByName(PUBLISHER_NAME);
        String name = null;
        for (PublisherDto publisherDto : publisherDtoListFromDB) {
            name = publisherDto.getName();
        }
        assertEquals(PUBLISHER_NAME, name, "Publisher name is not equals");
    }

    @Test
    void shouldFindAllPublishersInDatabaseByService() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        PublisherDto publisherDtoFromDB = publisherService.findPublisherById(MockUtils.findIdOfPublisher());
        List<PublisherDto> publisherDtoListFromDB = publisherService.findAllPublishers();
        assertTrue(publisherDtoListFromDB.contains(publisherDtoFromDB));
    }

    @Test
    void shouldGetPublisherSetOfAuthors() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        int id = MockUtils.findIdOfPublisher();
        Set<AuthorDto> authorDtoSetFromDB = publisherService.getPublisherSetOfAuthors(id);
        assertNotNull(authorDtoSetFromDB);
    }

    @Test
    void shouldChoosePublishersToAuthor() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        PublisherDto publisherDtoFromDB_1 = publisherService.findPublisherById(MockUtils.findIdOfPublisher());
        String id1 = String.valueOf(MockUtils.findIdOfPublisher());
        publisherService.addPublisher(PUBLISHER_NAME, UPDATE_PUBLISHER_COUNTRY, PUBLISHER_CITY, PUBLISHER_STREET, PUBLISHER_HOUSE, PUBLISHER_ZIPCODE);
        PublisherDto publisherDtoFromDB_2 = publisherService.findPublisherById(MockUtils.findIdOfPublisher());
        String id2 = String.valueOf(MockUtils.findIdOfPublisher());
        Set<PublisherDto> publisherDtoSetFromDB = publisherService.choosePublishersToAuthor(new String[]{id1, id2});
        assertTrue(publisherDtoSetFromDB.contains(publisherDtoFromDB_1));
        assertTrue(publisherDtoSetFromDB.contains(publisherDtoFromDB_2));
    }

    @Test
    void shouldSortAllPublishersByName() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        String id1 = String.valueOf(MockUtils.findIdOfPublisher());
        PublisherDto publisherDtoFromDB_1 = publisherService.findPublisherById(MockUtils.findIdOfPublisher());
        publisherService.addPublisher(UPDATE_PUBLISHER_NAME, UPDATE_PUBLISHER_COUNTRY, PUBLISHER_CITY, PUBLISHER_STREET, PUBLISHER_HOUSE, PUBLISHER_ZIPCODE);
        List<PublisherDto> publisherDtoListFromDB = publisherService.findPublisherByName(UPDATE_PUBLISHER_NAME);
        String id2 = String.valueOf(0);
        for (PublisherDto publisherDto : publisherDtoListFromDB) {
            id2 = String.valueOf(publisherDto.getId());
        }
        PublisherDto publisherDtoFromDB_2 = publisherService.findPublisherById(Integer.parseInt(id2));
        List<PublisherDto> publisherDtoList = publisherService.sortAllPublishersByName(new String[]{id1, id2});
        assertEquals(publisherDtoFromDB_2, publisherDtoList.get(0));
        assertEquals(publisherDtoFromDB_1, publisherDtoList.get(1));
    }
}