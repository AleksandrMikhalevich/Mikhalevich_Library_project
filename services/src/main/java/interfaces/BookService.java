package interfaces;

import entities.Book;
import exceptions.ServiceException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 14:49
 */
public interface BookService {

    /**
     * Method to add book to database
     * @param book is created record
     * @throws ServiceException from work with services
     */
    void addBook(Book book) throws ServiceException;

    /**
     * Method to update book by name in database
     * @param book is updated record
     * @throws ServiceException from work with services
     */
    void updateBook(Book book) throws ServiceException;

    /**
     * Method to delete book by name in database
     * @param id is identification number of book
     * @throws ServiceException from work with services
     */
    void deleteBook(int id) throws ServiceException;

    /**
     * Method to find book by identification number in database
     * @param id is identification number of book
     * @return book
     * @throws ServiceException from work with services
     */
    Book findBookById(int id) throws ServiceException;

    /**
     * Method to find book/books by name in database
     * @param name of book
     * @return list of books
     * @throws ServiceException from work with services
     */
    List<Book> findBookByName(String name) throws ServiceException;

    /**
     * Method to find all books in database
     * @return list of books
     * @throws ServiceException from work with services
     */
    List<Book> findAllBooks() throws ServiceException;
}
