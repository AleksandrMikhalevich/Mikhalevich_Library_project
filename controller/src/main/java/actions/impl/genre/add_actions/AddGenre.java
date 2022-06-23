package actions.impl.genre.add_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.GenreDto;
import entities.Author;
import entities.Genre;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.GenreServiceImpl;
import interfaces.AuthorService;
import interfaces.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 12:55
 */
public class AddGenre implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String name = req.getParameter(GENRE_NAME);
        String description = req.getParameter(GENRE_DESCRIPTION);
        try {
            GenreService genreService = new GenreServiceImpl();
            genreService.addGenre(name, description);
            List<GenreDto> genreList = genreService.findAllGenres();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreList);
            req.setAttribute("successAddGenre", MessageManager.getProperty("message.genre-add"));
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorAddGenre", MessageManager.getProperty("message.genre-add-error"));
            page = PageManager.getProperty("page.genres");
        }
        return page;
    }
}
