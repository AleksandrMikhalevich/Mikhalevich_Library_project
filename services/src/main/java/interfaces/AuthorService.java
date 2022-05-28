package interfaces;

import entities.Author;
import exceptions.ServiceException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:40
 */
public interface AuthorService {

    /**
     * Method to add author to database
     * @param author is created record
     * @throws ServiceException from work with services
     */
    void addAuthor(Author author) throws ServiceException;

    /**
     * Method to update author by name in database
     * @param author is updated record
     * @throws ServiceException from work with services
     */
    void updateAuthor(Author author) throws ServiceException;

    /**
     * Method to delete author by name in database
     * @param id is identification number of author
     * @throws ServiceException from work with services
     */
    void deleteAuthor(int id) throws ServiceException;

    /**
     * Method to find author by identification number in database
     * @param id is identification number of author
     * @return author
     * @throws ServiceException from work with services
     */
    Author findAuthorById(int id) throws ServiceException;

    /**
     * Method to find author/authors by name in database
     * @param name of author
     * @return list of authors
     * @throws ServiceException from work with services
     */
    List<Author> findAuthorByName(String name) throws ServiceException;

    /**
     * Method to find all authors in database
     * @return list of authors
     * @throws ServiceException from work with services
     */
    List<Author> findAllAuthors() throws ServiceException;
}
