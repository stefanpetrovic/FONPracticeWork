/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Communication;
import dao.domain.core.Message;
import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface MessageDAO extends BasePersistentDAO<Message, Long>{
    
    public List<Message> getMessagesByCommunication(Communication com) throws EngineDAOException;
    public List<Message> getUnreadMessages(Person person) throws EngineDAOException;
}
