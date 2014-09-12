/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface BaseCrudDAO<T, K> {

    T makePersistent(T entity) throws EngineDAOException;
    void makeTransient(T entity) throws EngineDAOException;
    void makeTransient(List<T> entities) throws EngineDAOException;
    T selectByKey(K key) throws EngineDAOException;
}
