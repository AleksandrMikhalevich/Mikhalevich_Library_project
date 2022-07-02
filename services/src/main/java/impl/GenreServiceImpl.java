package impl;

import dao.exceptions.DaoException;
import dao.impl.GenreDaoImpl;
import dao.interfaces.Dao;
import dto.GenreDto;
import entities.Genre;
import exceptions.ServiceException;
import interfaces.GenreService;
import mappers.EntityMapper;

import java.util.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:54
 */
public class GenreServiceImpl implements GenreService {

    /**
     * @param name is name of created genre
     * @param description is description of created genre
     * @throws ServiceException from work with services
     */
    @Override
    public void addGenre(String name, String description) throws ServiceException {
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            Genre genre = Genre.builder()
                    .name(name)
                    .description(description)
                    .build();
            genreDao.save(genre);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of updated genre
     * @param name is name of updated genre
     * @param description is description of updated genre
     * @throws ServiceException from work with services
     */
    @Override
    public void updateGenre(int id, String name, String description) throws ServiceException {
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            Genre genre = genreDao.findById(id);
            genre.setName(name);
            genre.setDescription(description);
            genreDao.update(genre);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of deleted genre
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
     * @param id is id of found genre
     * @return genre
     * @throws ServiceException from work with services
     */
    @Override
    public GenreDto findGenreById(int id) throws ServiceException {
        GenreDto genreDto;
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            Genre genre = genreDao.findById(id);
            genreDto = EntityMapper.getInstance().mapGenreToGenreDto(genre);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return genreDto;
    }

    /**
     * @param name of found genre
     * @return list of found genres
     * @throws ServiceException from work with services
     */
    @Override
    public List<GenreDto> findGenreByName(String name) throws ServiceException {
        List<GenreDto> genresDto;
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            List<Genre> genres = genreDao.findByName(name);
            genresDto = EntityMapper.getInstance().mapListGenreToListGenreDto(genres);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return genresDto;
    }

    /**
     * @return list of all found genres
     * @throws ServiceException from work with services
     */
    @Override
    public List<GenreDto> findAllGenres() throws ServiceException {
        List<GenreDto> genresDto;
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            List<Genre> genres = genreDao.findAll();
            genresDto = EntityMapper.getInstance().mapListGenreToListGenreDto(genres);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return genresDto;
    }

    /**
     * @param genresIds are ids of chosen genres
     * @return set of chosen genres
     * @throws ServiceException from work with services
     */
    @Override
    public Set<GenreDto> chooseGenresToBook(String[] genresIds) throws ServiceException {
        Dao<Genre> genreDao = new GenreDaoImpl();
        Set<GenreDto> genreSet = new HashSet<>();
        for (String genre_id : genresIds) {
            Genre genre;
            GenreDto genreDto;
            try {
                genre = genreDao.findById(Integer.parseInt(genre_id));
                genreDto = EntityMapper.getInstance().mapGenreToGenreDto(genre);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            genreSet.add(genreDto);
        }
        return genreSet;
    }

    /**
     * @return list of sorted genres
     * @throws ServiceException from work with services
     */
    @Override
    public List<GenreDto> sortAllGenresByName(String[] genresIds) throws ServiceException {
        List<GenreDto> genresDto = new ArrayList<>();
        try {
            Dao<Genre> genreDao = new GenreDaoImpl();
            GenreDto genreDto;
            for (String genre_id : genresIds) {
                Genre genre = genreDao.findById(Integer.parseInt(genre_id));
                genreDto = EntityMapper.getInstance().mapGenreToGenreDto(genre);
                genresDto.add(genreDto);
            }
            genresDto.sort(Comparator.comparing(GenreDto::getName));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return genresDto;
    }
}

