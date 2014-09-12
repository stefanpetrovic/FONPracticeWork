/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.exception.EngineDAOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Djole
 */
public class Main {
    
    public static void main(String[] args) {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        try {
            Person p = hpd.getPersonByUsernameAndPassword("probros", "123");
            System.out.println(p.getStudent().getIndexNo().toString());
        } catch (EngineDAOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
