/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.CommisionMemberDAO;
import dao.domain.core.CommisionMember;

/**
 *
 * @author Djole
 */
public class HibernateCommisionMemberDAO extends AbstractHibernateDAO<CommisionMember, Long> implements CommisionMemberDAO{
    
    public static final String COMMISION_MEMBER_ID = "commision_memberID";
    public static final String COMMISION = "commisionID";
    public static final String PROFESSOR = "professor";
    public static final String ROLE = "role";

    public HibernateCommisionMemberDAO(Class<CommisionMember> persistentClass) {
        super(persistentClass);
    }

    public HibernateCommisionMemberDAO() {
        super(CommisionMember.class);
    }
    
    
    
}
