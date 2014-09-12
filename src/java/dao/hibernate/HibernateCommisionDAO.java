/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.CommisionDAO;
import dao.domain.core.Commision;

/**
 *
 * @author Djole
 */
public class HibernateCommisionDAO extends AbstractHibernateDAO<Commision, Long> implements CommisionDAO{
    
    public static final String COMMISION_ID = "commisionID";

    public HibernateCommisionDAO(Class<Commision> persistentClass) {
        super(persistentClass);
    }

    public HibernateCommisionDAO() {
        super(Commision.class);
    }
    
    
}
