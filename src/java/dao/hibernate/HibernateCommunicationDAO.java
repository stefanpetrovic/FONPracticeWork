/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.CommunicationDAO;
import dao.domain.core.Communication;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
import static dao.hibernate.HibernatePersonDAO.ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD;
import static dao.hibernate.HibernatePersonDAO.NAME;
import java.text.MessageFormat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateCommunicationDAO extends AbstractHibernateDAO<Communication, Long> implements CommunicationDAO{
    
    public static final String COMMUNICATION_ID = "communicationID";
    public static final String EMPLOYEE = "employee";
    public static final String STUDENT = "student";
    public static final String LOCKED = "locked";

    public HibernateCommunicationDAO(Class<Communication> persistentClass) {
        super(persistentClass);
    }

    public HibernateCommunicationDAO() {
        super(Communication.class);
    }

    @Override
    public List<Communication> getCommunicationsByEmployee(Employee employee) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(EMPLOYEE, employee));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Communication> cs = null;
        try {
            cs = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (cs == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return cs; 
    }

    @Override
    public List<Communication> getCommunicationsByStudent(Student student) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(STUDENT, student));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Communication> cs = null;
        try {
            cs = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (cs == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return cs; 
    }
    
    
}
