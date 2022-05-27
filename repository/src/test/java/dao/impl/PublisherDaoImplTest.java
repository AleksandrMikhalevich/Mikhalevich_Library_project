package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Address;
import entities.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dao.impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-02 13:37
 */
class PublisherDaoImplTest {

    @Test
    void shouldCreatePublisherInDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Publisher publisherFromDB = publisherDao.findById(publisher.getId());
        Assertions.assertNotNull(publisherFromDB);
        Assertions.assertEquals(publisher, publisherFromDB);
    }

    @Test
    void shouldFindPublisherInDatabaseById() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Publisher publisherFromDB = publisherDao.findById(publisher.getId());
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
    void shouldUpdatePublisherInDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        Publisher publisherToUpdate = Publisher.builder()
                .id(publisher.getId())
                .name("Aaa")
                .build();
        publisherDao.update(publisherToUpdate);
        Publisher publisherFromDB = publisherDao.findById(publisherToUpdate.getId());
        Assertions.assertEquals(publisherToUpdate, publisherFromDB);
    }

    @Test
    void shouldDeletePublisherFromDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        publisherDao.delete(publisher.getId());
        Publisher publisherFromDB = publisherDao.findById(publisher.getId());
        Assertions.assertNull(publisherFromDB);
    }

    @Test
    void shouldFindPublisherInDatabaseByName() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        List<Publisher> listFromDB = publisherDao.findByName(PUBLISHER_NAME);
        Assertions.assertTrue(listFromDB.contains(publisher));
    }

    @Test
    void shouldFindAllPublishersInDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Publisher publisher2 = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.save(publisher);
        publisherDao.save(publisher2);
        Publisher publisherFromDB = publisherDao.findById(publisher.getId());
        Publisher publisherFromDB2 = publisherDao.findById(publisher2.getId());
        List<Publisher> listFromDB = publisherDao.findAll();
        Assertions.assertTrue(listFromDB.contains(publisherFromDB));
        Assertions.assertTrue(listFromDB.contains(publisherFromDB2));
    }
}