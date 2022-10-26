package dto;

import entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex Mikhalevich
 * @created 2022-06-21 15:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String login;
    private String password;
    private String passwordConfirm;
    private String email;
    private Role role;

}
