package dao.impl;

import dao.exceptions.DaoException;
import dao.interfaces.Dao;
import entities.Author;
import entities.Book;
import org.hibernate.HibernateException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 18:09
 */
public class AuthorDaoImpl implements Dao<Author> {

    /**
     *
     * @param author is Class-parameter of created record
     * @throws DaoException from work with database
     */
    @Override
    public void save(Author author) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
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
     * @return author
     * @throws DaoException from work with database
     */
    @Override
    public Author findById(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Author toFind;
        try {
            entityManager.getTransaction().begin();
            toFind = entityManager.find(Author.class, id);
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
     * @param author is updated record
     * @throws DaoException from work with database
     */
    @Override
    public void update(Author author) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(author);
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
            Author toDelete = entityManager.find(Author.class, id);
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
     *
     * @param name is name of author
     * @return list of authors
     * @throws DaoException from work with database
     */
    @Override
    public List<Author> findByName(String name) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteria = criteriaBuilder.createQuery(Author.class);
        Root<Author> author = criteria.from(Author.class);
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(author.get("firstName"), "%" + name + "%"),
                criteriaBuilder.like(author.get("secondName"), "%" + name + "%"),
                criteriaBuilder.like(author.get("surname"), "%" + name + "%"));
        criteria.select(author).where(predicate);
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * @return list of authors
     * @throws DaoException from work with database
     */
    @Override
    public List<Author> findAll() throws DaoException {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Author> criteria = criteriaBuilder.createQuery(Author.class);
        Root<Author> allAuthors = criteria.from(Author.class);
        criteria.select(allAuthors);
        return em.createQuery(criteria).getResultList();
    }
}
