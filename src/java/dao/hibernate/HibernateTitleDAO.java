/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.TitleDAO;
import dao.domain.core.Title;

/**
 *
 * @author Djole
 */
public class HibernateTitleDAO extends AbstractHibernateDAO<Title, Long> implements TitleDAO{
    
    public static final String TITLE_ID = "titleID";
    public static final String NAME = "name";

    public HibernateTitleDAO(Class<Title> persistentClass) {
        super(persistentClass);
    }

    public HibernateTitleDAO() {
        super(Title.class);
    }
    
    
    
}
