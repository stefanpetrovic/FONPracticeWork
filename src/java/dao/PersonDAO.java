/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Person;
import dao.exception.EngineDAOException;

/**
 *
 * @author Djole
 */
public interface PersonDAO extends BasePersistentDAO<Person, Long>{
    
    public Person getPersonByUsernameAndPassword(String username, String password) throws EngineDAOException;
}
