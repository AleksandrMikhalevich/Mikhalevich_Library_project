import entities.*;
import exceptions.ServiceException;
import impl.BookServiceImpl;
import impl.UserServiceImpl;
import interfaces.BookService;
import interfaces.UserService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 22:49
 */
public class Runner {

    public static void main(String[] args) {
        Author author = Author.builder()
                .firstName("Jack")
                .secondName("Jackovich")
                .surname("Black")
                .country("USA")
                .build();
        Genre genre = Genre.builder()
                .name("Thriller")
                .build();
        Publisher publisher = Publisher.builder()
                .name("Arcade")
                .address(new Address("USA", "Chicago", "Main", "10", "100122"))
                .build();
        Book book = Book.builder()
                .name("Spooky")
                .language("English")
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing("1950")
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
                .build();
        author.setPublishers(Set.of(publisher));
        User user = User.builder()
                .login("user_22")
                .password("qwerty")
                .email("user_22@library.org")
                .build();
        UserService userService = new UserServiceImpl();
        BookService bookService = new BookServiceImpl();
        try {
            userService.addUser(user);
            bookService.addBook(book);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
