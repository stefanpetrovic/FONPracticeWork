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
public interface BasePersistentDAO<T, K> extends BaseCrudDAO<T, K> {

    T merge(T entity) throws EngineDAOException;

    List<T> makePersistent(List<T> entities) throws EngineDAOException;

    List<T> findAll() throws EngineDAOException;

    List<T> findByExample(T exampleInstance, String[] excludeProperty) throws EngineDAOException;

    List<T> findByExampleExcludeZeroes(T exampleInstance) throws EngineDAOException;

    void flush() throws EngineDAOException;

    void clear() throws EngineDAOException;
}
