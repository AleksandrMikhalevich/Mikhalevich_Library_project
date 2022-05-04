package entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 16:00
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = -9045406178931973760L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String language;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "book_genre",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private Publisher publisher;

    @Column(name = "year_of_publishing")
    private String yearOfPublishing;

    @Column(name = "receipt_date")
    private Date receiptDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
