/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.PersonDAO;
import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import java.text.MessageFormat;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernatePersonDAO extends AbstractHibernateDAO<Person, Long> implements PersonDAO{
    
    public static final String PERSON_ID = "personID";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PICTURE_URI = "pictureURI";
    
    public static final String ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD = "Person not found by username and password";

    public HibernatePersonDAO() {
        super(Person.class);
    }

    @Override
    public Person getPersonByUsernameAndPassword(String username, String password) throws EngineDAOException{
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(USERNAME, username));
        criteria.add(Restrictions.eq(PASSWORD, password));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Person person = null;
        try {
            person = (Person) criteria.uniqueResult();
        } catch (RuntimeException e) {
            throw new EngineDAOException(e);
        }
        if (person == null) {
            throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));
        }
        return person;
    }
    
    
}
