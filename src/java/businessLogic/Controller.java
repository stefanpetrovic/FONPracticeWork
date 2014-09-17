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
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Title;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import dao.hibernate.HibernateCourseDAO;
import dao.hibernate.HibernateDepartmentDAO;
import dao.hibernate.HibernateEmployeeDAO;
import dao.hibernate.HibernatePersonDAO;
import dao.hibernate.HibernateStudentDAO;
import dao.hibernate.HibernateSubjectDAO;
import dao.hibernate.HibernateTitleDAO;
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

    public ArrayList<Work> searchTheses(String heading, String keywords, Course course) throws EngineDAOException {
        /*
        Dakle metoda prima ova tri parametra. Upit treba da bude ovako:
        - ako je heading postavljen - onda pretraga po naslovima za sve radove (LIKE klauzula)
        - ako je postavljen keywords - onda pretraga za svaku ključnu reč. Rad mora
        da sadrži sve ove ključne reči. Reči se unose razdvojene sa zarezom, pa ćeš morati da splituješ string, trimuješ razmake
        i pretražuješ svaku reč ponaosob.
        - ako je course odabran, onda samo radovi gde je taj predmet.
        
        Ako je više od ovih postavljeno, onda se spajaju AND klauzulom. Npr: naslov "deo naslova" i "ključnareč1,ključnareč2"
        dakle sadrži i jedno i drugo...
        */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    public void addThesisRequest(Work thesis) {
        // dodati cuvanje work-a(podaci koji se dobijaju ovde su profesor, student, naslov)
        //obavezno da baca exception

    }
}
