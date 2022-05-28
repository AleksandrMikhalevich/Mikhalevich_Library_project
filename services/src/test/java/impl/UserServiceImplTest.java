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
        User userFromDB = userService.findUserById(user.getId());
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
                .login(UPDATE_USER_LOGIN)
                .password(UPDATE_USER_PASSWORD)
                .email(UPDATE_USER_EMAIL)
                .build();
        userService.updateUser(userToUpdate);
        User userFromDB = userService.findUserById(userToUpdate.getId());
        Assertions.assertEquals(userToUpdate, userFromDB);
    }

    @Test
    void shouldDeleteUserInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        userService.deleteUser(user.getId());
        User userFromDB = userService.findUserById(user.getId());
        Assertions.assertNull(userFromDB);
    }

    @Test
    void shouldFindUserByIdInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        User userFromDB = userService.findUserById(user.getId());
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
        List<User> listFromDB = userService.findUserByName(LOGIN);
        Assertions.assertTrue(listFromDB.contains(user));
    }

    @Test
    void shouldFindAllUsersInDatabaseByService() throws ServiceException {
        User user = MockUtils.createUser();
        User user2 = MockUtils.createUser();
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        userService.addUser(user2);
        User userFromDB = userService.findUserById(user.getId());
        User userFromDB2 = userService.findUserById(user2.getId());
        List<User> listFromDB = userService.findAllUsers();
        Assertions.assertTrue(listFromDB.contains(userFromDB));
        Assertions.assertTrue(listFromDB.contains(userFromDB2));
    }
}