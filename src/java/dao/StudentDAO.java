/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface StudentDAO extends BasePersistentDAO<Student, Long>{

    public Student getStudentByPerson(Person person) throws EngineDAOException;
    public Student getStudentByIndexNo(String indexNo) throws EngineDAOException;
    public Student getStudentByIndexNoAndJMBG(String indexNo, String jmbg) throws EngineDAOException;
    public Student getStudentByJMBG(String jmbg) throws EngineDAOException;




}
