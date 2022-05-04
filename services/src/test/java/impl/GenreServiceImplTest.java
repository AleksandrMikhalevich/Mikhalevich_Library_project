package impl;

import entities.Genre;
import exceptions.ServiceException;
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
        Genre genreFromDB = genreService.findById(genre.getId());
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
                .name("Action")
                .build();
        genreService.updateGenre(genreToUpdate);
    }

    @Test
    void shouldDeleteGenreInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        genreService.deleteGenre(genre.getId());
    }

    @Test
    void shouldFindGenreByIdInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        Genre genreFromDB = genreService.findById(genre.getId());
        Assertions.assertNotNull(genreFromDB);
        Assertions.assertEquals(genre, genreFromDB);
        Assertions.assertEquals(GENRE_NAME, genreFromDB.getName(), "Genre name is not equals");
    }

    @Test
    void shouldFindGenreByNameInDatabaseByService() throws ServiceException {
        Genre genre = MockUtils.createGenre();
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(genre);
        List<Genre> genres = genreService.findGenreByName(GENRE_NAME);
        System.out.println(genres);
    }
}