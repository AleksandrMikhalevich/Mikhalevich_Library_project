package impl;

import dao.exceptions.DaoException;
import dao.impl.UserDaoImpl;
import dao.interfaces.Dao;
import dto.UserDto;
import entities.Role;
import entities.User;
import exceptions.ServiceException;
import interfaces.UserService;
import mappers.EntityMapper;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-04 16:17
 */
public class UserServiceImpl implements UserService {

    /**
     * @param login    of added user
     * @param password of added user
     * @param email    of added user
     * @return true if user added, false if user already existed
     * @throws ServiceException from work with services
     */
    @Override
    public boolean addUser(String login, String password, String email) throws ServiceException {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            User userFromDB = null;
            try {
                userFromDB = userDao.findByLogin(login);
            } catch (NoResultException ignored) {
            }
            if (userFromDB != null) {
                return false;
            } else {
                User user = User.builder()
                        .login(login)
                        .password(password)
                        .email(email)
                        .role(new Role(1, "USER"))
                        .build();
                userDao.save(user);
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /**
     * @param id of updated user
     * @param email of updated user
     * @param password of updated user
     * @throws ServiceException from work with services
     */
    @Override
    public void updateUser(int id, String email, String password) throws ServiceException {
        try {
            Dao<User> userDao = new UserDaoImpl();
            User user = userDao.findById(id);
            user.setEmail(email);
            user.setPassword(password);
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
     * @param login of user
     * @return list of users with similar logins
     * @throws ServiceException c
     */
    @Override
    public List<UserDto> findUserByName(String login) throws ServiceException {
        List<UserDto> usersDto;
        try {
            Dao<User> userDao = new UserDaoImpl();
            List<User> users = userDao.findByName(login);
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

    /**
     * @param login of user
     * @return unique user
     * @throws ServiceException from work with services
     */
    @Override
    public UserDto findUserByLogin(String login) throws ServiceException {
        UserDto userDto;
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.findByLogin(login);
            userDto = EntityMapper.getInstance().mapUserToUserDto(user);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return userDto;
    }

    /**
     * @param usersIds are ids of sorted users
     * @return list of sorted users
     * @throws ServiceException from work with services
     */
    @Override
    public List<UserDto> sortAllUsersByName(String[] usersIds) throws ServiceException {
        List<UserDto> usersDto = new ArrayList<>();
        try {
            Dao<User> userDao = new UserDaoImpl();
            UserDto userDto;
            for (String user_id : usersIds) {
                User user = userDao.findById(Integer.parseInt(user_id));
                userDto = EntityMapper.getInstance().mapUserToUserDto(user);
                usersDto.add(userDto);
            }
            usersDto.sort(Comparator.comparing(UserDto::getLogin));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return usersDto;
    }
}
