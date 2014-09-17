/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.EmployeeSubjectDAO;
import dao.domain.core.Employee;
import dao.domain.core.EmployeeSubject;
import dao.domain.core.Subject;
import dao.exception.EngineDAOException;
import static dao.hibernate.HibernatePersonDAO.ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD;
import java.text.MessageFormat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateEmployeeSubjectDAO extends AbstractHibernateDAO<EmployeeSubject, Long> implements EmployeeSubjectDAO{
    
    public static final String EMPLOYEE_SUBJECT_ID = "employee_subjectID";
    public static final String EMPLOYEE = "employee";
    public static final String SUBJECT = "subject";

    public HibernateEmployeeSubjectDAO(Class<EmployeeSubject> persistentClass) {
        super(persistentClass);
    }

    public HibernateEmployeeSubjectDAO() {
        super(EmployeeSubject.class);
    }

    
    @Override
    public List<EmployeeSubject> getSubjectsByEmployee(Employee employee) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(EMPLOYEE, employee));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<EmployeeSubject> employeesSubject = null;
        try {
            employeesSubject = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (employeesSubject == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return employeesSubject;
    }

}
