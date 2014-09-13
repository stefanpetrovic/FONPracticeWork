/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Course;
import dao.domain.core.Department;
import dao.domain.core.Title;
import java.util.List;
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
    
    public DropboxesDataBean() {
        titles = Controller.getInstance().getTitles();
        departments = Controller.getInstance().getDepartments();
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
    
    
}
