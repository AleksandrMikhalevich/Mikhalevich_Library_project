package dto;

import entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-21 15:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDto {

    private int id;
    private String name;
    private String description;
    private Set<Book> books;
}
