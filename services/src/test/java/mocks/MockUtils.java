package mocks;

import dto.*;
import entities.*;
import exceptions.ServiceException;
import impl.AuthorServiceImpl;
import impl.BookServiceImpl;
import impl.GenreServiceImpl;
import impl.PublisherServiceImpl;
import interfaces.AuthorService;
import interfaces.BookService;
import interfaces.GenreService;
import interfaces.PublisherService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:51
 */
public class MockUtils {

    public static int findIdOfBook() throws ServiceException {
        BookService bookService = new BookServiceImpl();
        List<BookDto> bookDtoListFromDB = bookService.findBookByName(BOOK_TITLE);
        int id = 0;
        for (BookDto bookDto : bookDtoListFromDB) {
            id = bookDto.getId();
        }
        return id;
    }

    public static int findIdOfAuthor() throws ServiceException {
        AuthorService authorService = new AuthorServiceImpl();
        List<AuthorDto> authorDtoListFromDB = authorService.findAuthorByName(AUTHOR_SURNAME);
        int id = 0;
        for (AuthorDto authorDto : authorDtoListFromDB) {
            id = authorDto.getId();
        }
        return id;
    }

    public static int findIdOfGenre() throws ServiceException {
        GenreService genreService = new GenreServiceImpl();
        List<GenreDto> genreDtoListFromDB = genreService.findGenreByName(GENRE_NAME);
        int id = 0;
        for (GenreDto genreDto : genreDtoListFromDB) {
            id = genreDto.getId();
        }
        return id;
    }

    public static int findIdOfPublisher() throws ServiceException {
        PublisherService publisherService = new PublisherServiceImpl();
        List<PublisherDto> publisherDtoListFromDB = publisherService.findPublisherByName(PUBLISHER_NAME);
        int id = 0;
        for (PublisherDto publisherDto : publisherDtoListFromDB) {
            id = publisherDto.getId();
        }
        return id;
    }

    public static Book createBook(Author author, Genre genre, Publisher publisher) {
        return Book.builder()
                .title(BOOK_TITLE)
                .language(BOOK_LANGUAGE)
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing(BOOK_YEAR_OF_PUBLISHING)
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
                .build();
    }

    public static Author createAuthor(Publisher publisher) {
        return Author.builder()
                .firstName(AUTHOR_FIRST_NAME)
                .secondName(AUTHOR_SECOND_NAME)
                .surname(AUTHOR_SURNAME)
                .country(AUTHOR_COUNTRY)
                .publishers(Set.of(publisher))
                .build();
    }

    public static Genre createGenre() {
        return Genre.builder()
                .name(GENRE_NAME)
                .build();
    }

    public static Publisher createPublisher(Address address) {
        return Publisher.builder()
                .name(PUBLISHER_NAME)
                .address(address)
                .build();
    }

    public static Address createAddress() {
        return Address.builder()
                .country(PUBLISHER_COUNTRY)
                .city(PUBLISHER_CITY)
                .street(PUBLISHER_STREET)
                .house(PUBLISHER_HOUSE)
                .zipcode(PUBLISHER_ZIPCODE)
                .build();
    }

    public static User createUser() {
        return User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
    }
}
