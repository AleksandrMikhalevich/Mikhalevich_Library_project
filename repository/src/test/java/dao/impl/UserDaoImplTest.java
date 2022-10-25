package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Role;
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
        Role role = MockUtils.createRole();
        User user = MockUtils.createUser(role);
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        roleDao.save(role);
        userDao.save(user);
        User userFromDB = userDao.findById(user.getId());
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
    }

    @Test
    void shouldFindUserInDatabaseById() throws DaoException {
        Role role = MockUtils.createRole();
        User user = MockUtils.createUser(role);
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        roleDao.save(role);
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
        Role role = MockUtils.createRole();
        User user = MockUtils.createUser(role);
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        roleDao.save(role);
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
        Role role = MockUtils.createRole();
        User user = MockUtils.createUser(role);
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        roleDao.save(role);
        userDao.save(user);
        userDao.delete(user.getId());
        User userFromDB = userDao.findById(user.getId());
        Assertions.assertNull(userFromDB);
    }

    @Test
    void shouldFindUsersInDatabaseByName() throws DaoException {
        Role role = MockUtils.createRole();
        User user = MockUtils.createUser(role);
        User user2 = User.builder()
                .login(UPDATE_LOGIN)
                .password(PASSWORD)
                .email(EMAIL)
                .role(role)
                .build();
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        roleDao.save(role);
        userDao.save(user);
        userDao.save(user2);
        List<User> listFromDB = userDao.findByName("user");
        Assertions.assertTrue(listFromDB.contains(user));
        Assertions.assertTrue(listFromDB.contains(user2));
    }

    @Test
    void shouldFindAllUsersInDatabase() throws DaoException {
        Role role = MockUtils.createRole();
        User user = MockUtils.createUser(role);
        User user2 = MockUtils.createUser(role);
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        roleDao.save(role);
        userDao.save(user);
        userDao.save(user2);
        User userFromDB = userDao.findById(user.getId());
        User userFromDB2 = userDao.findById(user2.getId());
        List<User> listFromDB = userDao.findAll();
        Assertions.assertTrue(listFromDB.contains(userFromDB));
        Assertions.assertTrue(listFromDB.contains(userFromDB2));
    }

    @Test
    void shouldFindUserInDatabaseByLogin() throws DaoException {
        Role role = MockUtils.createRole();
        User user = User.builder()
                .login(NEW_LOGIN)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
        Dao<Role> roleDao = new RoleDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        UserDaoImpl userDao2 = new UserDaoImpl();
        roleDao.save(role);
        userDao.save(user);
        User userFromDB = userDao2.findByLogin(NEW_LOGIN);
        Assertions.assertEquals(user, userFromDB);
    }
}