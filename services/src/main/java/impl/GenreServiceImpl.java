package impl;

import dao.exceptions.DaoException;
import dao.impl.GenreDaoImpl;
import dao.interfaces.Dao;
import entities.Genre;
import exceptions.ServiceException;
import interfaces.GenreService;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:54
 */
public class GenreServiceImpl implements GenreService {

    /**
     * @param genre is created record
     * @throws ServiceException from work with services
     */
    @Override
    public void addGenre(Genre genre) throws ServiceException {
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            genreDao.create(genre);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param genre is updated record
     * @throws ServiceException from work with services
     */
    @Override
    public void updateGenre(Genre genre) throws ServiceException {
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            genreDao.update(genre);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of genre
     * @throws ServiceException from work with services
     */
    @Override
    public void deleteGenre(int id) throws ServiceException {
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            genreDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of genre
     * @return genre
     * @throws ServiceException from work with services
     */
    @Override
    public Genre findById(int id) throws ServiceException {
        Genre genre;
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            genre = genreDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return genre;
    }

    /**
     * @param name of genre
     * @return list of genres
     * @throws ServiceException from work with services
     */
    @Override
    public List<Genre> findGenreByName(String name) throws ServiceException {
        List<Genre> genres;
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            genres = genreDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return genres;
    }
}
