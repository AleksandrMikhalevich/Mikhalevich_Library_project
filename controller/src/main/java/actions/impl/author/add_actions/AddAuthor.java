package actions.impl.author.add_actions;

import actions.interfaces.Command;
import actions.utils.MessageManager;
import actions.utils.PageManager;
import dto.AuthorDto;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.PublisherServiceImpl;
import interfaces.AuthorService;
import interfaces.PublisherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static actions.impl.constants.Constants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-13 15:15
 */
public class AddAuthor implements Command {

    /**
     * @param req HttpServletRequest from browser
     * @return page for displaying in browser
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        String surname = req.getParameter(AUTHOR_SURNAME);
        String firstName = req.getParameter(AUTHOR_FIRST_NAME);
        String secondName = req.getParameter(AUTHOR_SECOND_NAME);
        String country = req.getParameter(AUTHOR_COUNTRY);
        String[] publisher_ids = req.getParameterValues(PUBLISHERS_IDS);
        if (publisher_ids == null) {
            publisher_ids = new String[0];
        }
        int[] publishersIds = Arrays.stream(publisher_ids).mapToInt(Integer::parseInt).toArray();
        try {
            AuthorService authorService = new AuthorServiceImpl();
            authorService.addAuthor(surname, firstName, secondName, country, publishersIds);
            List<AuthorDto> authorList = authorService.findAllAuthors();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authorList", authorList);
            req.setAttribute("successAddAuthor", MessageManager.getProperty("message.author-add"));
            page = PageManager.getProperty("page.authors");
        } catch (ServiceException e) {
            req.setAttribute("errorAddAuthor", MessageManager.getProperty("message.author-add-error"));
            page = PageManager.getProperty("page.authors");
        }
        return page;
    }
}
