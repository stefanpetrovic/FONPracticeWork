/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Person;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author MIRA
 */
@ManagedBean
public class AddCommisionBean {

    private Work work;
    private Employee firstMember;
    private Employee secondMember;
    private Employee thirdMember;
    private String studentName;
    private String subjectName;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        makeWork(id);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    
    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Employee getFirstMember() {
        return firstMember;
    }

    public void setFirstMember(Employee firstMember) {
        this.firstMember = firstMember;
    }

    public Employee getSecondMember() {
        return secondMember;
    }

    public void setSecondMember(Employee secondMember) {
        this.secondMember = secondMember;
    }

    public Employee getThirdMember() {
        return thirdMember;
    }

    public void setThirdMember(Employee thirdMember) {
        this.thirdMember = thirdMember;
    }

    //@PostConstruct
    public void makeWork(Long id) {
        try {
            work = Controller.getInstance().getWork(id);
            Student st = work.getStudent();
            setStudentName(st.getPerson().getName() + " " + st.getPerson().getSurname());
            Subject s = work.getSubject();
            setSubjectName(s.getName());
        } catch (EngineDAOException ex) {
            Logger.getLogger(AddCommisionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public AddCommisionBean() {

    }

    public String addCommision() {
        try {
            Controller.getInstance().addCommision(work, firstMember, secondMember, thirdMember);
            //return null;
        } catch (EngineDAOException ex) {
            Logger.getLogger(AddCommisionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
