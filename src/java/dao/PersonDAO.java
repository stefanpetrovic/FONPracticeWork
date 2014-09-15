/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface PersonDAO extends BasePersistentDAO<Person, Long>{
    
    public Person getPersonByUsernameAndPassword(String username, String password) throws EngineDAOException;
    public Person getPersonByUsername(String username) throws EngineDAOException;
    public List<Person> getPersonsByName(String name) throws EngineDAOException;
    public List<Person> getPersonsByLastname(String lastname) throws EngineDAOException;
    public List<Person> getPersonsByNameAndLastname(String name, String lastname) throws EngineDAOException;
    public Person getPersonByEmail(String email) throws EngineDAOException;
}
