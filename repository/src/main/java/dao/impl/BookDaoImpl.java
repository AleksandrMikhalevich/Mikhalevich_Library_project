package dao.impl;

import dao.exceptions.DaoException;
import dao.interfaces.Dao;
import entities.Book;
import org.hibernate.HibernateException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 15:57
 */
public class BookDaoImpl implements Dao<Book> {

    /**
     *
     * @param book is created record
     * @throws DaoException from work with database
     */
    @Override
    public void save(Book book) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
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
     * @return book
     * @throws DaoException from work with database
     */
    @Override
    public Book findById(int id) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Book toFind;
        try {
            entityManager.getTransaction().begin();
            toFind = entityManager.find(Book.class, id);
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
     * @param book is updated record
     * @throws DaoException from work with database
     */
    @Override
    public void update(Book book) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);
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
            Book toDelete = entityManager.find(Book.class, id);
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
     * @param name is name of book
     * @return list of books
     * @throws DaoException from work with database
     */
    @Override
    public List<Book> findByName(String name) throws DaoException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteria.from(Book.class);
        criteria.select(book).where(criteriaBuilder.like(book.get("title"), "%" + name + "%"));
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * Method to find all records in database
     * @return list of books
     * @throws DaoException from work with database
     */
    @Override
    public List<Book> findAll() throws DaoException{
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = criteriaBuilder.createQuery(Book.class);
        Root<Book> allBooks = criteria.from(Book.class);
        criteria.select(allBooks);
        return em.createQuery(criteria).getResultList();
    }
}
