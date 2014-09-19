/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Work;
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
    private String idval;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdval() {
        return idval;
    }

    public void setIdval(String idval) {
        this.idval = idval;
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

    public AddCommisionBean() {
        work = Controller.getInstance().getWork(id);
    }
    
    

    public String addCommision() {
        Controller.getInstance().addCommision(work, firstMember, secondMember, thirdMember);
        return null;
    }
}
