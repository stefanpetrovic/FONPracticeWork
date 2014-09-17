/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Employee;
import dao.domain.core.Work;
import javax.faces.bean.ManagedBean;

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
    
    public String addCommision(){
        Controller.getInstance().addCommision(work,firstMember,secondMember,thirdMember);
        return null;
    }
}
