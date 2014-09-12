/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.EmployeeDAO;
import dao.domain.core.Department;
import dao.domain.core.Employee;
import java.util.List;

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
    public List<Employee> getEmployeeByDepartment(Department department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
