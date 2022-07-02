package interfaces;

import dto.BookDto;
import entities.Book;
import exceptions.ServiceException;

import java.sql.Date;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 14:49
 */
public interface BookService {

    /**
     * Method to add book to database
     *
     * @param title            is title of created book
     * @param language         is language of created book
     * @param yearOfPublishing is year of publishing of created book
     * @param receiptDate      is receipt date of created book
     * @param authorsIds        are ids of added authors to created book
     * @param genresIds         are ids of added genres to created book
     * @param publisherId      is id of added publisher to created book
     * @throws ServiceException from work with services
     */
    void addBook(String title, String language, String yearOfPublishing,
                 Date receiptDate, int[] authorsIds, int[] genresIds, int publisherId) throws ServiceException;

    /**
     * Method to update book in database
     *
     * @param id               is id of updated book
     * @param title            is title of updated book
     * @param language         is language of updated book
     * @param yearOfPublishing is year of publishing of updated book
     * @param receiptDate      is receipt date of updated book
     * @param authorsIds        are ids of added authors to updated book
     * @param genresIds         are ids of added genres to updated book
     * @param publisherId      is id of added publisher to updated book
     * @throws ServiceException from work with services
     */
    void updateBook(int id, String title, String language, String yearOfPublishing,
                    Date receiptDate, int[] authorsIds, int[] genresIds, int publisherId) throws ServiceException;

    /**
     * Method to delete book in database
     *
     * @param id is id of deleted book
     * @throws ServiceException from work with services
     */
    void deleteBook(int id) throws ServiceException;

    /**
     * Method to find book by id in database
     *
     * @param id is id of found book
     * @return found book
     * @throws ServiceException from work with services
     */
    BookDto findBookById(int id) throws ServiceException;

    /**
     * Method to find book/books by name in database
     *
     * @param name of book
     * @return list of found books
     * @throws ServiceException from work with services
     */
    List<BookDto> findBookByName(String name) throws ServiceException;

    /**
     * Method to find all books in database
     *
     * @return list of all found books
     * @throws ServiceException from work with services
     */
    List<BookDto> findAllBooks() throws ServiceException;

    /**
     * Method to sort books from database by title
     *
     * @return list of sorted books
     * @throws ServiceException from work with services
     */
    List<BookDto> sortAllBooksByName(String[] booksIds) throws ServiceException;

    /**
     * Method to sort books from database by receipt date
     *
     * @return list of sorted books
     * @throws ServiceException from work with services
     */
    List<BookDto> sortAllBooksByDate(String[] booksIds) throws ServiceException;

}
