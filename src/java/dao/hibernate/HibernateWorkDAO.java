/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.WorkDAO;
import dao.domain.core.Work;

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

    public HibernateWorkDAO(Class<Work> persistentClass) {
        super(persistentClass);
    }

    public HibernateWorkDAO() {
        super(Work.class);
    }
    
    
    
}
