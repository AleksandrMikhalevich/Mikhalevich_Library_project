package dto;

import entities.Author;
import entities.Genre;
import entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-31 11:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private int id;
    private String title;
    private String language;
    private Set<Author> authors;
    private Set<Genre> genres;
    private Publisher publisher;
    private String yearOfPublishing;
    private Date receiptDate;

}
