package mappers;

import dto.*;
import entities.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-20 16:38
 */
@Mapper
public interface EntityMapper {

    static EntityMapper getInstance() {
        return Mappers.getMapper(EntityMapper.class);
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "language", target = "language")
    @Mapping(source = "authors", target = "authors")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "publisher", target = "publisher")
    @Mapping(source = "yearOfPublishing", target = "yearOfPublishing")
    @Mapping(source = "receiptDate", target = "receiptDate")
    BookDto mapBookToBookDto(Book book);

    List<BookDto> mapListBookToListBookDto(List<Book> bookList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "secondName", target = "secondName")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "books", target = "books")
    @Mapping(source = "publishers", target = "publishers")
    AuthorDto mapAuthorToAuthorDto(Author author);

    List<AuthorDto> mapListAuthorToListAuthorDto(List<Author> authorList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "books", target = "books")
    GenreDto mapGenreToGenreDto(Genre genre);

    List<GenreDto> mapListGenreToListGenreDto(List<Genre> genreList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "books", target = "books")
    @Mapping(source = "authors", target = "authors")
    PublisherDto mapPublisherToPublisherDto(Publisher publisher);

    List<PublisherDto> mapListPublisherToListPublisherDto(List<Publisher> publisherList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "email", target = "email")
    UserDto mapUserToUserDto(User user);

    List<UserDto> mapListUserToListUserDto(List<User> userList);

    @InheritInverseConfiguration
    Book inverseMapBookDto(BookDto bookDto);

    @InheritInverseConfiguration
    Author inverseMapAuthorDto(AuthorDto authorDto);

    @InheritInverseConfiguration
    Genre inverseMapGenreDto(GenreDto genreDto);

    @InheritInverseConfiguration
    Publisher inverseMapPublisherDto(PublisherDto publisherDto);

    @InheritInverseConfiguration
    User inverseMapUserDto(UserDto userDto);

}
