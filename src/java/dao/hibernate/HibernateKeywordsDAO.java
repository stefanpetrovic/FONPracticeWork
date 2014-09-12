/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.KeywordsDAO;
import dao.domain.core.Keywords;

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
    
    
    
}
