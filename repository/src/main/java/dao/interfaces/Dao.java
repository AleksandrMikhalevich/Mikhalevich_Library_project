package dao.interfaces;

import dao.exceptions.DaoException;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 15:57
 */
public interface Dao<T> {

    /**
     * Method to create record in database
     * @param t is Class-parameter of created record
     * @throws DaoException from work with database
     */
    void save(T t) throws DaoException;

    /**
     * Method to read record from database
     * @param id is identification number of found record
     * @return record
     * @throws DaoException from work with database
     */
    T findById(int id) throws DaoException;

    /**
     * Method to update record in database
     * @param t is updated record
     * @throws DaoException from work with database
     */
    void update(T t) throws DaoException;

    /**
     * Method to delete record from database
     * @param id is identification number of deleted record
     * @throws DaoException from work with database
     */
    void delete(int id) throws DaoException;

    /**
     * Method to read records by name from database
     * @param name is name of found record
     * @return record
     * @throws DaoException from work with database
     */
    List<T> findByName(String name) throws DaoException;

    /**
     * Method to read all records from database
     * @return all found records
     * @throws DaoException from work with database
     */
    List<T> findAll() throws DaoException;

}
