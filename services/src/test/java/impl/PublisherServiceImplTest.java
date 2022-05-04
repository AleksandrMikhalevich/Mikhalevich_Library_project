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
import static org.junit.jupiter.api.Assertions.*;

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
        Publisher publisherFromDB = publisherService.findById(publisher.getId());
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
                .name("Aaa")
                .build();
        publisherService.updatePublisher(publisherToUpdate);
    }

    @Test
    void shouldDeletePublisherInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        publisherService.deletePublisher(publisher.getId());

    }

    @Test
    void shouldFindPublisherByIdInDatabaseByService() throws ServiceException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherService publisherService = new PublisherServiceImpl();
        publisherService.addPublisher(publisher);
        Publisher publisherFromDB = publisherService.findById(publisher.getId());
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
        List<Publisher> publishers = publisherService.findPublisherByName(PUBLISHER_NAME);
        System.out.println(publishers);
    }
}