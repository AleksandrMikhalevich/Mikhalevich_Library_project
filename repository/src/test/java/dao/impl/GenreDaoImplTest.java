package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dao.impl.mocks.MockConstants.GENRE_NAME;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-02 13:25
 */
class GenreDaoImplTest {

    @Test
    void shouldCreateGenreInDatabase() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.create(genre);
        Genre genreFromDB = genreDao.findById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertEquals(genre, genreFromDB);
    }

    @Test
    void shouldFindGenreInDatabaseById() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.create(genre);
        Genre genreFromDB = genreDao.findById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertNotNull(genreFromDB.getId());
        Assertions.assertEquals(GENRE_NAME, genreFromDB.getName(), "Genre name is not equals");
    }

    @Test
    void shouldUpdateGenreInDatabase() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.create(genre);
        Genre genreToUpdate = Genre.builder()
                .id(genre.getId())
                .name("Action")
                .build();
        genreDao.update(genreToUpdate);
    }

    @Test
    void shouldDeleteGenreFromDatabase() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.create(genre);
        genreDao.delete(genre.getId());
    }

    @Test
    void shouldFindGenreByName() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.create(genre);
        List<Genre> genres = genreDao.findByName(GENRE_NAME);
        System.out.println(genres);
    }
}