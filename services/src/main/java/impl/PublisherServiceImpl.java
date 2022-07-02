package impl;

import dao.exceptions.DaoException;
import dao.impl.GenreDaoImpl;
import dao.impl.PublisherDaoImpl;
import dao.interfaces.Dao;
import dto.AuthorDto;
import dto.GenreDto;
import dto.PublisherDto;
import entities.Address;
import entities.Genre;
import entities.Publisher;
import exceptions.ServiceException;
import interfaces.PublisherService;
import mappers.EntityMapper;

import java.util.*;
import java.util.stream.Collectors;

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
            publisherDto = EntityMapper.getInstance().mapPublisherToPublisherDto(publisher);
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
            publishersDto = EntityMapper.getInstance().mapListPublisherToListPublisherDto(publishers);
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
            publishersDto = EntityMapper.getInstance().mapListPublisherToListPublisherDto(publishers);
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
    public Set<AuthorDto> getPublisherSetOfAuthors(int publisherId) throws ServiceException {
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        Publisher publisher;
        Set<AuthorDto> publisherAuthors;
        try {
            publisher = publisherDao.findById(publisherId);
            publisherAuthors = publisher.getAuthors().stream()
                    .map(author -> EntityMapper.getInstance().mapAuthorToAuthorDto(author))
                    .collect(Collectors.toSet());
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
    public Set<PublisherDto> choosePublishersToAuthor(String[] publishersIds) throws ServiceException {
        Dao<Publisher> publisherDao = new PublisherDaoImpl();
        Set<PublisherDto> publisherSet = new HashSet<>();
        for (String publisher_id : publishersIds) {
            Publisher publisher;
            PublisherDto publisherDto;
            try {
                publisher = publisherDao.findById(Integer.parseInt(publisher_id));
                publisherDto = EntityMapper.getInstance().mapPublisherToPublisherDto(publisher);
            } catch (DaoException e) {
                throw new ServiceException();
            }
            publisherSet.add(publisherDto);
        }
        return publisherSet;
    }

    /**
     * @return list of sorted publishers
     * @throws ServiceException from work with services
     */
    @Override
    public List<PublisherDto> sortAllPublishersByName(String[] publishersIds) throws ServiceException {
        List<PublisherDto> publishersDto = new ArrayList<>();
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            PublisherDto publisherDto;
            for (String publisher_id : publishersIds) {
                Publisher publisher = publisherDao.findById(Integer.parseInt(publisher_id));
                publisherDto = EntityMapper.getInstance().mapPublisherToPublisherDto(publisher);
                publishersDto.add(publisherDto);
            }
            publishersDto.sort(Comparator.comparing(PublisherDto::getName));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publishersDto;
    }
}
