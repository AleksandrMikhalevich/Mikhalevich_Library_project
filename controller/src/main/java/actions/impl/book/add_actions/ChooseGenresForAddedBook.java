package actions.impl.book.add_actions;

import actions.interfaces.Command;
import actions.utils.PageManager;
import actions.utils.MessageManager;
import entities.Genre;
import exceptions.ServiceException;
import impl.GenreServiceImpl;
import interfaces.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-08 19:03
 */
public class ChooseGenresForAddedBook implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        try {
            GenreService genreService = new GenreServiceImpl();
            List<Genre> genreList = genreService.findAllGenres();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("addedGenres", genreList);
            page = PageManager.getProperty("page.chooseGenresForAddedBook");
        } catch (ServiceException e) {
            req.setAttribute("errorChooseGenres", MessageManager.getProperty("message.choose-genres-error"));
            page = PageManager.getProperty("page.book-add");
        }
        return page;
    }
}
