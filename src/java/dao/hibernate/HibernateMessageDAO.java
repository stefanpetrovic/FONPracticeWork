/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.MessageDAO;
import dao.domain.core.Communication;
import dao.domain.core.Message;
import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import static dao.hibernate.HibernatePersonDAO.ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD;
import static dao.hibernate.HibernatePersonDAO.NAME;
import java.text.MessageFormat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateMessageDAO extends AbstractHibernateDAO<Message, Long> implements MessageDAO{
    
    public static final String MESSAGE_ID = "messageID";
    public static final String TEXT = "text";
    public static final String FILE_URI = "fileURI";
    public static final String SENDER = "sender";
    public static final String RECIEVER = "reciever";
    public static final String COMMUNICATION = "communication";
    public static final String READ = "isRead";

    public HibernateMessageDAO(Class<Message> persistentClass) {
        super(persistentClass);
    }

    public HibernateMessageDAO() {
        super(Message.class);
    }

    @Override
    public List<Message> getMessagesByCommunication(Communication com) throws EngineDAOException {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(COMMUNICATION, com));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Message> messages = null;
        try {
            messages = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (messages == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return messages;  
    }

    @Override
    public List<Message> getUnreadMessages(Person person) throws EngineDAOException{
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(RECIEVER, person));
        criteria.add(Restrictions.eq(READ, false));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Message> messages = null;
        try {
            messages = criteria.list();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (messages == null) {
            getSession().getTransaction().rollback();
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
        }
        getSession().getTransaction().commit();
        return messages;
    }
    
    
}
