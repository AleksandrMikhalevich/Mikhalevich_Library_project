package interfaces;

import entities.Genre;
import exceptions.ServiceException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:50
 */
public interface GenreService {

    /**
     * Method to add genre to database
     * @param genre is created record
     * @throws ServiceException from work with services
     */
    void addGenre(Genre genre) throws ServiceException;

    /**
     * Method to update genre by name in database
     * @param genre is updated record
     * @throws ServiceException from work with services
     */
    void updateGenre(Genre genre) throws ServiceException;

    /**
     * Method to delete genre by name in database
     * @param id is identification number of genre
     * @throws ServiceException from work with services
     */
    void deleteGenre(int id) throws ServiceException;

    /**
     * Method to find genre by identification number in database
     * @param id is identification number of genre
     * @return genre
     * @throws ServiceException from work with services
     */
    Genre findById(int id) throws ServiceException;

    /**
     * Method to find genre/genres by name in database
     * @param name of genre
     * @return list of genres
     * @throws ServiceException from work with services
     */
    List<Genre> findGenreByName(String name) throws ServiceException;

}
