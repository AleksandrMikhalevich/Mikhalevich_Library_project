package impl;

import dao.exceptions.DaoException;
import dao.impl.AuthorDaoImpl;
import dao.interfaces.Dao;
import entities.Author;
import exceptions.ServiceException;
import interfaces.AuthorService;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:43
 */
public class AuthorServiceImpl implements AuthorService {

    /**
     * @param author is created record
     * @throws ServiceException from work with services
     */
    @Override
    public void addAuthor(Author author) throws ServiceException {
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            authorDao.save(author);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param author is updated record
     * @throws ServiceException from work with services
     */
    @Override
    public void updateAuthor(Author author) throws ServiceException {
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            authorDao.update(author);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of author
     * @throws ServiceException from work with services
     */
    @Override
    public void deleteAuthor(int id) throws ServiceException {
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            authorDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of author
     * @return author
     * @throws ServiceException from work with services
     */
    @Override
    public Author findAuthorById(int id) throws ServiceException {
        Author author;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            author = authorDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return author;
    }

    /**
     * @param name of author
     * @return list of authors
     * @throws ServiceException from work with services
     */
    @Override
    public List<Author> findAuthorByName(String name) throws ServiceException {
        List<Author> authors;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            authors = authorDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authors;
    }

    /**
     * @return list of authors
     * @throws ServiceException from work with services
     */
    @Override
    public List<Author> findAllAuthors() throws ServiceException {
        List<Author> authors;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            authors = authorDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authors;
    }
}
