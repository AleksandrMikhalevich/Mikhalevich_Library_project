package dto;

import entities.Book;
import entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-21 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private int id;
    private String firstName;
    private String secondName;
    private String surname;
    private String country;
    private Set<Book> books;
    private Set<Publisher> publishers;

}
