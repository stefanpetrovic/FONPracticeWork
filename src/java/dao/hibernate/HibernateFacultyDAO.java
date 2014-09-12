/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.FacultyDAO;
import dao.domain.core.Faculty;

/**
 *
 * @author Djole
 */
public class HibernateFacultyDAO extends AbstractHibernateDAO<Faculty, Long> implements FacultyDAO{
    
    public static final String FACULTY_ID = "facultyID";
    public static final String NAME = "name";

    public HibernateFacultyDAO(Class<Faculty> persistentClass) {
        super(persistentClass);
    }

    public HibernateFacultyDAO() {
        super(Faculty.class);
    }
    
    
    
}
