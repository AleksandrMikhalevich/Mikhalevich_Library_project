package actions.impl.book.update_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import dto.GenreDto;
import entities.Genre;
import exceptions.ServiceException;
import impl.GenreServiceImpl;
import interfaces.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static actions.impl.constants.Constants.GENRE_IDS;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-08 16:22
 */
public class UpdateChosenGenresForExistingBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String[] genre_ids = req.getParameterValues(GENRE_IDS);
        if (genre_ids == null) {
            genre_ids = new String[0];
        }
        try {
            GenreService genreService = new GenreServiceImpl();
            Set<GenreDto> genreSet = genreService.chooseGenresToBook(genre_ids);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("chosenGenres", genreSet);
            page = PageManager.getProperty("page.book-update");
        } catch (ServiceException e) {
            req.setAttribute("errorAddChosenGenres", MessageManager.getProperty("message.chosen-genres-add-error"));
            page = PageManager.getProperty("page.book-update");
        }
        return page;
    }
}
