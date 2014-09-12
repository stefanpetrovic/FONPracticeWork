/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.BasePersistentDAO;
import dao.HibernateUtil;
import dao.exception.EngineDAOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;

/**
 *
 * @author Djole
 */
public abstract class AbstractHibernateDAO<T, K> implements BasePersistentDAO<T, K> {

    //private final Logger logger   = LoggerFactory.getLogger(AbstractHibernateDAO.class);

    protected final Class<T> persistentClass;

    public AbstractHibernateDAO(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public AbstractHibernateDAO() {
        // ... Hibernate
        persistentClass = null;
    }

    @Override
    public T selectByKey(final K key) throws EngineDAOException {
        try {
            //getSession().getTransaction();
            return (T) getSession().get(persistentClass, (Serializable) key);
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() throws EngineDAOException {
        /*try {
            return hibernateTemplate.loadAll(persistentClass);
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }  */
        return new ArrayList<T>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleInstance, final String[] excludeProperty) throws EngineDAOException {
        try {
            final Criteria criteria = getSession().createCriteria(persistentClass);
            final Example example = Example.create(exampleInstance);
            for (final String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
            criteria.add(example);
            return criteria.list();
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }
    }

    @Override
    public List<T> findByExampleExcludeZeroes(final T exampleInstance) throws EngineDAOException {
        try {
            final Criteria criteria = getSession().createCriteria(persistentClass);
            final Example example = Example.create(exampleInstance);
            example.excludeZeroes();
            criteria.add(example);
            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T makePersistent(final T entity) throws EngineDAOException {
        try {
            //logger.debug("Persisting entity of type " + entity.getClass().getName());
            getSession().getTransaction().begin();
            getSession().saveOrUpdate(entity);
            getSession().getTransaction().commit();
        } catch (RuntimeException r) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(r);
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T merge(final T entity) throws EngineDAOException {
        //logger.debug("Persisting entity of type " + entity.getClass().getName());
        try {
            getSession().getTransaction().begin();
            T t = (T) getSession().merge(entity);
            getSession().getTransaction().commit();
            return t;
        } catch (RuntimeException r) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(r);
        }
    }

    @Override
    public List<T> makePersistent(final List<T> entities) throws EngineDAOException {
        try {
            for (final T entity : entities) {
                makePersistent(entity);
            }
            getSession().getTransaction().commit();
            return entities;
        } catch (RuntimeException r) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(r);
        }
    }

    @Override
    public void makeTransient(final T entity) throws EngineDAOException {
        try {
            getSession().getTransaction().begin();
            getSession().delete(entity);
            getSession().getTransaction().commit();
        } catch (RuntimeException he) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(he);

        }
    }

    @Override
    public void makeTransient(final List<T> entities) throws EngineDAOException {
        try {
            for (final T entity : entities) {
                makeTransient(entity);
            }
            getSession().getTransaction().commit();
        } catch (RuntimeException r) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(r);
        }
    }

    @Override
    public void flush() throws EngineDAOException {
        try {
            getSession().flush();
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }
    }

    @Override
    public void clear() throws EngineDAOException {
        try {
            getSession().clear();
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }
    }

    protected org.hibernate.Session getSession() throws EngineDAOException {
        try {
            return HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (RuntimeException r) {
            throw new EngineDAOException(r);
        }
    }

}
