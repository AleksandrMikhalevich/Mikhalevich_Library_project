package interfaces;

import dto.UserDto;
import exceptions.ServiceException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:15
 */
public interface UserService {

    /**
     * Method to add user to database
     *
     * @param login    of added user
     * @param password of added user
     * @param email    of added user
     * @return true if user added, false if user already existed
     * @throws ServiceException from work with services
     */
    boolean addUser(String login, String password, String email) throws ServiceException;

    /**
     * Method to update user by id in database
     *
     * @param id of updated user
     * @param email of updated user
     * @param password of updated user
     * @throws ServiceException from work with services
     */
    void updateUser(int id, String email, String password) throws ServiceException;

    /**
     * Method to delete user by id in database
     *
     * @param id is identification number of user
     * @throws ServiceException from work with services
     */
    void deleteUser(int id) throws ServiceException;

    /**
     * Method to find user by id in database
     *
     * @param id is identification number of user
     * @return publisher
     * @throws ServiceException from work with services
     */
    UserDto findUserById(int id) throws ServiceException;

    /**
     * Method to find user/users by similar login in database
     *
     * @param login of user
     * @return list of users
     * @throws ServiceException from work with services
     */
    List<UserDto> findUserByName(String login) throws ServiceException;

    /**
     * Method to find all users in database
     *
     * @return list of users
     * @throws ServiceException from work with services
     */
    List<UserDto> findAllUsers() throws ServiceException;

    /**
     * Method to find user by exact login in database
     *
     * @return unique user
     * @throws ServiceException from work with services
     */
    UserDto findUserByLogin(String login) throws ServiceException;

    /**
     * Method to sort users from database by login
     *
     * @return list of sorted users
     * @throws ServiceException from work with services
     */
    List<UserDto> sortAllUsersByName(String[] usersIds) throws ServiceException;

}
