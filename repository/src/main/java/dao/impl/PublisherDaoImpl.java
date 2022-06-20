package dao.impl;

import dao.exceptions.DaoException;
import dao.interfaces.Dao;
import entities.Author;
import entities.Publisher;
import org.hibernate.HibernateException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 18:20
 */
public class PublisherDaoImpl implements Dao<Publisher> {

    /**
     *
     * @param publisher is Class-parameter of created record
     * @throws DaoException from work with database
     */
    @Override
    public void save(Publisher publisher) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(publisher);
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
     * @return publisher
     * @throws DaoException from work with database
     */
    @Override
    public Publisher findById(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Publisher toFind = null;
        try {
            entityManager.getTransaction().begin();
            toFind = entityManager.find(Publisher.class, id);
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return toFind;
    }

    /**
     *
     * @param publisher is updated record
     * @throws DaoException from work with database
     */
    @Override
    public void update(Publisher publisher) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(publisher);
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
     * @throws DaoException from work with database
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Publisher toDelete = entityManager.find(Publisher.class, id);
            for (Author author : toDelete.getAuthors()) {
                toDelete.removeAuthor(author);
            }
            entityManager.remove(toDelete);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * @param name of publisher
     * @return list of publishers
     * @throws DaoException from work with database
     */
    @Override
    public List<Publisher> findByName(String name) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Publisher> criteria = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> publisher = criteria.from(Publisher.class);
        criteria.select(publisher).where(criteriaBuilder.like(publisher.get("name"), "%" + name + "%"));
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * @return list of publishers
     * @throws DaoException from work with database
     */
    @Override
    public List<Publisher> findAll() throws DaoException {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Publisher> criteria = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> allPublishers = criteria.from(Publisher.class);
        criteria.select(allPublishers);
        return em.createQuery(criteria).getResultList();
    }
}
