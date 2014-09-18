/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
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

    public GradeWorkBean() {
        availableGrades = new ArrayList<>();
        availableGrades.add(5);
        availableGrades.add(6);
        availableGrades.add(7);
        availableGrades.add(8);
        availableGrades.add(9);
        availableGrades.add(10);
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
    
    
    public String gradeWork(){
        try {
              Controller.getInstance().gradeWork(work, grade);
        } catch (EngineDAOException e) {
        }
      
        return null;
    }
    
    

}
