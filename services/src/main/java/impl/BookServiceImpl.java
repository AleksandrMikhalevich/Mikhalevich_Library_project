package impl;

import dao.exceptions.DaoException;
import dao.impl.BookDaoImpl;
import dao.interfaces.Dao;
import entities.Book;
import exceptions.ServiceException;
import interfaces.BookService;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:15
 */
public class BookServiceImpl implements BookService {

    /**
     * @param book is created record
     * @throws ServiceException from work with services
     */
    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            bookDao.create(book);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param book is updated record
     * @throws ServiceException from work with services
     */
    @Override
    public void updateBook(Book book) throws ServiceException {
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of book
     * @throws ServiceException from work with services
     */
    @Override
    public void deleteBook(int id) throws ServiceException {
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            bookDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of book
     * @return book
     * @throws ServiceException from work with services
     */
    @Override
    public Book findById(int id) throws ServiceException {
        Book book;
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            book = bookDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return book;
    }

    /**
     * @param name of book
     * @return list of books
     * @throws ServiceException from work with services
     */
    @Override
    public List<Book> findBookByName(String name) throws ServiceException {
        List<Book> books;
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            books = bookDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return books;
    }
}
