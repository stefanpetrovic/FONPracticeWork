/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Employee;
import dao.domain.core.EmployeeSubject;
import dao.domain.core.Subject;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface EmployeeSubjectDAO extends BasePersistentDAO<EmployeeSubject, Long>{
    
    public List<EmployeeSubject> getSubjectsByEmployee(Employee employee) throws EngineDAOException;
}
