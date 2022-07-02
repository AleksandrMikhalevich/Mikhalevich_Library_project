package actions.impl.genre.find_actions;

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

/**
 * @author Alex Mikhalevich
 * @created 2022-06-16 12:56
 */
public class FindAllGenres implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            GenreService genreService = new GenreServiceImpl();
            List<GenreDto> genreList = genreService.findAllGenres();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("genreList", genreList);
            httpSession.removeAttribute("sortGenreResults");
            httpSession.removeAttribute("searchGenreResults");
            page = PageManager.getProperty("page.genres");
        } catch (ServiceException e) {
            req.setAttribute("errorGenreList", MessageManager.getProperty("message.genre-list-error"));
            page = PageManager.getProperty("page.main");
        }
        return page;
    }
}
