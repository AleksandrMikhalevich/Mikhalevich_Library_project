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
 * @created 2022-05-01 16:45
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Publisher implements Serializable {

    @Serial
    private static final long serialVersionUID = 3937784919017180628L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "publisher")
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();

    @ManyToMany(mappedBy = "publishers")
    @ToString.Exclude
    private Set<Author> authors = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
