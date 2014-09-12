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

    //String ENTITY_NAME = "USERINFOENTITY";

    //List<Student> getUsersByName(String name) throws EngineDAOException;
    public Student getStudentByPerson(Person person) throws EngineDAOException;
    /*UserinfoEntity getUserById(Long id) throws EngineDAOException;
    List<UserinfoEntity> getUsersByLastName(String lastName) throws EngineDAOException;
    List<UserinfoEntity> getUsersByNameAndLastName(String name,String lastName) throws EngineDAOException;
    UserinfoEntity getUserByEmail(String email) throws EngineDAOException;
    List<UserinfoEntity> getUsersByLocation(String location) throws EngineDAOException;*/




}
