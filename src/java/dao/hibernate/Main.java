/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.domain.core.Course;
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
        HibernateStudentDAO spd = new HibernateStudentDAO();
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        Person p = new Person();
        Student s = new Student();
        try {
            p.setEmail("visionary");
            p.setName("Goran");
            p.setPassword("123");
            p.setPictureURI("ololol");
            p.setSurname("Maksimovic");
            p.setUsername("Rango");
            s.setCourse(hcd.selectByKey(new Long("1")));
            s.setIndexNo("38");
            s.setJmbg("222323");
            s.setPerson(p);
            s.setWorkCollection(null);
            hpd.makePersistent(p);
            p = hpd.getPersonByUsernameAndPassword(p.getUsername(), p.getPassword());
            s.setStudentID(p.getPersonID());
            
            spd.makePersistent(s);
        } catch (EngineDAOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
