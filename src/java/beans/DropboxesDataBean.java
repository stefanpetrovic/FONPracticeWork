/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Course;
import dao.domain.core.Department;
import dao.domain.core.Subject;
import dao.domain.core.Title;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author stefan
 */
@ManagedBean
@ApplicationScoped
public class DropboxesDataBean {
    
    private List<Title> titles;
    private List<Department> departments;
    private List<Course> courses;
    private List<Subject> subjects;
    
    public DropboxesDataBean() {
        try {
            titles = Controller.getInstance().getTitles();
            departments = Controller.getInstance().getDepartments();
            courses = Controller.getInstance().getCourses();
            subjects = Controller.getInstance().getSubjects();
        } catch (EngineDAOException ex) {
            Logger.getLogger(DropboxesDataBean.class.getName()).log(Level.SEVERE, null, ex);
            titles = new ArrayList<>();
            departments = new ArrayList<>();
            courses = new ArrayList<>();
            subjects = new ArrayList<>();
        }
    }
    
    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    
}
