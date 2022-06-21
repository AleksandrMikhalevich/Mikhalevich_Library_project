package dto;

import entities.Address;
import entities.Author;
import entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-21 15:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherDto {

    private int id;
    private String name;
    private Address address;
    private Set<Book> books;
    private Set<Author> authors;

}
