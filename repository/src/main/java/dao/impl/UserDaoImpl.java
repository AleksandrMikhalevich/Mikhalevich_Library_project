package dao.impl;

import dao.exceptions.DaoException;
import dao.interfaces.Dao;
import entities.User;
import org.hibernate.HibernateException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:20
 */
public class UserDaoImpl implements Dao<User> {

    /**
     *
     * @param user is Class-parameter of created record
     * @throws DaoException from work with database
     */
    @Override
    public void save(User user) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param id is identification number of found record
     * @return user
     * @throws DaoException from work with database
     */
    @Override
    public User findById(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        User toFind;
        try {
            entityManager.getTransaction().begin();
            toFind = entityManager.find(User.class, id);
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
        return toFind;
    }

    /**
     *
     * @param user is updated record
     * @throws DaoException from work with database
     */
    @Override
    public void update(User user) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param id is identification number of deleted record
     * @throws DaoException from work with database
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            User toDelete = entityManager.find(User.class, id);
            entityManager.remove(toDelete);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
    }

    /**
     * @param login of user
     * @return list of users with similar logins
     * @throws DaoException from work with database
     */
    @Override
    public List<User> findByName(String login) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        criteria.select(user).where(criteriaBuilder.like(user.get("login"), "%" + login + "%"));
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * @return list of users
     * @throws DaoException from work with database
     */
    @Override
    public List<User> findAll() throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> allUsers = criteria.from(User.class);
        criteria.select(allUsers);
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * Method to find user by unique name in database
     *
     * @return unique user
     * @throws DaoException from work with database
     */
    public User findByLogin(String login) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        criteria.select(user).where(criteriaBuilder.equal(user.get("login"), login));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
