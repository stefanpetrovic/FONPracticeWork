/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import dao.domain.core.Commision;
import dao.domain.core.CommisionMember;
import dao.domain.core.Communication;
import dao.domain.core.Course;
import dao.domain.core.Department;
import dao.domain.core.Employee;
import dao.domain.core.EmployeeSubject;
import dao.domain.core.Keywords;
import dao.domain.core.Message;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Title;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import dao.hibernate.HibernateCommisionDAO;
import dao.hibernate.HibernateCommisionMemberDAO;
import dao.hibernate.HibernateCommunicationDAO;
import dao.hibernate.HibernateCourseDAO;
import dao.hibernate.HibernateDepartmentDAO;
import dao.hibernate.HibernateEmployeeDAO;
import dao.hibernate.HibernateEmployeeSubjectDAO;
import dao.hibernate.HibernateKeywordsDAO;
import dao.hibernate.HibernateMessageDAO;
import dao.hibernate.HibernatePersonDAO;
import dao.hibernate.HibernateStudentDAO;
import dao.hibernate.HibernateSubjectDAO;
import dao.hibernate.HibernateTitleDAO;
import dao.hibernate.HibernateWorkDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for communication with database.
 *
 * @author stefan
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
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

    public void addEmployee(EmployeeSubject employeeSubject) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        HibernateEmployeeSubjectDAO hesd = new HibernateEmployeeSubjectDAO();
        if (isUsernameUnique(employeeSubject.getEmployee().getPerson().getUsername()) && isEmailUnique(employeeSubject.getEmployee().getPerson().getUsername())) {
            hpd.makePersistent(employeeSubject.getEmployee().getPerson());
        } else {
            throw new EngineDAOException("Username or Email already exist");
        }
        Person person = hpd.getPersonByUsername(employeeSubject.getEmployee().getPerson().getUsername());
        employeeSubject.getEmployee().setEmployeeID(person.getPersonID());
        hed.makePersistent(employeeSubject.getEmployee());
        hesd.makePersistent(employeeSubject);
    }

    public void addStudent(Student student) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateStudentDAO spd = new HibernateStudentDAO(Student.class);
        Person pers = student.getPerson();
        if (isUsernameUnique(pers.getUsername()) && isEmailUnique(pers.getUsername()) && isJMBGUnique(student.getJmbg()) && isIndexNoUnique(student.getIndexNo())) {
            
            hpd.makePersistent(pers);
            Person person = hpd.getPersonByUsername(pers.getUsername());
            student.setStudentID(person.getPersonID());
            spd.makePersistent(student);
        } else {
            throw new EngineDAOException("Username, Email, JMBG or Index Number already exist.");
        }
    }

    public Student getStudentById(Long id) throws EngineDAOException {
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        return hsd.selectByKey(id);
    }

    public Employee getEmployeeById(Long id) throws EngineDAOException {
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        return hed.selectByKey(id);
    }

    public List<Employee> getAllProfessors() throws EngineDAOException {
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        return hed.findAll();
    }

    public List<Subject> getAllSubjectsByProfessor(Employee employee) throws EngineDAOException {
        HibernateEmployeeSubjectDAO hesd = new HibernateEmployeeSubjectDAO();
        List<Subject> subjects = new ArrayList<>();
        List<EmployeeSubject> es = hesd.getSubjectsByEmployee(employee);
        for (EmployeeSubject es1 : es) {
            subjects.add(es1.getSubject());
        }
        return subjects;
    }

    public void updatePerson(Person person) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        hpd.makePersistent(person);
        if (person.getStudent() != null) {
            hsd.makePersistent(person.getStudent());
        }
        if (person.getEmployee() != null) {
            hed.makePersistent(person.getEmployee());
        }
    }

    /*public void makeCommision(Commision commision, List<CommisionMember> commisionMembers) throws EngineDAOException{
     HibernateCommisionDAO hcd = new HibernateCommisionDAO();
     HibernateCommisionMemberDAO hcmd = new HibernateCommisionMemberDAO();
     hcd.makePersistent(commision);
     Commision c = 
     }*/
    public boolean isUsernameUnique(String username) {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        try {
            hpd.getPersonByUsername(username);
            return false;
        } catch (EngineDAOException ex) {
            return true;
        }
    }

    public boolean isIndexNoUnique(String indexNo) {
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        try {
            hsd.getStudentByIndexNo(indexNo);
            return false;
        } catch (EngineDAOException ex) {
            return true;
        }
    }

    public boolean isEmailUnique(String email) {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        try {
            hpd.getPersonByEmail(email);
            return false;
        } catch (EngineDAOException ex) {
            return true;
        }
    }

    public boolean isJMBGUnique(String jmbg) {
        HibernateStudentDAO hsd = new HibernateStudentDAO();
        try {
            hsd.getStudentByJMBG(jmbg);
            return false;
        } catch (EngineDAOException ex) {
            return true;
        }
    }
    //student object has firstName, surName or both set
    public List<Student> getStudents(Student student) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        List<Person> persons = new ArrayList<>();
        if(student.getPerson().getName() !=null && student.getPerson().getSurname() != null){
            persons = hpd.getPersonsByNameAndLastname(student.getPerson().getName(), student.getPerson().getSurname());
        }
        if(student.getPerson().getName() != null && student.getPerson().getSurname() ==null){
            persons = hpd.getPersonsByName(student.getPerson().getName());
        }
        if(student.getPerson().getName() == null && student.getPerson().getSurname() != null){
            persons = hpd.getPersonsByLastname(student.getPerson().getSurname());
        }
        List<Student> students = new ArrayList<>();
        for(Person p : persons){
            if (p.getStudent() != null) {
                students.add(p.getStudent());
            }
        }
        return students;
    }
    
    //employee object has firstName, surName or both set
    public List<Employee> getEmployees(Employee employee) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        List<Person> persons = new ArrayList<>();
        if(employee.getPerson().getName() != null && employee.getPerson().getSurname() != null){
            persons = hpd.getPersonsByNameAndLastname(employee.getPerson().getName(), employee.getPerson().getSurname());
        }
        if(employee.getPerson().getName() != null && employee.getPerson().getSurname() == null){
            persons = hpd.getPersonsByName(employee.getPerson().getName());
        }
        if(employee.getPerson().getName() == null && employee.getPerson().getSurname() != null){
            persons = hpd.getPersonsByLastname(employee.getPerson().getSurname());
        }
        List<Employee> employees = new ArrayList<>();
        for(Person p : persons){
            if (p.getEmployee() != null) {
                employees.add(p.getEmployee());
            }
        }
        return employees;
    }

    public List<Work> searchTheses(String heading, List<String> keywords, Subject subject) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        HibernateKeywordsDAO hkd = new HibernateKeywordsDAO();
        List<Work> finalWorks = new ArrayList<>();
        if (heading != null && keywords == null && subject == null) {
            return hwd.getWorkByTitle(heading);
        }
        if (heading != null && keywords == null && subject != null) {
            return hwd.getWorksByTitleAndSubject(heading, subject);
        }
        if (keywords != null) {
            List<Keywords> keys = new ArrayList<>();
            for (String k : keywords) {
                Keywords keyword = new Keywords();
                keyword.setKeyword(k);
                keys.add(keyword);
            }
            keys = hkd.getKeywordsByKeywords(keys);
            List<Work> works = new ArrayList<>();
            if (heading != null && subject == null) {
                works = hwd.getWorkByTitle(heading);
            }
            if (heading != null && subject != null) {
                works = hwd.getWorksByTitleAndSubject(heading, subject);
            }
            if (heading == null && subject != null) {
                works = hwd.getWorksBySubject(subject);
            }
            if (heading == null && subject == null) {
                works = hwd.findAll();
            }
            for (Keywords k : keys) {
                for (Work work : works) {
                    if (k.getWork().getWorkID().equals(work.getWorkID()) && !finalWorks.contains(work)) {
                        finalWorks.add(work);
                    }
                }
            }
            return finalWorks;
        }
        if (heading == null && keywords == null && subject != null) {
            return hwd.getWorksBySubject(subject);
        }
        return finalWorks;

    }

    public void addThesisRequest(Work thesis) throws EngineDAOException {
        thesis.setStatus(0);
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        hwd.makePersistent(thesis);
    }

    public static void main(String[] args) {
        try {
//            Person s = Controller.getInstance().login("Rango1", "123");
//            System.out.println(s.getStudent());
//            Work w = s.getStudent().getWorkList().get(0);
            //System.out.println(w);
            
            //System.out.println(Controller.getInstance().getStudentsCurrentWork(s.getStudent()).get(0));
            Person p = new Person();
            p.setEmail("basdf");
            p.setName("asdfasf");
            p.setPassword("asdfasd");
            p.setPictureURI("");
            p.setSurname("asdfasdf");
            p.setUsername("asdfasdf");
            Student s = new Student();
            s.setPerson(p);
            HibernateCourseDAO hc = new HibernateCourseDAO();
            
            s.setCourse(hc.selectByKey(2L));
            s.setIndexNo("324");
            s.setJmbg("5698745825698");
            
            Controller.getInstance().addStudent(s);
            
        } catch (EngineDAOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCommision(Work work, Employee firstMember, Employee secondMember, Employee thirdMember) throws EngineDAOException {
        HibernateCommisionDAO hcd = new HibernateCommisionDAO();
        HibernateCommisionMemberDAO hcmd = new HibernateCommisionMemberDAO();
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        Commision commision = new Commision();
        //save commision
        hcd.makePersistent(commision);
        //save mentor
        CommisionMember member1 = new CommisionMember();
        member1.setCommisionID(commision);
        member1.setProfessor(firstMember);
        member1.setRole("mentor");
        //save commision members
        CommisionMember member2 = new CommisionMember();
        member2.setCommisionID(commision);
        member2.setProfessor(secondMember);
        member2.setRole("commision member");
        CommisionMember member3 = new CommisionMember();
        member3.setCommisionID(commision);
        member3.setProfessor(thirdMember);
        member3.setRole("commision member");
        work.setCommision(commision);
        //update work
        hwd.makePersistent(work);
    }

    public List<Work> searchUnapprovedTheses() throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        return hwd.getUnapprovedWorks();
    }

    public List<Work> searchUncommisionedTheses() throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        return hwd.getUncommisionedWorks();
    }

    public List<Work> searchUngradedTheses() throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        return hwd.getUngradedWorks();
    }

    public void gradeWork(Work work, int grade) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        work.setGrade(grade);
        hwd.makePersistent(work);
    }

    public List<Integer> createGrades() {
        ArrayList<Integer> availableGrades = new ArrayList<>();
        availableGrades.add(5);
        availableGrades.add(6);
        availableGrades.add(7);
        availableGrades.add(8);
        availableGrades.add(9);
        availableGrades.add(10);
        return availableGrades;

    }

    public Work getWork(Long id) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        return hwd.selectByKey(id);
    }

    public List<Work> getPersonWorks(Person person) throws EngineDAOException {
        HibernatePersonDAO hpd = new HibernatePersonDAO();
        if (person.getStudent() == null) {
            HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
            Employee employee = hed.selectByKey(person.getPersonID());
            return employee.getWorkList();
        } else {
            HibernateStudentDAO hsd = new HibernateStudentDAO();
            Student student = hsd.selectByKey(person.getPersonID());
            return student.getWorkList();
        }
    }

    //returns current work of a student(work that is approved, but doesn't have final version uploaded)
    public List<Work> getStudentsCurrentWork(Student student) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        return hwd.getApprovedWorkByStudentWithoutFinalURI(student);
    }

    public List<Work> getUngradedTheses(Person person) throws EngineDAOException {
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        Employee employee = hed.selectByKey(person.getPersonID());
        List<Work> ungradedWorks = new ArrayList<>();
        for (Work w : employee.getWorkList()) {
            if (w.getGrade() == null) {
                ungradedWorks.add(w);
            }
        }
        return ungradedWorks;
    }

    public List<Work> getUncomissionedTheses(Person person) throws EngineDAOException {
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        Employee employee = hed.selectByKey(person.getPersonID());
        List<Work> uncommisionedWorks = new ArrayList<>();
        for (Work w : employee.getWorkList()) {
            if (w.getCommision() == null) {
                uncommisionedWorks.add(w);
            }
        }
        return uncommisionedWorks;
    }

    public List<Work> getUnaprovedTheses(Person person) throws EngineDAOException {
        HibernateEmployeeDAO hed = new HibernateEmployeeDAO();
        Employee employee = hed.selectByKey(person.getPersonID());
        List<Work> unaprovedWorks = new ArrayList<>();
        for (Work w : employee.getWorkList()) {
            if (w.getStatus() == HibernateWorkDAO.UNAPPROVED) {
                unaprovedWorks.add(w);
            }
        }
        return unaprovedWorks;
    }

    public void approveThesis(Work work) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        work.setStatus(HibernateWorkDAO.APPROVED);
        work.setAcceptanceDate(new Date());
        hwd.makePersistent(work);
    }

    public void denyThesis(Work work) throws EngineDAOException {
        HibernateWorkDAO hwd = new HibernateWorkDAO();
        work.setStatus(HibernateWorkDAO.REJECTED);
        hwd.makePersistent(work);
    }

    public void createCommunication(Communication com) throws EngineDAOException{
        HibernateCommunicationDAO hcd = new HibernateCommunicationDAO();
        hcd.makePersistent(com);
    }
    
    public List<Message> getMessagesByCommunication(Communication com) throws EngineDAOException{
        HibernateMessageDAO hmd = new HibernateMessageDAO();
        return hmd.getMessagesByCommunication(com);
    }
    
    public List<Communication> getCommunicationsByEmployee(Employee employee) throws EngineDAOException{
        HibernateCommunicationDAO hcd = new HibernateCommunicationDAO();
        return hcd.getCommunicationsByEmployee(employee);
    }
    
    public List<Communication> getCommunicationsByStudent(Student student) throws EngineDAOException{
        HibernateCommunicationDAO hcd = new HibernateCommunicationDAO();
        return hcd.getCommunicationsByStudent(student);
    }
    
    public void createMessage(Message message) throws EngineDAOException{
        HibernateMessageDAO hmd = new HibernateMessageDAO();
        hmd.makePersistent(message);
    }
    
    public List<Communication> getCommunicationsWithUnreadMessages(Person person) throws EngineDAOException{
        HibernateMessageDAO hmd = new HibernateMessageDAO();
        List<Communication> communications = new ArrayList<>();
        for(Message m : hmd.getUnreadMessages(person)){
            communications.add(m.getCommunication());
        }
        return communications;
    }
    
    public void setMessagesToSeen(Communication communication) throws EngineDAOException{
        HibernateMessageDAO hmd = new HibernateMessageDAO();
        List<Message> messages = hmd.getMessagesByCommunication(communication);
        for(Message m : messages){
            m.setRead(true);
            hmd.makePersistent(m);
        }        
    }
    
    public List<Subject> getSubjectsByDepartment(Department department) throws EngineDAOException{
        HibernateSubjectDAO hsd = new HibernateSubjectDAO();
        return hsd.getSubjectsByDepartments(department);
    }
    
    
}
