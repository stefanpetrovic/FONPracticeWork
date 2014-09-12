/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.CourseDAO;
import dao.domain.core.Course;

/**
 *
 * @author Djole
 */
public class HibernateCourseDAO extends AbstractHibernateDAO<Course, Long> implements CourseDAO{
    
    public static final String COURSE_ID = "courseID";
    public static final String NAME = "name";

    public HibernateCourseDAO(Class<Course> persistentClass) {
        super(persistentClass);
    }

    public HibernateCourseDAO() {
        super(Course.class);
    }
    
    
    
}
