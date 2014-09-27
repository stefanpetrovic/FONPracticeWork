/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author MIRA
 */
@ManagedBean
public class ThesisAprovalBean {

    private Work work;
    private Long id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public ThesisAprovalBean() {
        this.work = new Work();
        this.work.setDescription(new String());
    }

    public void makeWork(Long id) {
        try {
            work = Controller.getInstance().getWork(id);
            System.out.println("workid: " + work.getStatus());
            if (work.getStatus() == 1) {
                setStatus("Odobren");
                // clearMessages();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad je već odobren.", null));
            }
            if (work.getStatus() == -1) {
                setStatus("Odbijen");
                //  clearMessages();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad je već odbijen.", null));

            }
        } catch (EngineDAOException ex) {
            Logger.getLogger(AddCommisionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String approve() {
        try {
            Controller.getInstance().approveThesis(work);
            setStatus("Odobren");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad je odobren.", null));
            return null;
        } catch (EngineDAOException e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Došlo je do greške. Nemoguće je odobriti rad.", null));
            return null;
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Došlo je do greške. Nemoguće je odobriti rad.", null));
            return null;
        }
    }

    public String deny() {
        try {
            Controller.getInstance().denyThesis(work);
            setStatus("Odbijen");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad je odbijen.", null));

            return null;
        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Došlo je do greške. Nemoguće je odbiti rad.", null));
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Došlo je do greške. Nemoguće je odbiti rad.", null));
            return null;
        }
    }

}
