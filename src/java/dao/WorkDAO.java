/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface WorkDAO {
    
    public List<Work> getWorkByTitle(String title) throws EngineDAOException;
    public List<Work> getWorksByTitleAndSubject(String title, Subject subject) throws EngineDAOException;
    public List<Work> getWorksBySubject(Subject subject) throws EngineDAOException;
    public List<Work> getUnapprovedWorks() throws EngineDAOException;
    public List<Work> getUncommisionedWorks() throws EngineDAOException;
    public List<Work> getUngradedWorks() throws EngineDAOException;
    public List<Work> getApprovedWorkByStudentWithoutFinalURI(Student student) throws EngineDAOException;
    
}
