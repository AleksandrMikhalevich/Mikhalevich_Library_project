package interfaces;

import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:40
 */
public interface AuthorService {

    /**
     * Method to add author to database
     *
     * @param surname is surname of created author
     * @param firstName is name of created author
     * @param secondName is second name of created author
     * @param country is country of created author
     * @param publishersIds are ids of added publishers to created author
     * @throws ServiceException from work with services
     */
    void addAuthor(String surname, String firstName, String secondName, String country, int[] publishersIds) throws ServiceException;

    /**
     * Method to update author by name in database
     *
     * @param id is id of updated author
     * @param surname is surname of updated author
     * @param firstName is name of updated author
     * @param secondName is second name of updated author
     * @param country is country of updated author
     * @param publishersIds are ids of added publishers to updated author
     * @throws ServiceException from work with services
     */
    void updateAuthor(int id, String surname, String firstName, String secondName, String country, int[] publishersIds) throws ServiceException;

    /**
     * Method to delete author by name in database
     *
     * @param id is identification number of author
     * @throws ServiceException from work with services
     */
    void deleteAuthor(int id) throws ServiceException;

    /**
     * Method to find author by identification number in database
     *
     * @param id is identification number of author
     * @return author
     * @throws ServiceException from work with services
     */
    Author findAuthorById(int id) throws ServiceException;

    /**
     * Method to find author/authors by name in database
     *
     * @param name of author
     * @return list of authors
     * @throws ServiceException from work with services
     */
    List<Author> findAuthorByName(String name) throws ServiceException;

    /**
     * Method to find all authors in database
     *
     * @return list of authors
     * @throws ServiceException from work with services
     */
    List<Author> findAllAuthors() throws ServiceException;

    /**
     * Method to find and add chosen authors to book
     *
     * @return set of chosen authors
     * @throws ServiceException from work with services
     */
    Set<Author> chooseAuthorsToBook(String[] authorIds) throws ServiceException;

    /**
     * Method to find all publishers connected to authors
     *
     * @return set of chosen publishers
     * @throws ServiceException from work with services
     */
    Set<Publisher> getAuthorSetOfPublishers(String[] authorIds) throws ServiceException;

    /**
     * Method to sort authors from database by surname
     *
     * @return list of sorted authors
     * @throws ServiceException from work with services
     */
    List<Author> sortAllAuthorsBySurname() throws ServiceException;
}
