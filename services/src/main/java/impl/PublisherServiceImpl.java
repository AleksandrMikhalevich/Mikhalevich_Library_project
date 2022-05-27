package impl;

import dao.exceptions.DaoException;
import dao.impl.PublisherDaoImpl;
import dao.interfaces.Dao;
import entities.Publisher;
import exceptions.ServiceException;
import interfaces.PublisherService;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:09
 */
public class PublisherServiceImpl implements PublisherService {

    /**
     * @param publisher is created record
     * @throws ServiceException from work with services
     */
    @Override
    public void addPublisher(Publisher publisher) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            publisherDao.save(publisher);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param publisher is updated record
     * @throws ServiceException from work with services
     */
    @Override
    public void updatePublisher(Publisher publisher) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            publisherDao.update(publisher);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of publisher
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
     * @param id is identification number of publisher
     * @return publisher
     * @throws ServiceException from work with services
     */
    @Override
    public Publisher findById(int id) throws ServiceException {
        Publisher publisher;
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            publisher = publisherDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publisher;
    }

    /**
     * @param name of publisher
     * @return list of publishers
     * @throws ServiceException from work with services
     */
    @Override
    public List<Publisher> findPublisherByName(String name) throws ServiceException {
        List<Publisher> publishers;
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            publishers = publisherDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return publishers;
    }
}
