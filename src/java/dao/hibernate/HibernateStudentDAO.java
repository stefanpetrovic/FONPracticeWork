/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.StudentDAO;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
import java.text.MessageFormat;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateStudentDAO extends AbstractHibernateDAO<Student, Long> implements StudentDAO{

    public static final String STUDENT_ID = "studentID";
    public static final String INDEX_NO = "indexNo";
    public static final String COURSE = "course";
    public static final String JMBG = "jmbg";
    
    public static final String ERROR_STUDENT_NOT_FOUND_BY_PERSON = "Student not found by person";

    public HibernateStudentDAO(Class<Student> persistentClass) {
        super(persistentClass);
    }

    public HibernateStudentDAO() {
        super(Student.class);
    }  
        
    @Override
    public Student getStudentByPerson(Person person) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(STUDENT_ID, person.getPersonID()));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Student student = null;
        try {
            student = (Student) criteria.uniqueResult();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (student == null) {
            throw new EngineDAOException(MessageFormat.format(ERROR_STUDENT_NOT_FOUND_BY_PERSON, null));
        }
        return student;
    }

    @Override
    public Student getStudentByIndexNo(String indexNo) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(INDEX_NO, indexNo));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Student student = null;
        try {
            student = (Student) criteria.uniqueResult();
        } catch (RuntimeException e) {
            getSession().getTransaction().commit();
            throw new EngineDAOException(e);
        }
        if (student == null) {
            getSession().getTransaction().commit();
            throw new EngineDAOException(MessageFormat.format(ERROR_STUDENT_NOT_FOUND_BY_PERSON, null));
        }
        getSession().getTransaction().commit();
        return student;
    }

    @Override
    public Student getStudentByIndexNoAndJMBG(String indexNo, String jmbg) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(INDEX_NO, indexNo));
        criteria.add(Restrictions.eq(JMBG, jmbg));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Student student = null;
        try {
            student = (Student) criteria.uniqueResult();
        } catch (RuntimeException e) {
            getSession().getTransaction().commit();
            throw new EngineDAOException(e);
        }
        if (student == null) {
            getSession().getTransaction().commit();
            throw new EngineDAOException(MessageFormat.format(ERROR_STUDENT_NOT_FOUND_BY_PERSON, null));
        }
        getSession().getTransaction().commit();
        return student;
    }

    @Override
    public Student getStudentByJMBG(String jmbg) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(JMBG, jmbg));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Student student = null;
        try {
            student = (Student) criteria.uniqueResult();
        } catch (RuntimeException e) {
            getSession().getTransaction().commit();
            throw new EngineDAOException(e);
        }
        if (student == null) {
            getSession().getTransaction().commit();
            throw new EngineDAOException(MessageFormat.format(ERROR_STUDENT_NOT_FOUND_BY_PERSON, null));
        }
        getSession().getTransaction().commit();
        return student;
    }
    
}
