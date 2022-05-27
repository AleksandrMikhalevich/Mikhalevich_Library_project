package impl;

import dao.exceptions.DaoException;
import dao.impl.UserDaoImpl;
import dao.interfaces.Dao;
import entities.User;
import exceptions.ServiceException;
import interfaces.UserService;

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
    public User findById(int id) throws ServiceException {
        User user;
        try {
            Dao<User> userDao = new UserDaoImpl();
            user = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return user;
    }

    /**
     * @param name of user
     * @return list of users
     * @throws ServiceException from work with services
     */
    @Override
    public List<User> findUserByName(String name) throws ServiceException {
        List<User> users;
        try {
            Dao<User> userDao = new UserDaoImpl();
            users = userDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return users;
    }
}
