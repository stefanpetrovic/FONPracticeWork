/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.EmployeeDAO;
import dao.domain.core.Department;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import static dao.hibernate.HibernatePersonDAO.ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD;
import static dao.hibernate.HibernatePersonDAO.PASSWORD;
import static dao.hibernate.HibernatePersonDAO.USERNAME;
import java.text.MessageFormat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateEmployeeDAO extends AbstractHibernateDAO<Employee, Long> implements EmployeeDAO{
    
    public static final String EMPLOYEE_ID = "employeeID";
    public static final String DEPARTMENT = "department";
    public static final String SUBJECT = "subject";
    public static final String TITLE = "title";
    
    public HibernateEmployeeDAO(){
        super(Employee.class);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    public Person getPersonByUsernameAndPassword(String username, String password) throws EngineDAOException{
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(USERNAME, username));
        criteria.add(Restrictions.eq(PASSWORD, password));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Person person = null;
        try {
            person = (Person) criteria.uniqueResult();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (person == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return person;
    }*/

    @Override
    public List<Employee> getEmployeesByName(String name) throws EngineDAOException{
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(HibernatePersonDAO.NAME, name));
        //criteria.add(Restrictions.eq(PASSWORD, password));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Employee> employees = null;
        try {
            employees = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (employees == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return employees;    }
    
    
}
