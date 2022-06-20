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
        userDao.save(user);
        User userFromDB = userDao.findById(user.getId());
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
    }

    @Test
    void shouldFindUserInDatabaseById() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.save(user);
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
        userDao.save(user);
        User userToUpdate = User.builder()
                .id(user.getId())
                .login(UPDATE_LOGIN)
                .password(UPDATE_PASSWORD)
                .email(UPDATE_EMAIL)
                .build();
        userDao.update(userToUpdate);
        User userFromDB = userDao.findById(userToUpdate.getId());
        Assertions.assertEquals(userToUpdate, userFromDB);
    }

    @Test
    void shouldDeleteUserFromDatabase() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.save(user);
        userDao.delete(user.getId());
        User userFromDB = userDao.findById(user.getId());
        Assertions.assertNull(userFromDB);
    }

    @Test
    void shouldFindUserInDatabaseByName() throws DaoException {
        User user = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.save(user);
        List<User> listFromDB = userDao.findByName(LOGIN);
        Assertions.assertTrue(listFromDB.contains(user));
    }

    @Test
    void shouldFindAllUsersInDatabase() throws DaoException {
        User user = MockUtils.createUser();
        User user2 = MockUtils.createUser();
        Dao<User> userDao = new UserDaoImpl();
        userDao.save(user);
        userDao.save(user2);
        User userFromDB = userDao.findById(user.getId());
        User userFromDB2 = userDao.findById(user2.getId());
        List<User> listFromDB = userDao.findAll();
        Assertions.assertTrue(listFromDB.contains(userFromDB));
        Assertions.assertTrue(listFromDB.contains(userFromDB2));
    }
}