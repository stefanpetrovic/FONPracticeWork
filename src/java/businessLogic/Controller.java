/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic;

import dao.domain.core.Commision;
import dao.domain.core.CommisionMember;
import dao.domain.core.Course;
import dao.domain.core.Department;
import dao.domain.core.Employee;
import dao.domain.core.Keywords;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Title;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import dao.hibernate.HibernateCourseDAO;
import dao.hibernate.HibernateDepartmentDAO;
import dao.hibernate.HibernateEmployeeDAO;
import dao.hibernate.HibernateKeywordsDAO;
import dao.hibernate.HibernatePersonDAO;
import dao.hibernate.HibernateStudentDAO;
import dao.hibernate.HibernateSubjectDAO;
import dao.hibernate.HibernateTitleDAO;
import dao.hibernate.HibernateWorkDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<Title> getTitles() throws EngineDAOException {
        HibernateTitleDAO htd = new HibernateTitleDAO();
        return htd.findAll();
    }
    
    //this should be connected to HibernateDepartmentDAO
    public List<Department> getDepartments() throws EngineDAOException {
        HibernateDepartmentDAO hdd = new HibernateDepartmentDAO();
        return hdd.findAll();
    }
    
    //this should be connected to HibernateCourseDAO
    public List<Course> getCourses() throws EngineDAOException {
        HibernateCourseDAO hcd = new HibernateCourseDAO(Course.class);
        return hcd.findAll();
    }
    
    public List<Subject> getSubjects() throws EngineDAOException {
        HibernateSubjectDAO hsd = new HibernateSubjectDAO(Subject.class);
        return hsd.findAll();
    }
    
    public Person login(String username, String password) throws EngineDAOException {
        HibernatePersonDAO hpDAO = new HibernatePersonDAO();
        return hpDAO.getPersonByUsernameAndPassword(username, password);
    }
    
    public void addEmployee(Employee employee) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        if(isUsernameUnique(employee.getPerson().getUsername()) && isEmailUnique(employee.getPerson().getUsername())){
            hpd.makePersistent(employee.getPerson());
        }else{
            throw new EngineDAOException("Username or Email already exist");
        }
        Person person = hpd.getPersonByUsername(employee.getPerson().getUsername());
        employee.setEmployeeID(person.getPersonID());
        hed.makePersistent(employee);
    }
    
    public void addStudent(Student student) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateStudentDAO spd = new HibernateStudentDAO(Student.class);
        if(isUsernameUnique(student.getPerson().getUsername()) && isEmailUnique(student.getPerson().getUsername()) && isJMBGUnique(student.getJmbg()) && isIndexNoUnique(student.getIndexNo())){
            hpd.makePersistent(student.getPerson());
            Person person = hpd.getPersonByUsername(student.getPerson().getUsername());
            student.setStudentID(person.getPersonID());
            spd.makePersistent(student);
        }else{
            throw new EngineDAOException("Username, Email, JMBG or Index Number already exist.");
        }      
    }
    
    public Student getStudentById(Long id) throws EngineDAOException{
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        return hsd.selectByKey(id);
    }
    
    public Employee getEmployeeById(Long id) throws EngineDAOException{
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        return hed.selectByKey(id);
    }
    
    public List<Employee> getAllProfessors() {
        //treba da vrati sve profesore-zaposlene i obavezno da baci izuzetak
        return new ArrayList<>();
    }
    
    public List<Subject> getAllSubjectsByProfessor(Employee employee) {
        // na osnovu zaposlenog vratiti sve predmete,obavezno exception da baca
        return new ArrayList<>();
    }

    public void updatePerson(Person person) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        hpd.makePersistent(person);
        if(person.getStudent()!=null){
            hsd.makePersistent(person.getStudent());
        }
        if(person.getEmployee()!=null){
            hed.makePersistent(person.getEmployee());
        }
    }
    
    /*public void makeCommision(Commision commision, List<CommisionMember> commisionMembers) throws EngineDAOException{
        HibernateCommisionDAO hcd = new HibernateCommisionDAO();
        HibernateCommisionMemberDAO hcmd = new HibernateCommisionMemberDAO();
        hcd.makePersistent(commision);
        Commision c = 
    }*/
    public boolean isUsernameUnique(String username){
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        try{
            hpd.getPersonByUsername(username);
            return false;
        }catch(EngineDAOException ex){
            return true;
        }
    }
    
    public boolean isIndexNoUnique(String indexNo){
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        try{
            hsd.getStudentByIndexNo(indexNo);
            return false;
        }catch(EngineDAOException ex){
            return true;
        }
    }
    
    public boolean isEmailUnique(String email){
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        try{
            hpd.getPersonByEmail(email);
            return false;
        }catch(EngineDAOException ex){
            return true;
        }
    }
    
    public boolean isJMBGUnique(String jmbg) {
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        try{
            hsd.getStudentByJMBG(jmbg);
            return false;
        }catch(EngineDAOException ex){
            return true;
        }
    } 

    public List<Work> searchTheses(String heading, List<String> keywords, Subject subject) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        HibernateKeywordsDAO hkd = new HibernateKeywordsDAO();
        List<Work> finalWorks = new ArrayList<>();
        if(heading!=null && keywords==null && subject==null){
            return hwd.getWorkByTitle(heading);
        }
        if(heading!=null && keywords==null && subject!=null){
            return hwd.getWorksByTitleAndSubject(heading, subject);
        }
        if(keywords!=null){
            List<Keywords> keys = new ArrayList<>();
            for(String k : keywords){
                Keywords keyword = new Keywords();
                keyword.setKeyword(k);
                keys.add(keyword);
            }
            keys = hkd.getKeywordsByKeywords(keys);
            List<Work> works = new ArrayList<>();
            if(heading!=null && subject==null){
                works = hwd.getWorkByTitle(heading);
            }
            if(heading!=null && subject!=null){
                works = hwd.getWorksByTitleAndSubject(heading, subject);
            }
            if(heading==null && subject!=null){
                works = hwd.getWorksBySubject(subject);
            }
            if(heading==null && subject==null){
                works = hwd.findAll();
            }
            for(Keywords k : keys){
                for(Work work : works){
                    if(k.getWork().getWorkID().equals(work.getWorkID()) && !finalWorks.contains(work)){
                        finalWorks.add(work);
                    }
                }
            }
            return finalWorks;
        }
        if(heading==null && keywords==null && subject!=null){
            return hwd.getWorksBySubject(subject);
        }
        return finalWorks;

    }
    public void addThesisRequest(Work thesis) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        hwd.makePersistent(thesis);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(Controller.getInstance().getSubjects());
        } catch (EngineDAOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
