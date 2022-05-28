package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockConstants;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dao.impl.mocks.MockConstants.*;
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
        genreDao.save(genre);
        Genre genreFromDB = genreDao.findById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertEquals(genre, genreFromDB);
    }

    @Test
    void shouldFindGenreInDatabaseById() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.save(genre);
        Genre genreFromDB = genreDao.findById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertNotNull(genreFromDB.getId());
        Assertions.assertEquals(GENRE_NAME, genreFromDB.getName(), "Genre name is not equals");
    }

    @Test
    void shouldUpdateGenreInDatabase() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.save(genre);
        Genre genreToUpdate = Genre.builder()
                .id(genre.getId())
                .name(UPDATE_GENRE_NAME)
                .build();
        genreDao.update(genreToUpdate);
        Genre genreFromDB = genreDao.findById(genreToUpdate.getId());
        Assertions.assertEquals(genreToUpdate, genreFromDB);
    }

    @Test
    void shouldDeleteGenreFromDatabase() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.save(genre);
        genreDao.delete(genre.getId());
        Genre genreFromDB = genreDao.findById(genre.getId());
        Assertions.assertNull(genreFromDB);
    }

    @Test
    void shouldFindGenreInDatabaseByName() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.save(genre);
        List<Genre> listFromDB = genreDao.findByName(GENRE_NAME);
        Assertions.assertTrue(listFromDB.contains(genre));
    }

    @Test
    void shouldFindAllGenresInDatabase() throws DaoException {
        Genre genre = MockUtils.createGenre();
        Genre genre2 = MockUtils.createGenre();
        Dao<Genre> genreDao = new GenreDaoImpl();
        genreDao.save(genre);
        genreDao.save(genre2);
        Genre genreFromDB = genreDao.findById(genre.getId());
        Genre genreFromDB2 = genreDao.findById(genre2.getId());
        List<Genre> listFromDB = genreDao.findAll();
        Assertions.assertTrue(listFromDB.contains(genreFromDB));
        Assertions.assertTrue(listFromDB.contains(genreFromDB2));
    }
}