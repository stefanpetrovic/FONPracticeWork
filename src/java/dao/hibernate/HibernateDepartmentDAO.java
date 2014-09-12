/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.domain.core.Department;

/**
 *
 * @author Djole
 */
public class HibernateDepartmentDAO extends AbstractHibernateDAO<Department, Long>{
    
    public static final String DEPARTMENT_ID = "departmentID";
    public static final String NAME = "name";
    public static final String CHIEF = "chief";
    public static final String FACULTY = "faculty";

    public HibernateDepartmentDAO(Class<Department> persistentClass) {
        super(persistentClass);
    }

    public HibernateDepartmentDAO() {
        super(Department.class);
    }
    
    
    
}
