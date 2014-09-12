/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.SubjectDAO;
import dao.domain.core.Subject;

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
    
    
    
}
