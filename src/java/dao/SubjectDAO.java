/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Department;
import dao.domain.core.Subject;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface SubjectDAO extends BasePersistentDAO<Subject, Long>{
    
    public List<Subject> getSubjectsByDepartments(Department department) throws EngineDAOException;
}
