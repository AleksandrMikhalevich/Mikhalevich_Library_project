package actions.impl.genre.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
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
        try {
            GenreService genreService = new GenreServiceImpl();
            List<Genre> genreListSortByName = genreService.sortAllGenresByName();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreListSortByName);
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorSortGenres", MessageManager.getProperty("message.sort-error"));
            page = PageManager.getProperty("page.genres");
        }
        return page;
    }
}
