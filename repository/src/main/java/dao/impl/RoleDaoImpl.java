package dao.impl;

import dao.exceptions.DaoException;
import dao.interfaces.Dao;
import entities.Role;
import org.hibernate.HibernateException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-09-24 13:44
 */
public class RoleDaoImpl implements Dao<Role> {

    /**
     * @param role is Class-parameter of created record
     * @throws DaoException from work with database
     */
    @Override
    public void save(Role role) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(role);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
    }

    /**
     * @param id is identification number of found record
     * @return role
     * @throws DaoException from work with database
     */
    @Override
    public Role findById(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Role toFind;
        try {
            entityManager.getTransaction().begin();
            toFind = entityManager.find(Role.class, id);
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
        return toFind;
    }

    /**
     * @param role is updated record
     * @throws DaoException from work with database
     */
    @Override
    public void update(Role role) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(role);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        } finally {
            entityManager.close();
        }
    }

    /**
     * @param id is identification number of deleted record
     * @throws DaoException from work with database
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role toDelete = entityManager.find(Role.class, id);
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
     * @param name is name of found record
     * @return list of roles
     * @throws DaoException from work with database
     */
    @Override
    public List<Role> findByName(String name) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = criteriaBuilder.createQuery(Role.class);
        Root<Role> role = criteria.from(Role.class);
        criteria.select(role).where(criteriaBuilder.like(role.get("name"), "%" + name + "%"));
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * @return list of roles
     * @throws DaoException from work with database
     */
    @Override
    public List<Role> findAll() throws DaoException {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = criteriaBuilder.createQuery(Role.class);
        Root<Role> allRoles = criteria.from(Role.class);
        criteria.select(allRoles);
        return em.createQuery(criteria).getResultList();
    }
}
