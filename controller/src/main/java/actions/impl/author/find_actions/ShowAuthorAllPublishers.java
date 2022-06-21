package actions.impl.author.find_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import entities.Author;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import interfaces.AuthorService;

import javax.servlet.http.HttpServletRequest;

import static actions.impl.constants.Constants.AUTHOR_ID;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:45
 */
public class ShowAuthorAllPublishers implements Command {

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
            req.setAttribute("author", author);
            page = PageManager.getProperty("page.authorAllPublishers");
        } catch (ServiceException e) {
            req.setAttribute("errorShowAuthorPublishers", MessageManager.getProperty("message.show-author-publishers-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
