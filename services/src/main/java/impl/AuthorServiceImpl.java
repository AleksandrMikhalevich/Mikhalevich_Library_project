package impl;

import dao.exceptions.DaoException;
import dao.impl.AuthorDaoImpl;
import dao.impl.PublisherDaoImpl;
import dao.interfaces.Dao;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;
import interfaces.AuthorService;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:43
 */
public class AuthorServiceImpl implements AuthorService {

    /**
     * @param surname is surname of created author
     * @param firstName is name of created author
     * @param secondName is second name of created author
     * @param country is country of created author
     * @param publishersIds are ids of added publishers to created author
     * @throws ServiceException from work with services
     */
    @Override
    public void addAuthor(String surname, String firstName, String secondName, String country, int[] publishersIds) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Set<Publisher> publisherSet = new HashSet<>();
            for (int publisherId : publishersIds) {
                Publisher publisher = publisherDao.findById(publisherId);
                publisherSet.add(publisher);
            }
            Dao<Author> authorDao = new AuthorDaoImpl();
            Author author = Author.builder()
                    .surname(surname)
                    .firstName(firstName)
                    .secondName(secondName)
                    .country(country)
                    .publishers(publisherSet)
                    .build();
            authorDao.save(author);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of updated author
     * @param surname is surname of updated author
     * @param firstName is name of updated author
     * @param secondName is second name of updated author
     * @param country is country of updated author
     * @param publishersIds are ids of added publishers to updated author
     * @throws ServiceException from work with services
     */
    @Override
    public void updateAuthor(int id, String surname, String firstName, String secondName, String country, int[] publishersIds) throws ServiceException {
        try {
            Dao<Publisher> publisherDao = new PublisherDaoImpl();
            Set<Publisher> publisherSet = new HashSet<>();
            for (int publisherId : publishersIds) {
                Publisher publisher = publisherDao.findById(publisherId);
                publisherSet.add(publisher);
            }
            Dao<Author> authorDao = new AuthorDaoImpl();
            Author author = authorDao.findById(id);
            author.setSurname(surname);
            author.setFirstName(firstName);
            author.setSecondName(secondName);
            author.setCountry(country);
            author.setPublishers(publisherSet);
            authorDao.update(author);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is id of deleted author
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
     * @param id is id of found author
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
     * @param name of found author
     * @return list of  found authors
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
     * @return list of all found authors
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

    /**
     * @param authorIds are ids of chosen authors
     * @return set of chosen authors
     * @throws ServiceException from work with services
     */
    @Override
    public Set<Author> chooseAuthorsToBook(String[] authorIds) throws ServiceException {
        Dao<Author> authorDao = new AuthorDaoImpl();
        Set<Author> authorSet = new HashSet<>();
        for (String author_id : authorIds) {
            Author author;
            try {
                author = authorDao.findById(Integer.parseInt(author_id));
            } catch (DaoException e) {
                throw new ServiceException();
            }
            authorSet.add(author);
        }
        return authorSet;
    }

    /**
     * @param authorIds are ids of needed authors
     * @return set of author's publishers
     * @throws ServiceException from work with services
     */
    @Override
    public Set<Publisher> getAuthorSetOfPublishers(String[] authorIds) throws ServiceException {
        Dao<Author> authorDao = new AuthorDaoImpl();
        Set<Publisher> authorsPublishers = new HashSet<>();
        for (String author_id : authorIds) {
            Author author;
            try {
                author = authorDao.findById(Integer.parseInt(author_id));
            } catch (DaoException e) {
                throw new ServiceException();
            }
            authorsPublishers.addAll(author.getPublishers());
        }
        return authorsPublishers;
    }

    /**
     * @return list of sorted authors
     * @throws ServiceException from work with services
     */
    @Override
    public List<Author> sortAllAuthorsBySurname() throws ServiceException {
        List<Author> authors;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            authors = authorDao.findAll();
            authors.sort(Comparator.comparing(Author::getSurname));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authors;
    }
}
