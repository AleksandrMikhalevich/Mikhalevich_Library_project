package impl;

import entities.User;
import exceptions.ServiceException;
import impl.mocks.MockUtils;
import interfaces.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 20:13
 */
class UserServiceImplTest {

    @Test
    void shouldCreateUserInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        User userFromDB = userService.findById(user.getId());
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
    }

    @Test
    void shouldUpdateUserInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        User userToUpdate = User.builder()
                .id(user.getId())
                .login("user_22")
                .password("qwerty_22")
                .email("user_22@library.org")
                .build();
        userService.updateUser(userToUpdate);
    }

    @Test
    void shouldDeleteUserInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        userService.deleteUser(user.getId());
    }

    @Test
    void shouldFindUserByIdInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        User userFromDB = userService.findById(user.getId());
        Assertions.assertNotNull(userFromDB);
        Assertions.assertNotNull(userFromDB.getId());
        Assertions.assertEquals(LOGIN, userFromDB.getLogin(),"User login is not equals");
        Assertions.assertEquals(PASSWORD, userFromDB.getPassword(),"User password is not equals");
        Assertions.assertEquals(EMAIL, userFromDB.getEmail(),"User email is not equals");
    }

    @Test
    void shouldFindUserByNameInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        List<User> users = userService.findUserByName(LOGIN);
        System.out.println(users);
    }
}