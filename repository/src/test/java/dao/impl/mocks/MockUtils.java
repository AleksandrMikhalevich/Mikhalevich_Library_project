package dao.impl.mocks;

import entities.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import static dao.impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:51
 */
public class MockUtils {

    public static Author createAuthor(Publisher publisher) {
        return Author.builder()
                .firstName(FIRST_NAME)
                .secondName(SECOND_NAME)
                .surname(SURNAME)
                .country(AUTHOR_COUNTRY)
                .publishers(Set.of(publisher))
                .build();
    }

    public static Book createBook(Author author, Genre genre, Publisher publisher) {
        return Book.builder()
                .title(BOOK_TITLE)
                .language(LANGUAGE)
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing(YEAR_OF_PUBLISHING)
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
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
                .city(CITY)
                .street(STREET)
                .house(HOUSE)
                .zipcode(ZIPCODE)
                .build();
    }

    public static User createUser(Role role) {
        return User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .email(EMAIL)
                .role(role)
                .build();
    }

    public static Role createRole() {
        return Role.builder()
                .name("USER")
                .build();
    }
}
