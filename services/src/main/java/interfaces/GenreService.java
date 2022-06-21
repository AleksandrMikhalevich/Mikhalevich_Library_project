package interfaces;

import entities.Genre;
import exceptions.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:50
 */
public interface GenreService {

    /**
     * Method to add genre to database
     *
     * @param name is name of created genre
     * @param description is description of created genre
     * @throws ServiceException from work with services
     */
    void addGenre(String name, String description) throws ServiceException;

    /**
     * Method to update genre by name in database
     *
     * @param id is id of updated genre
     * @param name is name of updated genre
     * @param description is description of updated genre
     * @throws ServiceException from work with services
     */
    void updateGenre(int id, String name, String description) throws ServiceException;

    /**
     * Method to delete genre by name in database
     *
     * @param id is identification number of genre
     * @throws ServiceException from work with services
     */
    void deleteGenre(int id) throws ServiceException;

    /**
     * Method to find genre by identification number in database
     *
     * @param id is identification number of genre
     * @return genre
     * @throws ServiceException from work with services
     */
    Genre findGenreById(int id) throws ServiceException;

    /**
     * Method to find genre/genres by name in database
     *
     * @param name of genre
     * @return list of genres
     * @throws ServiceException from work with services
     */
    List<Genre> findGenreByName(String name) throws ServiceException;

    /**
     * Method to find all genres in database
     *
     * @return list of genres
     * @throws ServiceException from work with services
     */
    List<Genre> findAllGenres() throws ServiceException;

    /**
     * Method to find and add chosen genres to book
     *
     * @return set of chosen genres
     * @throws ServiceException from work with services
     */
    Set<Genre> chooseGenresToBook(String[] genre_ids) throws ServiceException;

    /**
     * Method to sort genres from database by name
     *
     * @return list of sorted genres
     * @throws ServiceException from work with services
     */
    List<Genre> sortAllGenresByName() throws ServiceException;
}
