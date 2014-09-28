/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Communication;
import dao.domain.core.Employee;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface CommunicationDAO extends BasePersistentDAO<Communication, Long>{
    
    public List<Communication> getCommunicationsByEmployee(Employee employee) throws EngineDAOException;
    public List<Communication> getCommunicationsByStudent(Student student) throws EngineDAOException;
    public Communication getCommunicationByEmployeeAndStudent(Employee employee,Student student) throws EngineDAOException;
}
