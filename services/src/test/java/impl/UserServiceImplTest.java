package impl;

import dao.exceptions.DaoException;
import dto.UserDto;
import exceptions.ServiceException;
import interfaces.UserService;
import mocks.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static mocks.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-10-25 13:20
 */
class UserServiceImplTest {

    @BeforeEach
    void setUp() throws DaoException {
        MockUtils.createRole();
    }

    @Test
    void shouldAddUserToDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB = userService.findUserByLogin(LOGIN);
        assertNotNull(userDtoFromDB);
        assertEquals(LOGIN, userDtoFromDB.getLogin(), "Login is not equals");
        assertEquals(PASSWORD, userDtoFromDB.getPassword(), "Password is not equals");
        assertEquals(EMAIL, userDtoFromDB.getEmail(), "Email is not equals");
    }

    @Test
    void shouldUpdateUserInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(ANOTHER_LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB = userService.findUserByLogin(ANOTHER_LOGIN );
        userService.updateUser(userDtoFromDB.getId(), UPDATE_USER_EMAIL, UPDATE_USER_PASSWORD);
        UserDto userDtoFromDB2 = userService.findUserById(userDtoFromDB.getId());
        assertNotNull(userDtoFromDB2);
        assertEquals(ANOTHER_LOGIN, userDtoFromDB2.getLogin(), "Login is not equals");
        assertEquals(UPDATE_USER_EMAIL, userDtoFromDB2.getEmail(), "Email is not equals");
        assertEquals(UPDATE_USER_PASSWORD, userDtoFromDB2.getPassword(), "Password is not equals");
    }

    @Test
    void shouldDeleteUserInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB = userService.findUserByLogin(LOGIN);
        userService.deleteUser(userDtoFromDB.getId());
        UserDto userDtoFromDB2 = userService.findUserById(userDtoFromDB.getId());
        assertNull(userDtoFromDB2);
    }

    @Test
    void shouldFindUserByIdInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB = userService.findUserByLogin(LOGIN);
        UserDto userDtoFromDB2 = userService.findUserById(userDtoFromDB.getId());
        assertNotNull(userDtoFromDB2);
        assertEquals(userDtoFromDB, userDtoFromDB2);
        assertEquals(LOGIN, userDtoFromDB2.getLogin(), "Login is not equals");
        assertEquals(PASSWORD, userDtoFromDB2.getPassword(), "Password is not equals");
        assertEquals(EMAIL, userDtoFromDB2.getEmail(), "Email is not equals");
    }

    @Test
    void shouldFindUserByNameInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        List<UserDto> userDtoListFromDB = userService.findUserByName(LOGIN);
        String login = null;
        for (UserDto userDto : userDtoListFromDB) {
            login = userDto.getLogin();
        }
        assertEquals(LOGIN, login, "Login is not equals");
    }

    @Test
    void shouldFindAllUsersInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB = userService.findUserByLogin(LOGIN);
        List<UserDto> userDtoListFromDB = userService.findAllUsers();
        assertTrue(userDtoListFromDB.contains(userDtoFromDB));
    }

    @Test
    void shouldFindUserByLoginInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB = userService.findUserByLogin(LOGIN);
        assertNotNull(userDtoFromDB);
        assertEquals(LOGIN, userDtoFromDB.getLogin(), "Login is not equals");
        assertEquals(PASSWORD, userDtoFromDB.getPassword(), "Password is not equals");
        assertEquals(EMAIL, userDtoFromDB.getEmail(), "Email is not equals");
    }

    @Test
    void shouldSortAllUsersByNameInDatabaseByService() throws ServiceException {
        UserService userService = new UserServiceImpl();
        userService.addUser(LOGIN, PASSWORD, EMAIL);
        UserDto userDtoFromDB_1 = userService.findUserByLogin(LOGIN);
        String id1 = String.valueOf(userDtoFromDB_1.getId());
        userService.addUser(ANOTHER_LOGIN, UPDATE_USER_PASSWORD, UPDATE_USER_PASSWORD);
        List<UserDto> userDtoListFromDB = userService.findUserByName(ANOTHER_LOGIN);
        String id2 = String.valueOf(0);
        for (UserDto userDto : userDtoListFromDB) {
            id2 = String.valueOf(userDto.getId());
        }
        UserDto userDtoFromDB_2 = userService.findUserById(Integer.parseInt(id2));
        List<UserDto> userDtoList = userService.sortAllUsersByName(new String[]{id1, id2});
        assertEquals(userDtoFromDB_2, userDtoList.get(0));
        assertEquals(userDtoFromDB_1, userDtoList.get(1));
    }
}