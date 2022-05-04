package entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 16:11
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 537892088715347239L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column
    private String surname;

    @Column
    private String country;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @ToString.Exclude
    @JoinTable(name = "author_publisher",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
