package mappers;

import dto.*;
import entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    BookDto mapToBookDto(Book book);

    List<BookDto> mapListToBookDto(List<Book> bookList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "secondName", target = "secondName")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "books", target = "books")
    @Mapping(source = "publishers", target = "publishers")
    AuthorDto mapToAuthorDto(Author author);

    List<AuthorDto> mapListToAuthorDto(List<Author> authorList);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "books", target = "books")
    GenreDto mapToGenreDto(Genre genre);

    List<GenreDto> mapListToGenreDto(List<Genre> genreList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "books", target = "books")
    @Mapping(source = "authors", target = "authors")
    PublisherDto mapPublisherToDto(Publisher publisher);

    List<PublisherDto> mapListToPublisherDto(List<Publisher> publisherList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "email", target = "email")
    UserDto mapUserToDto(User user);

    List<UserDto> mapListToUserDto(List<User> userList);



}
