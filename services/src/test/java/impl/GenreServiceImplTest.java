package impl;

import dto.GenreDto;
import exceptions.ServiceException;
import mocks.MockUtils;
import interfaces.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static mocks.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 18:08
 */
class GenreServiceImplTest {

    @BeforeEach
    void setUp() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        genreService.addGenre(GENRE_NAME, GENRE_DESCRIPTION);
    }

    @Test
    void shouldAddGenreInDatabaseByService() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        GenreDto genreDtoFromDB = genreService.findGenreById(MockUtils.findIdOfGenre());
        assertNotNull(genreDtoFromDB);
        assertEquals(GENRE_NAME, genreDtoFromDB.getName(), "Name is not equals");
        assertEquals(GENRE_DESCRIPTION, genreDtoFromDB.getDescription(), "Description is not equals");
    }

    @Test
    void shouldUpdateGenreInDatabaseByService() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        int id = MockUtils.findIdOfGenre();
        genreService.updateGenre(id, UPDATE_GENRE_NAME, UPDATE_GENRE_DESCRIPTION);
        GenreDto genreDtoFromDB = genreService.findGenreById(id);
        assertNotNull(genreDtoFromDB);
        assertEquals(UPDATE_GENRE_NAME, genreDtoFromDB.getName(), "Name is not equals");
        assertEquals(UPDATE_GENRE_DESCRIPTION, genreDtoFromDB.getDescription(), "Description is not equals");
    }

    @Test
    void shouldDeleteGenreInDatabaseByService() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        int id = MockUtils.findIdOfGenre();
        genreService.deleteGenre(id);
        GenreDto genreDtoFromDB = genreService.findGenreById(id);
        assertNull(genreDtoFromDB);
    }

    @Test
    void shouldFindGenreByIdInDatabaseByService() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        GenreDto genreDtoFromDB = genreService.findGenreById(MockUtils.findIdOfGenre());
        assertNotNull(genreDtoFromDB);
        assertEquals(GENRE_NAME, genreDtoFromDB.getName(), "Name is not equals");
        assertEquals(GENRE_DESCRIPTION, genreDtoFromDB.getDescription(), "Description is not equals");
    }

    @Test
    void shouldFindGenreByNameInDatabaseByService() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        List<GenreDto> genreDtoListFromDB = genreService.findGenreByName(GENRE_NAME);
        String name = null;
        for (GenreDto genreDto : genreDtoListFromDB) {
            name = genreDto.getName();
        }
        assertEquals(GENRE_NAME, name, "Name is not equals");
    }

    @Test
    void shouldFindAllGenresInDatabaseByService() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        GenreDto genreDtoFromDB = genreService.findGenreById(MockUtils.findIdOfGenre());
        List<GenreDto> genreDtoListFromDB = genreService.findAllGenres();
        assertTrue(genreDtoListFromDB.contains(genreDtoFromDB));
    }

    @Test
    void shouldChooseGenresToBook() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        GenreDto genreDtoFromDB_1 = genreService.findGenreById(MockUtils.findIdOfGenre());
        String id1 = String.valueOf(MockUtils.findIdOfGenre());
        genreService.addGenre(GENRE_NAME, UPDATE_GENRE_DESCRIPTION);
        GenreDto genreDtoFromDB_2 = genreService.findGenreById(MockUtils.findIdOfGenre());
        String id2 = String.valueOf(MockUtils.findIdOfGenre());
        Set<GenreDto> genreDtoSetFromDB = genreService.chooseGenresToBook(new String[]{id1, id2});
        assertTrue(genreDtoSetFromDB.contains(genreDtoFromDB_1));
        assertTrue(genreDtoSetFromDB.contains(genreDtoFromDB_2));
    }

    @Test
    void shouldSortAllGenresByName() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        String id1 = String.valueOf(MockUtils.findIdOfGenre());
        GenreDto genreDtoFromDB_1 = genreService.findGenreById(MockUtils.findIdOfGenre());
        genreService.addGenre(UPDATE_GENRE_NAME, UPDATE_GENRE_DESCRIPTION);
        List<GenreDto> genreDtoListFromDB = genreService.findGenreByName(UPDATE_GENRE_NAME);
        String id2 = String.valueOf(0);
        for (GenreDto genreDto : genreDtoListFromDB) {
            id2 = String.valueOf(genreDto.getId());
        }
        GenreDto genreDtoFromDB_2 = genreService.findGenreById(Integer.parseInt(id2));
        List<GenreDto> genreDtoList = genreService.sortAllGenresByName(new String[]{id1, id2});
        assertEquals(genreDtoFromDB_2, genreDtoList.get(0));
        assertEquals(genreDtoFromDB_1, genreDtoList.get(1));
    }
}