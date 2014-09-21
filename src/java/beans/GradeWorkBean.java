/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Student;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author MIRA
 */
@ManagedBean
public class GradeWorkBean {

    private Work work;
    private int grade;
    private List<Integer> availableGrades;
    private Long id;
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(work.getGrade() != null){
            this.message = "Rad je već ocenjen ocenom "+work.getGrade();
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        System.out.println("\n\n\t\t ID rada: " + id);
        makeWork(id);
       
    }

    public GradeWorkBean() {
        availableGrades = Controller.getInstance().createGrades();
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Integer> getAvailableGrades() {
        return availableGrades;
    }

    public void setAvailableGrades(List<Integer> availableGrades) {
        this.availableGrades = availableGrades;
    }

    public void makeWork(Long id) {
        try {
            work = Controller.getInstance().getWork(id);
//            Student st = work.getStudent();
//            setStudentName(st.getPerson().getName() + " " + st.getPerson().getSurname());
//            Subject s = work.getSubject();
//            setSubjectName(s.getName());
        } catch (EngineDAOException ex) {
            Logger.getLogger(AddCommisionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String gradeWork() {
        try {
            Controller.getInstance().gradeWork(work, grade);
        } catch (EngineDAOException e) {
        }

        return null;
    }
    
  

}
