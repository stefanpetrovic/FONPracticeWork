/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.SubjectDAO;
import dao.domain.core.Department;
import dao.domain.core.Person;
import dao.domain.core.Subject;
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
public class HibernateSubjectDAO extends AbstractHibernateDAO<Subject, Long> implements SubjectDAO{
    
    public static final String SUBJECT_ID = "subjectID";
    public static final String NAME = "name";
    public static final String DEPARTMENT = "department";
    public static final String COURSE = "course";

    public HibernateSubjectDAO(Class<Subject> persistentClass) {
        super(persistentClass);
    }

    public HibernateSubjectDAO() {
        super(Subject.class);
    }

    @Override
    public List<Subject> getSubjectsByDepartments(Department department) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(DEPARTMENT, department));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Subject> subjects = null;
        try {
            subjects = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (subjects == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return subjects;  
    }
    
    
    
}
