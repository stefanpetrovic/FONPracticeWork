/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hibernate;

import businessLogic.Controller;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.domain.core.Course;
import dao.domain.core.Employee;
import dao.domain.core.Keywords;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
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
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        HibernateKeywordsDAO hkd = new HibernateKeywordsDAO();
        HibernateSubjectDAO hsd = new HibernateSubjectDAO();
        Person p = new Person();
        Student s = new Student();
        try {
            /*p.setEmail("visionary1");
            p.setName("Goran");
            p.setPassword("123");
            p.setPictureURI("ololol");
            p.setSurname("Maksimovic");
            p.setUsername("Rango1");
            s.setCourse(hcd.selectByKey(new Long("1")));
            s.setIndexNo("381");
            s.setJmbg("222323");
            s.setPerson(p);
            s.setWorkCollection(null);
            //hpd.makePersistent(p);
            //p = hpd.getPersonByUsernameAndPassword(p.getUsername(), p.getPassword());
            //s.setStudentID(p.getPersonID());
            
            Controller.getInstance().addStudent(s);*/
            /*List<Person> persons = new ArrayList<>();
            persons = hpd.getPersonsByName("Sinisa");
            System.out.println(persons.get(0).getEmployee());
            System.out.println(Controller.getInstance().isUsernameUnique("probros"));*/
            /*Person person = hpd.selectByKey(new Long("1"));
            person.setName("Djordje");
            person.getStudent().setJmbg("212");
            Controller.getInstance().updatePerson(person);*/
            /*String s1 = "goran";
            String s2 = "maturski";
            List<String> str = new ArrayList<>();
            str.add(s1);
            str.add(s2);
            List<Work> work = new ArrayList<>();
            Subject subject = hsd.selectByKey(new Long("1"));
            work = Controller.getInstance().searchTheses(null, str, null);
            for(Work w : work){
                System.out.println(w.getTitle());
                System.out.println(w.getSubject().getName());
            }
            /*List<Keywords> keys = new ArrayList<>();
            for(String k : str){
                Keywords keyword = new Keywords();
                keyword.setKeyword(k);
                keys.add(keyword);
            }
            keys = hkd.getKeywordsByKeywords(keys);
            for(Keywords k : keys){
                System.out.println(k.getKeywordsID());
                System.out.println(k.getKeyword());
                System.out.println(k.getWork());
            }*/
            p = Controller.getInstance().login("probros", "123");
            System.out.println(p);
            System.out.println(p.getEmployee());
            System.out.println(p.getEmployee().getEmployeeSubjectList());
        } catch (EngineDAOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
