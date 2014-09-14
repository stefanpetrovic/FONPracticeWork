/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic;

import dao.domain.core.Course;
import dao.domain.core.Department;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Title;
import dao.exception.EngineDAOException;
import dao.hibernate.HibernatePersonDAO;
import dao.hibernate.HibernateStudentDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for communication with database.
 * @author stefan
 */

public class Controller {
    private static Controller instance;
    
    private Controller() {
        
    }
    
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }
    //this should be connected to HibernateTitleDAO
    public List<Title> getTitles() {
        return new ArrayList<Title>();
    }
    
    //this should be connected to HibernateDepartmentDAO
    public List<Department> getDepartments() {
        return new ArrayList<Department>();
    }
    
    //this should be connected to HibernateCourseDAO
    public List<Course> getCourses() {
        return new ArrayList<Course>();
    }
    
    public Person login(String username, String password) throws EngineDAOException {
        HibernatePersonDAO hpDAO = new HibernatePersonDAO();
        return hpDAO.getPersonByUsernameAndPassword(username, password);
    }
    
    public void addEmployee(Employee employee) {
        //insert code that saves employee via DAO
    }
    
    public void addStudent(Student student) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateStudentDAO spd = new HibernateStudentDAO();
        Person p = student.getPerson();
        p = hpd.getPersonByUsername(p.getUsername());
        if(p == null){
           return;
        }

        hpd.makePersistent(p);
        p = hpd.getPersonByUsername(p.getUsername());
        student.setStudentID(p.getPersonID());
        spd.makePersistent(student);
    }
}
