package impl;

import dao.exceptions.DaoException;
import dao.impl.PublisherDaoImpl;
import dao.interfaces.Dao;
import dto.PublisherDto;
import entities.Address;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;
import interfaces.PublisherService;
import mappers.EntityMapper;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:09
 */
public class PublisherServiceImpl implements PublisherService {

    /**
     * @param name is name of created publisher
     * @param country is country of created publisher
     * @param city is city of created publisher
     * @param street is street of created publisher
     * @param house is house of created publisher
     * @param zipcode is zipcode of created publisher
     * @throws ServiceException from work with services
     */
    @Override
    public void addPublisher(String name, String country, String city, String street, String house, String zipcode) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Publisher publisher = Publisher.builder()
                    .name(name)
                    .address(new Address(country, city, street, house, zipcode))
                    .build();
            publisherDao.save(publisher);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of updated publisher
     * @param name is name of updated publisher
     * @param country is country of updated publisher
     * @param city is city of updated publisher
     * @param street is street of updated publisher
     * @param house is house of updated publisher
     * @param zipcode is zipcode of updated publisher
     * @throws ServiceException from work with services
     */
    @Override
    public void updatePublisher(int id, String name, String country, String city, String street, String house, String zipcode) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Publisher publisher = publisherDao.findById(id);
            publisher.setName(name);
            publisher.setAddress(new Address(country, city, street, house, zipcode));
            publisherDao.update(publisher);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of deleted publisher
     * @throws ServiceException from work with services
     */
    @Override
    public void deletePublisher(int id) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            publisherDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of found publisher
     * @return publisher
     * @throws ServiceException from work with services
     */
    @Override
    public PublisherDto findPublisherById(int id) throws ServiceException {
        PublisherDto publisherDto;
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Publisher publisher = publisherDao.findById(id);
            publisherDto = EntityMapper.getInstance().mapPublisherToDto(publisher);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publisherDto;
    }

    /**
     * @param name of found publisher
     * @return list of found publishers
     * @throws ServiceException from work with services
     */
    @Override
    public List<PublisherDto> findPublisherByName(String name) throws ServiceException {
        List<PublisherDto> publishersDto;
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            List<Publisher> publishers = publisherDao.findByName(name);
            publishersDto = EntityMapper.getInstance().mapListToPublisherDto(publishers);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publishersDto;
    }

    /**
     * @return list of all found publishers
     * @throws ServiceException from work with services
     */
    @Override
    public List<PublisherDto> findAllPublishers() throws ServiceException {
        List<PublisherDto> publishersDto;
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            List<Publisher> publishers = publisherDao.findAll();
            publishersDto = EntityMapper.getInstance().mapListToPublisherDto(publishers);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publishersDto;
    }

    /**
     * @param publisherId is id of needed publisher
     * @return set of chosen authors
     * @throws ServiceException from work with services
     */
    @Override
    public Set<Author> getPublisherSetOfAuthors(int publisherId) throws ServiceException {
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        Set<Author> publisherAuthors;
        Publisher publisher;
        try {
            publisher = publisherDao.findById(publisherId);
            publisherAuthors = new HashSet<>(publisher.getAuthors());
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publisherAuthors;
    }

    /**
     * @param publishersIds are ids of chosen publishers
     * @return set of chosen publishers
     * @throws ServiceException from work with services
     */
    @Override
    public Set<Publisher> choosePublishersToAuthor(String[] publishersIds) throws ServiceException {
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        Set<Publisher> publisherSet = new HashSet<>();
        for (String publisher_id : publishersIds) {
            Publisher publisher;
            try {
                publisher = publisherDao.findById(Integer.parseInt(publisher_id));
            } catch (DaoException e) {
                throw new ServiceException();
            }
            publisherSet.add(publisher);
        }
        return publisherSet;
    }

    /**
     * @return list of sorted publishers
     * @throws ServiceException from work with services
     */
    @Override
    public List<PublisherDto> sortAllPublishersByName() throws ServiceException {
        List<PublisherDto> publishersDto;
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            List<Publisher> publishers = publisherDao.findAll();
            publishers.sort(Comparator.comparing(Publisher::getName));
            publishersDto = EntityMapper.getInstance().mapListToPublisherDto(publishers);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publishersDto;
    }
}
