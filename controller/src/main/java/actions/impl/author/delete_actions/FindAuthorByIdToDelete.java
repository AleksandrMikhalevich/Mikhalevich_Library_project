package actions.impl.author.delete_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Author;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import interfaces.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static actions.impl.constants.Constants.AUTHOR_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:15
 */
public class FindAuthorByIdToDelete implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int id = Integer.parseInt(req.getParameter(AUTHOR_ID));
        try {
            AuthorService authorService = new AuthorServiceImpl();
            Author author = authorService.findAuthorById(id);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorToDelete", author);
            page = PageManager.getProperty("page.author-delete");
        } catch (ServiceException e) {
            req.setAttribute("errorFindAuthorToDelete", MessageManager.getProperty("message.author-find-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
