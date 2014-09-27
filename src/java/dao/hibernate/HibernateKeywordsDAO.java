/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.KeywordsDAO;
import dao.domain.core.Keywords;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import static dao.hibernate.HibernatePersonDAO.ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD;
import static dao.hibernate.HibernateWorkDAO.TITLE;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Djole
 */
public class HibernateKeywordsDAO extends AbstractHibernateDAO<Keywords, Long> implements KeywordsDAO{
    
    public static final String KEYWORDS_ID = "keywordsID";
    public static final String WORK = "work";
    public static final String KEYWORD = "keyword";

    public HibernateKeywordsDAO(Class<Keywords> persistentClass) {
        super(persistentClass);
    }

    public HibernateKeywordsDAO() {
        super(Keywords.class);
    }

    @Override
    public List<Keywords> getKeywordsByKeywords(List<Keywords> keywords) throws EngineDAOException {
        List<Keywords> keys = new ArrayList<>();
        for(Keywords k : keywords){
            getSession().beginTransaction();
            Criteria criteria = getSession().createCriteria(persistentClass);
            criteria.add(Restrictions.like(KEYWORD, "%"+k.getKeyword()+"%"));
            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            Keywords keyword;
            try {
                keyword = (Keywords) criteria.uniqueResult();
            } catch (RuntimeException e) {
                throw new EngineDAOException(e);
            }
            if (keyword == null) {
                getSession().getTransaction().rollback();
                throw new EngineDAOException(MessageFormat.format(ERROR_PERSON_NOT_FOUND_BY_USERNAME_AND_PASSWORD, null));            
            }
            getSession().getTransaction().commit();
            keys.add(keyword);
        }
        return keys;
    }
    
    
    
}
