package interfaces;

import entities.User;
import exceptions.ServiceException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:15
 */
public interface UserService {

    /**
     * Method to add user to database
     * @param user is created record
     * @throws ServiceException from work with services
     */
    void addUser(User user) throws ServiceException;

    /**
     * Method to update user by name in database
     * @param user is updated record
     * @throws ServiceException from work with services
     */
    void updateUser(User user) throws ServiceException;

    /**
     * Method to delete user by name in database
     * @param id is identification number of user
     * @throws ServiceException from work with services
     */
    void deleteUser(int id) throws ServiceException;

    /**
     * Method to find user by identification number in database
     * @param id is identification number of user
     * @return publisher
     * @throws ServiceException from work with services
     */
    User findById(int id) throws ServiceException;

    /**
     * Method to find user by name in database
     * @param name of user
     * @return list of users
     * @throws ServiceException from work with services
     */
    List<User> findUserByName(String name) throws ServiceException;

}
