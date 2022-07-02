package actions.impl.genre.find_actions;

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
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 13:02
 */
public class SortGenres implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String[] genres_ids = req.getParameterValues("genres_ids");
        if (genres_ids == null) {
            genres_ids = new String[0];
        }
        try {
            GenreService genreService = new GenreServiceImpl();
            List<GenreDto> genreListSortByName = genreService.sortAllGenresByName(genres_ids);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreListSortByName);
            httpSession.setAttribute("sortGenreResults", 0);
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorSortGenres", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.genres");
        }
        return page;
    }
}
