package impl;

import dao.exceptions.DaoException;
import dao.impl.UserDaoImpl;
import dao.interfaces.Dao;
import dto.UserDto;
import entities.User;
import exceptions.ServiceException;
import interfaces.UserService;
import mappers.EntityMapper;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:17
 */
public class UserServiceImpl implements UserService {

    /**
     * @param user is created record
     * @throws ServiceException from work with services
     */
    @Override
    public void addUser(User user) throws ServiceException {
        try {
            Dao<User> userDao = new UserDaoImpl();
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param user is updated record
     * @throws ServiceException from work with services
     */
    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            Dao<User> userDao = new UserDaoImpl();
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of user
     * @throws ServiceException from work with services
     */
    @Override
    public void deleteUser(int id) throws ServiceException {
        try {
            Dao<User> userDao = new UserDaoImpl();
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id is identification number of user
     * @return user
     * @throws ServiceException from work with services
     */
    @Override
    public UserDto findUserById(int id) throws ServiceException {
        UserDto userDto;
        try {
            Dao<User> userDao = new UserDaoImpl();
            User user = userDao.findById(id);
            userDto = EntityMapper.getInstance().mapUserToUserDto(user);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return userDto;
    }

    /**
     * @param name of user
     * @return list of users
     * @throws ServiceException c
     */
    @Override
    public List<UserDto> findUserByName(String name) throws ServiceException {
        List<UserDto> usersDto;
        try {
            Dao<User> userDao = new UserDaoImpl();
            List<User> users = userDao.findByName(name);
            usersDto = EntityMapper.getInstance().mapListUserToListUserDto(users);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return usersDto;
    }

    /**
     * @return list of users
     * @throws ServiceException from work with services
     */
    @Override
    public List<UserDto> findAllUsers() throws ServiceException {
        List<UserDto> usersDto;
        try {
            Dao<User> userDao = new UserDaoImpl();
            List<User> users = userDao.findAll();
            usersDto = EntityMapper.getInstance().mapListUserToListUserDto(users);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return usersDto;
    }
}
