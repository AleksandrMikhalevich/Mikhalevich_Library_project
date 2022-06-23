package mappers;

import dto.*;
import entities.*;
import impl.mocks.MockUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-20 16:50
 */
class EntityMapperTest {

    @Test
    void mapToBookDto() {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Genre genre = MockUtils.createGenre();
        Author author = MockUtils.createAuthor(publisher);
        Book book = MockUtils.createBook(author, genre, publisher);
        BookDto bookDto = EntityMapper.getInstance().mapToBookDto(book);
        assertNotNull(bookDto);
        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getLanguage(), book.getLanguage());
        assertEquals(bookDto.getAuthors(), book.getAuthors());
        assertEquals(bookDto.getYearOfPublishing(), book.getYearOfPublishing());
        assertEquals(bookDto.getReceiptDate(), book.getReceiptDate());
    }

    @Test
    void mapListToBookDto() {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Genre genre = MockUtils.createGenre();
        Author author = MockUtils.createAuthor(publisher);
        Book book = MockUtils.createBook(author, genre, publisher);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        List<BookDto> bookDtoList = EntityMapper.getInstance().mapListToBookDto(bookList);
        assertNotNull(bookDtoList);
    }

    @Test
    void mapToAuthorDto() {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Author author = MockUtils.createAuthor(publisher);
        AuthorDto authorDto = EntityMapper.getInstance().mapToAuthorDto(author);
        assertNotNull(authorDto);
        assertEquals(authorDto.getFirstName(), author.getFirstName());
        assertEquals(authorDto.getSecondName(), author.getSecondName());
        assertEquals(authorDto.getSurname(), author.getSurname());
        assertEquals(authorDto.getCountry(), author.getCountry());

    }

    @Test
    void mapListToAuthorDto() {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        Author author = MockUtils.createAuthor(publisher);
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        List<AuthorDto> authorDtoList = EntityMapper.getInstance().mapListToAuthorDto(authorList);
        assertNotNull(authorDtoList);
    }

    @Test
    void mapToGenreDto() {
        Genre genre = MockUtils.createGenre();
        GenreDto genreDto = EntityMapper.getInstance().mapToGenreDto(genre);
        assertNotNull(genreDto);
        assertEquals(genreDto.getName(), genre.getName());
        assertEquals(genreDto.getDescription(), genre.getDescription());
    }

    @Test
    void mapListToGenreDto() {
        Genre genre = MockUtils.createGenre();
        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre);
        List<GenreDto> genreDtoList = EntityMapper.getInstance().mapListToGenreDto(genreList);
        assertNotNull(genreDtoList);
    }

    @Test
    void mapPublisherToDto() {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        PublisherDto publisherDto = EntityMapper.getInstance().mapPublisherToDto(publisher);
        assertNotNull(publisherDto);
        assertEquals(publisherDto.getName(), publisher.getName());
        assertEquals(publisherDto.getAddress(), publisher.getAddress());
    }

    @Test
    void mapListToPublisherDto() {
        Publisher publisher = MockUtils.createPublisher(MockUtils.createAddress());
        List<Publisher> publisherList = new ArrayList<>();
        publisherList.add(publisher);
        List<PublisherDto> publisherDtoList = EntityMapper.getInstance().mapListToPublisherDto(publisherList);
        assertNotNull(publisherDtoList);
    }

    @Test
    void mapUserToDto() {
        User user = MockUtils.createUser();
        UserDto userDto = EntityMapper.getInstance().mapUserToDto(user);
        assertNotNull(userDto);
        assertEquals(userDto.getLogin(), user.getLogin());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    void mapListToUserDto() {
        User user = MockUtils.createUser();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserDto> userDtoList = EntityMapper.getInstance().mapListToUserDto(userList);
        assertNotNull(userDtoList);
    }
}