package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 16:50
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {

    private String country;

    private String city;

    private String street;

    private String house;

    private String zipcode;
}
