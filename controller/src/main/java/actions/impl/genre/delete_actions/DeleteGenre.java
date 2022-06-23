package actions.impl.genre.delete_actions;

import actions.impl.constants.Constants;
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
import static actions.impl.constants.Constants.GENRE_DESCRIPTION;
import static actions.impl.constants.Constants.GENRE_NAME;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 12:56
 */
public class DeleteGenre implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(GENRE_ID));
        try {
            GenreService genreService = new GenreServiceImpl();
            genreService.deleteGenre(id);
            List<GenreDto> genreList = genreService.findAllGenres();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreList);
            req.setAttribute("successDeleteGenre", MessageManager.getProperty("message.genre-delete"));
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorDeleteGenre", MessageManager.getProperty("message.genre-delete-error"));
            page = PageManager.getProperty("page.genres");
        }
        return page;
    }
}
