package interfaces;

import entities.Publisher;
import exceptions.ServiceException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:03
 */
public interface PublisherService {

    /**
     * Method to add publisher to database
     * @param publisher is created record
     * @throws ServiceException from work with services
     */
    void addPublisher(Publisher publisher) throws ServiceException;

    /**
     * Method to update publisher by name in database
     * @param publisher is updated record
     * @throws ServiceException from work with services
     */
    void updatePublisher(Publisher publisher) throws ServiceException;

    /**
     * Method to delete publisher by name in database
     * @param id is identification number of publisher
     * @throws ServiceException from work with services
     */
    void deletePublisher(int id) throws ServiceException;

    /**
     * Method to find publisher by identification number in database
     * @param id is identification number of publisher
     * @return publisher
     * @throws ServiceException from work with services
     */
    Publisher findPublisherById(int id) throws ServiceException;

    /**
     * Method to find publisher/publishers by name in database
     * @param name of publisher
     * @return list of publishers
     * @throws ServiceException from work with services
     */
    List<Publisher> findPublisherByName(String name) throws ServiceException;

    /**
     * Method to find all publishers in database
     * @return list of publishers
     * @throws ServiceException from work with services
     */
    List<Publisher> findAllPublishers() throws ServiceException;
}
