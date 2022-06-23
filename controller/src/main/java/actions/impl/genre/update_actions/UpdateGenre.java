package actions.impl.genre.update_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.GenreDto;
import entities.Genre;
import exceptions.ServiceException;
import impl.GenreServiceImpl;
import interfaces.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 12:56
 */
public class UpdateGenre implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(GENRE_ID));
        String name = req.getParameter(GENRE_NAME);
        String description = req.getParameter(GENRE_DESCRIPTION);
        try {
            GenreService genreService = new GenreServiceImpl();
            genreService.updateGenre(id, name, description);
            List<GenreDto> genreList = genreService.findAllGenres();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreList);
            req.setAttribute("successUpdateGenre", MessageManager.getProperty("message.genre-update"));
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorUpdateGenre", MessageManager.getProperty("message.genre-update-error"));
            page = PageManager.getProperty("page.genres");
        }
        return page;
    }
}
