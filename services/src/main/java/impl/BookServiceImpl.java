package impl;

import dao.exceptions.DaoException;
import dao.impl.AuthorDaoImpl;
import dao.impl.BookDaoImpl;
import dao.impl.GenreDaoImpl;
import dao.impl.PublisherDaoImpl;
import dao.interfaces.Dao;
import dto.BookDto;
import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Publisher;
import exceptions.ServiceException;
import interfaces.BookService;
import mappers.EntityMapper;

import java.sql.Date;
import java.util.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:15
 */
public class BookServiceImpl implements BookService {

    /**
     * @param title            is title of created book
     * @param language         is language of created book
     * @param yearOfPublishing is year of publishing of created book
     * @param receiptDate      is receipt date of created book
     * @param authorsIds        are ids of added authors to created book
     * @param genresIds         are ids of added genres to created book
     * @param publisherId      is id of added publisher to created book
     * @throws ServiceException from work with services
     */
    @Override
    public void addBook(String title, String language, String yearOfPublishing, Date receiptDate,
                        int[] authorsIds, int[] genresIds, int publisherId) throws ServiceException {
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            Set<Author> authorSet = new HashSet<>();
            for (int authorId : authorsIds) {
                Author author = authorDao.findById(authorId);
                authorSet.add(author);
            }
            Dao<Genre> genreDao = new GenreDaoImpl();
            Set<Genre> genreSet = new HashSet<>();
            for (int genreId : genresIds) {
                Genre genre = genreDao.findById(genreId);
                genreSet.add(genre);
            }
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Publisher publisher = publisherDao.findById(publisherId);
            Dao<Book> bookDao = new BookDaoImpl();
            Book book = Book.builder()
                    .title(title)
                    .language(language)
                    .yearOfPublishing(yearOfPublishing)
                    .receiptDate(receiptDate)
                    .authors(authorSet)
                    .genres(genreSet)
                    .publisher(publisher)
                    .build();
            bookDao.save(book);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id               is id of updated book
     * @param title            is title of updated book
     * @param language         is language of updated book
     * @param yearOfPublishing is year of publishing of updated book
     * @param receiptDate      is receipt date of updated book
     * @param authorsIds       are ids of added authors to updated book
     * @param genresIds        are ids of added genres to updated book
     * @param publisherId      is id of added publisher to updated book
     * @throws ServiceException from work with services
     */
    @Override
    public void updateBook(int id, String title, String language, String yearOfPublishing,
                           Date receiptDate, int[] authorsIds, int[] genresIds, int publisherId) throws ServiceException {
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            Set<Author> authorSet = new HashSet<>();
            for (int authorId : authorsIds) {
                Author author = authorDao.findById(authorId);
                authorSet.add(author);
            }
            Dao<Genre> genreDao = new GenreDaoImpl();
            Set<Genre> genreSet = new HashSet<>();
            for (int genreId : genresIds) {
                Genre genre = genreDao.findById(genreId);
                genreSet.add(genre);
            }
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Publisher publisher = publisherDao.findById(publisherId);
            Dao<Book> bookDao = new BookDaoImpl();
            Book book = bookDao.findById(id);
            book.setTitle(title);
            book.setLanguage(language);
            book.setYearOfPublishing(yearOfPublishing);
            book.setReceiptDate(receiptDate);
            book.setAuthors(authorSet);
            book.setGenres(genreSet);
            book.setPublisher(publisher);
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of deleted book
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
     * @param id is id of found book
     * @return found book
     * @throws ServiceException from work with services
     */
    @Override
    public BookDto findBookById(int id) throws ServiceException {
        BookDto bookDto;
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            Book book = bookDao.findById(id);
            bookDto = EntityMapper.getInstance().mapBookToBookDto(book);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return bookDto;
    }

    /**
     * @param name of found book
     * @return list of found books
     * @throws ServiceException from work with services
     */
    @Override
    public List<BookDto> findBookByName(String name) throws ServiceException {
        List<BookDto> booksDto;
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            List<Book> books = bookDao.findByName(name);
            booksDto = EntityMapper.getInstance().mapListBookToListBookDto(books);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return booksDto;
    }

    /**
     * @return list of all found books
     * @throws ServiceException from work with services
     */
    @Override
    public List<BookDto> findAllBooks() throws ServiceException {
        List<BookDto> booksDto;
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            List<Book> books = bookDao.findAll();
            booksDto = EntityMapper.getInstance().mapListBookToListBookDto(books);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return booksDto;
    }

    /**
     * @return list of all sorted books by title
     * @throws ServiceException from work with services
     */
    @Override
    public List<BookDto> sortAllBooksByName(String[] booksIds) throws ServiceException {
        List<BookDto> booksDto = new ArrayList<>();
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            BookDto bookDto;
            for (String book_id : booksIds) {
                Book book = bookDao.findById(Integer.parseInt(book_id));
                bookDto = EntityMapper.getInstance().mapBookToBookDto(book);
                booksDto.add(bookDto);
            }
            booksDto.sort(Comparator.comparing(BookDto::getTitle));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return booksDto;
    }

    /**
     * @return list of all sorted books by date
     * @throws ServiceException from work with services
     */
    @Override
    public List<BookDto> sortAllBooksByDate(String[] booksIds) throws ServiceException {
        List<BookDto> booksDto = new ArrayList<>();
        try {
            Dao<Book> bookDao = new BookDaoImpl();
            BookDto bookDto;
            for (String book_id : booksIds) {
                Book book = bookDao.findById(Integer.parseInt(book_id));
                bookDto = EntityMapper.getInstance().mapBookToBookDto(book);
                booksDto.add(bookDto);
            }
            booksDto.sort(Comparator.comparing(BookDto::getReceiptDate).reversed());
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return booksDto;
    }
}
