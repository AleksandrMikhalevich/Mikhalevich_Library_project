package dao.impl;

import dao.exceptions.DaoException;
import dao.interfaces.Dao;
import entities.Genre;
import org.hibernate.HibernateException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:17
 */
public class GenreDaoImpl implements Dao<Genre> {

    /**
     *
     * @param genre is Class-parameter of created record
     * @throws DaoException from work with database
     */
    @Override
    public void create(Genre genre) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param id is identification number of found record
     * @return genre
     * @throws DaoException from work with database
     */
    @Override
    public Genre findById(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Genre forFind = null;
        try {
            entityManager.getTransaction().begin();
            forFind = entityManager.find(Genre.class, id);
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return forFind;
    }

    /**
     *
     * @param genre is updated record
     */
    @Override
    public void update(Genre genre) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(genre);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param id is identification number of deleted record
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Genre forDelete = entityManager.find(Genre.class, id);
            entityManager.remove(forDelete);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param name is name of genre
     * @return  list of found genres
     * @throws DaoException from work with database
     */
    @Override
    public List<Genre> findByName(String name) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> criteria = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> genre = criteria.from(Genre.class);
        criteria.select(genre).where(criteriaBuilder.equal(genre.get("name"), name));
        return entityManager.createQuery(criteria).getResultList();
    }
}
