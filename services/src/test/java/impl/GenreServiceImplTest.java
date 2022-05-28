package impl;

import entities.Genre;
import exceptions.ServiceException;
import impl.mocks.MockConstants;
import impl.mocks.MockUtils;
import interfaces.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static impl.mocks.MockConstants.GENRE_NAME;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 18:08
 */
class GenreServiceImplTest {

    @Test
    void shouldCreateGenreInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        Genre genreFromDB = genreService.findGenreById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertEquals(genre, genreFromDB);
    }

    @Test
    void shouldUpdateGenreInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        Genre genreToUpdate = Genre.builder()
                .id(genre.getId())
                .name(MockConstants.UPDATE_GENRE_NAME)
                .build();
        genreService.updateGenre(genreToUpdate);
        Genre genreFromDB = genreService.findGenreById(genreToUpdate.getId());
        Assertions.assertEquals(genreToUpdate, genreFromDB);
    }

    @Test
    void shouldDeleteGenreInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        genreService.deleteGenre(genre.getId());
        Genre genreFromDB = genreService.findGenreById(genre.getId());
        Assertions.assertNull(genreFromDB);
    }

    @Test
    void shouldFindGenreByIdInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        Genre genreFromDB = genreService.findGenreById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertEquals(genre, genreFromDB);
        Assertions.assertEquals(GENRE_NAME, genreFromDB.getName(), "Genre name is not equals");
    }

    @Test
    void shouldFindGenreByNameInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        List<Genre> listFromDB = genreService.findGenreByName(GENRE_NAME);
        Assertions.assertTrue(listFromDB.contains(genre));
    }

    @Test
    void shouldFindAllGenresInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        Genre genre2 = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        genreService.addGenre(genre2);
        Genre genreFromDB = genreService.findGenreById(genre.getId());
        Genre genreFromDB2 = genreService.findGenreById(genre2.getId());
        List<Genre> listFromDB = genreService.findAllGenres();
        Assertions.assertTrue(listFromDB.contains(genreFromDB));
        Assertions.assertTrue(listFromDB.contains(genreFromDB2));
    }
}