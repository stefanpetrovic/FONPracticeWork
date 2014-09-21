/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author MIRA
 */
@ManagedBean
public class ThesisAprovalBean {
    
    private Work work;
    private Long id;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        makeWork(id);
    }
    
     public void makeWork(Long id) {
        try {
            work = Controller.getInstance().getWork(id);
        } catch (EngineDAOException ex) {
            Logger.getLogger(AddCommisionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     public String approve(){
         try {
             Controller.getInstance().approveThesis(work);
             return null;
         } catch (EngineDAOException e) {
             return null;
         }
     }
     
     public String deny(){
         try {
             Controller.getInstance().denyThesis(work);
             return null;
         } catch (EngineDAOException e) {
             return null;
         }
     }
    
}
