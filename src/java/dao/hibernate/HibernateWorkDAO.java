/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.WorkDAO;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import static dao.hibernate.HibernatePersonDAO.ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD;
import static dao.hibernate.HibernatePersonDAO.PASSWORD;
import static dao.hibernate.HibernatePersonDAO.USERNAME;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateWorkDAO extends AbstractHibernateDAO<Work, Long> implements WorkDAO{
    
    public static final String WORK_ID = "workID";
    public static final String TITLE = "title";
    public static final String FINAL_FILE_URI = "finalFileURI";
    public static final String ACCEPTANCE_DATE = "acceptanceDate";
    public static final String EXAM_DATE = "examDate";
    public static final String STUDENT = "student";
    public static final String MENTOR = "mentor";
    public static final String COMMISION = "commision";
    public static final String GRADE = "grade";
    public static final String STATUS = "status";
    public static final String DESCRIPTION = "description";
    public static final String SUBJECT = "subject";

    public static final int REJECTED = -1;
    public static final int UNAPPROVED = 0;
    public static final int APPROVED = 1;
    
    public HibernateWorkDAO(Class<Work> persistentClass) {
        super(persistentClass);
    }

    public HibernateWorkDAO() {
        super(Work.class);
    }

    @Override
    public List<Work> getWorkByTitle(String title) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(TITLE, title));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> works;
        try {
            works = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (works == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return works;
    }

    @Override
    public List<Work> getWorksByTitleAndSubject(String title, Subject subject) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(TITLE, title));
        criteria.add(Restrictions.eq(SUBJECT, subject));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> works;
        try {
            works = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (works == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return works;
    }

    @Override
    public List<Work> getWorksBySubject(Subject subject) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(SUBJECT, subject));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> works;
        try {
            works = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (works == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return works;
    }

    @Override
    public List<Work> getUnapprovedWorks() throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(STATUS, UNAPPROVED));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> works;
        try {
            works = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (works == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return works;
    }

    @Override
    public List<Work> getUncommisionedWorks() throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(COMMISION, null));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> works;
        try {
            works = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (works == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return works;
    }

    @Override
    public List<Work> getUngradedWorks() throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(GRADE, null));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> works;
        try {
            works = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (works == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return works;
    }

    @Override
    public List<Work> getApprovedWorkByStudentWithoutFinalURI(Student student) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.isNull(FINAL_FILE_URI));
        criteria.add(Restrictions.eq(STUDENT, student));
        criteria.add(Restrictions.eq(STATUS, APPROVED));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Work> work = new ArrayList<>();
        try {
            work = criteria.list();
            System.out.println(work);
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (work == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return work;
    }

}
