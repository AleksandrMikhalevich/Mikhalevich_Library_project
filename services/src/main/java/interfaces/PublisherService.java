package interfaces;

import dto.PublisherDto;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:03
 */
public interface PublisherService {

    /**
     * Method to add publisher to database
     *
     * @param name is name of created publisher
     * @param country is country of created publisher
     * @param city is city of created publisher
     * @param street is street of created publisher
     * @param house is house of created publisher
     * @param zipcode is zipcode of created publisher
     * @throws ServiceException from work with services
     */
    void addPublisher(String name, String country, String city, String street, String house, String zipcode) throws ServiceException;

    /**
     * Method to update publisher by id in database
     *
     * @param id is id of updated publisher
     * @param name is name of updated publisher
     * @param country is country of updated publisher
     * @param city is city of updated publisher
     * @param street is street of updated publisher
     * @param house is house of updated publisher
     * @param zipcode is zipcode of updated publisher
     * @throws ServiceException from work with services
     */
    void updatePublisher(int id, String name, String country, String city, String street, String house, String zipcode) throws ServiceException;

    /**
     * Method to delete publisher by name in database
     *
     * @param id is identification number of publisher
     * @throws ServiceException from work with services
     */
    void deletePublisher(int id) throws ServiceException;

    /**
     * Method to find publisher by id in database
     *
     * @param id is id of deleted publisher
     * @return publisher
     * @throws ServiceException from work with services
     */
    PublisherDto findPublisherById(int id) throws ServiceException;

    /**
     * Method to find publisher/publishers by name in database
     *
     * @param name of found publisher
     * @return list of found publishers
     * @throws ServiceException from work with services
     */
    List<PublisherDto> findPublisherByName(String name) throws ServiceException;

    /**
     * Method to find all publishers in database
     *
     * @return list of all found publishers
     * @throws ServiceException from work with services
     */
    List<PublisherDto> findAllPublishers() throws ServiceException;

    /**
     * Method to find all authors connected to publisher
     *
     * @param publisherId is id of needed publisher
     * @return set of chosen authors
     * @throws ServiceException from work with services
     */
    Set<Author> getPublisherSetOfAuthors(int publisherId) throws ServiceException;

    /**
     * Method to find and add chosen publishers to author
     *
     * @return set of chosen publishers
     * @throws ServiceException from work with services
     */
    Set<Publisher> choosePublishersToAuthor(String[] publishersIds) throws ServiceException;

    /**
     * Method to sort publishers from database by name
     *
     * @return list of sorted publishers
     * @throws ServiceException from work with services
     */
    List<PublisherDto> sortAllPublishersByName() throws ServiceException;
}
