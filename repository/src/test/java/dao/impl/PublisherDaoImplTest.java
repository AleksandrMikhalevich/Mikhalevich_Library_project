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
        publisherDao.create(publisher);
        Publisher publisherFromDB = publisherDao.findById(publisher.getId());
        Assertions.assertNotNull(publisherFromDB);
        Assertions.assertEquals(publisher, publisherFromDB);
    }

    @Test
    void shouldFindPublisherInDatabaseById() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.create(publisher);
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
        publisherDao.create(publisher);
        Publisher publisherToUpdate = Publisher.builder()
                .id(publisher.getId())
                .name("Aaa")
                .build();
        publisherDao.update(publisherToUpdate);
    }

    @Test
    void shouldDeletePublisherFromDatabase() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.create(publisher);
        publisherDao.delete(publisher.getId());
    }

    @Test
    void shouldFindPublisherByName() throws DaoException {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        publisherDao.create(publisher);
        List<Publisher> publishers = publisherDao.findByName(PUBLISHER_NAME);
        System.out.println(publishers);
    }
}