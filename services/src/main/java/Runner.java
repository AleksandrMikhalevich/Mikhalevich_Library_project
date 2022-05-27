import entities.*;
import exceptions.ServiceException;
import impl.BookServiceImpl;
import impl.UserServiceImpl;
import interfaces.BookService;
import interfaces.UserService;
import utils.HibernateUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 22:49
 */
public class Runner {

    public static void main(String[] args) {
        Publisher publisher = Publisher.builder()
                .name("Arcade")
                .address(new Address("USA", "Chicago", "Main", "10", "100122"))
                .build();
        Author author = Author.builder()
                .firstName("Jack")
                .secondName("Jackovich")
                .surname("Black")
                .country("USA")
                .publishers(Set.of(publisher))
                .build();
        Genre genre = Genre.builder()
                .name("Thriller")
                .build();
        Book book = Book.builder()
                .title("Spooky")
                .language("English")
                .authors(Set.of(author))
                .genres(Set.of(genre))
                .publisher(publisher)
                .yearOfPublishing("1950")
                .receiptDate(new Date(LocalDate.now().toEpochDay()))
                .build();
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
        HibernateUtil.close();
    }
}
