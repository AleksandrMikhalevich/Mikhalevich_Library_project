package actions.impl.genre.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Genre;
import exceptions.ServiceException;
import impl.GenreServiceImpl;
import interfaces.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static actions.impl.constants.Constants.SEARCH_GENRE_BY_NAME_QUERY;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 12:57
 */
public class FindGenreByName implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String name = req.getParameter(SEARCH_GENRE_BY_NAME_QUERY);
        try {
            GenreService genreService = new GenreServiceImpl();
            List<Genre> genreList = genreService.findGenreByName(name);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreList);
            req.setAttribute("searchGenreResults", MessageManager.getProperty("message.search-results"));
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorSearchGenreResults", MessageManager.getProperty("message.search-error"));
            page = PageManager.getProperty("page.genres");
        }
        return page;
    }
}
