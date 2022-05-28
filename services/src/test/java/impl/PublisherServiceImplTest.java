package impl;

import entities.Address;
import entities.Publisher;
import exceptions.ServiceException;
import impl.mocks.MockUtils;
import interfaces.PublisherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 19:46
 */
class PublisherServiceImplTest {

    @Test
    void shouldCreatePublisherInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        Publisher publisherFromDB = publisherService.findPublisherById(publisher.getId());
        Assertions.assertNotNull(publisherFromDB);
        Assertions.assertEquals(publisher, publisherFromDB);
    }

    @Test
    void shouldUpdatePublisherInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        Publisher publisherToUpdate = Publisher.builder()
                .id(publisher.getId())
                .name(UPDATE_PUBLISHER_NAME)
                .build();
        publisherService.updatePublisher(publisherToUpdate);
        Publisher publisherFromDB = publisherService.findPublisherById(publisherToUpdate.getId());
        Assertions.assertEquals(publisherToUpdate, publisherFromDB);
    }

    @Test
    void shouldDeletePublisherInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        publisherService.deletePublisher(publisher.getId());
        Publisher publisherFromDB = publisherService.findPublisherById(publisher.getId());
        Assertions.assertNull(publisherFromDB);
    }

    @Test
    void shouldFindPublisherByIdInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        Publisher publisherFromDB = publisherService.findPublisherById(publisher.getId());
        Assertions.assertNotNull(publisherFromDB);
        Assertions.assertNotNull(publisherFromDB.getId());
        Assertions.assertEquals(PUBLISHER_NAME, publisherFromDB.getName(), "Publisher name is not equals");
        Address addressToCompare = Address.builder()
                .country(PUBLISHER_COUNTRY)
                .city(CITY)
                .street(STREET)
                .house(HOUSE)
                .zipcode(ZIPCODE)
                .build();
        Assertions.assertEquals(addressToCompare, publisherFromDB.getAddress());
    }

    @Test
    void shouldFindPublisherByNameInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        List<Publisher> listFromDB = publisherService.findPublisherByName(PUBLISHER_NAME);
        Assertions.assertTrue(listFromDB.contains(publisher));
    }

    @Test
    void shouldFindAllPublishersInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Publisher publisher2 = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        publisherService.addPublisher(publisher2);
        Publisher publisherFromDB = publisherService.findPublisherById(publisher.getId());
        Publisher publisherFromDB2 = publisherService.findPublisherById(publisher2.getId());
        List<Publisher> listFromDB = publisherService.findAllPublishers();
        Assertions.assertTrue(listFromDB.contains(publisherFromDB));
        Assertions.assertTrue(listFromDB.contains(publisherFromDB2));
    }
}