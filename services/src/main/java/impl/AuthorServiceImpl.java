package impl;

import dao.exceptions.DaoException;
import dao.impl.AuthorDaoImpl;
import dao.impl.PublisherDaoImpl;
import dao.interfaces.Dao;
import dto.AuthorDto;
import dto.PublisherDto;
import entities.Author;
import entities.Publisher;
import exceptions.ServiceException;
import interfaces.AuthorService;
import mappers.EntityMapper;

import java.util.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 15:43
 */
public class AuthorServiceImpl implements AuthorService {

    /**
     * @param surname       is surname of created author
     * @param firstName     is name of created author
     * @param secondName    is second name of created author
     * @param country       is country of created author
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
     * @param id            is id of updated author
     * @param surname       is surname of updated author
     * @param firstName     is name of updated author
     * @param secondName    is second name of updated author
     * @param country       is country of updated author
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
    public AuthorDto findAuthorById(int id) throws ServiceException {
        AuthorDto authorDto;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            Author author = authorDao.findById(id);
            authorDto = EntityMapper.getInstance().mapAuthorToAuthorDto(author);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authorDto;
    }

    /**
     * @param name of found author
     * @return list of  found authors
     * @throws ServiceException from work with services
     */
    @Override
    public List<AuthorDto> findAuthorByName(String name) throws ServiceException {
        List<AuthorDto> authorsDto;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            List<Author> authors = authorDao.findByName(name);
            authorsDto = EntityMapper.getInstance().mapListAuthorToListAuthorDto(authors);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authorsDto;
    }

    /**
     * @return list of all found authors
     * @throws ServiceException from work with services
     */
    @Override
    public List<AuthorDto> findAllAuthors() throws ServiceException {
        List<AuthorDto> authorsDto;
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            List<Author> authors = authorDao.findAll();
            authorsDto = EntityMapper.getInstance().mapListAuthorToListAuthorDto(authors);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authorsDto;
    }

    /**
     * @param authorIds are ids of chosen authors
     * @return set of chosen authors
     * @throws ServiceException from work with services
     */
    @Override
    public Set<AuthorDto> chooseAuthorsToBook(String[] authorIds) throws ServiceException {
        Dao<Author> authorDao = new AuthorDaoImpl();
        Set<AuthorDto> authorSet = new HashSet<>();
        for (String author_id : authorIds) {
            Author author;
            AuthorDto authorDto;
            try {
                author = authorDao.findById(Integer.parseInt(author_id));
                authorDto = EntityMapper.getInstance().mapAuthorToAuthorDto(author);
            } catch (DaoException e) {
                throw new ServiceException();
            }
            authorSet.add(authorDto);
        }
        return authorSet;
    }

    /**
     * @param authorsIds are ids of needed authors
     * @return set of author's publishers
     * @throws ServiceException from work with services
     */
    @Override
    public Set<PublisherDto> getAuthorSetOfPublishers(String[] authorsIds) throws ServiceException {
        Dao<Author> authorDao = new AuthorDaoImpl();
        Set<PublisherDto> authorsPublishers = new HashSet<>();
        for (String author_id : authorsIds) {
            Author author;
            PublisherDto publisherDto = null;
            try {
                author = authorDao.findById(Integer.parseInt(author_id));
                for (Publisher publisher : author.getPublishers()) {
                    publisherDto = EntityMapper.getInstance().mapPublisherToPublisherDto(publisher);
                }
            } catch (DaoException e) {
                throw new ServiceException();
            }
            authorsPublishers.add(publisherDto);
        }
        return authorsPublishers;
    }

    /**
     * @return list of sorted authors
     * @throws ServiceException from work with services
     */
    @Override
    public List<AuthorDto> sortAllAuthorsBySurname(String[] authorsIds) throws ServiceException {
        List<AuthorDto> authorsDto = new ArrayList<>();
        try {
            Dao<Author> authorDao = new AuthorDaoImpl();
            AuthorDto authorDto;
            for (String author_id : authorsIds) {
                Author author = authorDao.findById(Integer.parseInt(author_id));
                authorDto = EntityMapper.getInstance().mapAuthorToAuthorDto(author);
                authorsDto.add(authorDto);
            }
            authorsDto.sort(Comparator.comparing(AuthorDto::getSurname));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authorsDto;
    }
}
