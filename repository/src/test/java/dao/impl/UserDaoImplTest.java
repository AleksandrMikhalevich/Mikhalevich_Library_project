package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dao.impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-02 13:52
 */
class UserDaoImplTest {

    @Test
    void shouldCreateUserInDatabase() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.create(user);
        User userFromDB = userDao.findById(user.getId());
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
    }

    @Test
    void shouldFindUserInDatabaseById() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.create(user);
        User userFromDB = userDao.findById(user.getId());
        Assertions.assertNotNull(userFromDB);
        Assertions.assertNotNull(userFromDB.getId());
        Assertions.assertEquals(LOGIN, userFromDB.getLogin(),"User login is not equals");
        Assertions.assertEquals(PASSWORD, userFromDB.getPassword(),"User password is not equals");
        Assertions.assertEquals(EMAIL, userFromDB.getEmail(),"User email is not equals");
    }

    @Test
    void shouldUpdateUserInDatabase() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.create(user);
        User userToUpdate = User.builder()
                .id(user.getId())
                .login("user_22")
                .password("qwerty_22")
                .email("user_22@library.org")
                .build();
        userDao.update(userToUpdate);
    }

    @Test
    void shouldDeleteUserFromDatabase() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.create(user);
        userDao.delete(user.getId());
    }

    @Test
    void shouldFindUserByName() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.create(user);
        List<User> users = userDao.findByName(LOGIN);
        System.out.println(users);
    }
}