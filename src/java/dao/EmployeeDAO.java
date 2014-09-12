/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Department;
import dao.domain.core.Employee;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface EmployeeDAO extends BasePersistentDAO<Employee, Long>{
    
    public List<Employee> getEmployeeByDepartment(Department department);
    
}
