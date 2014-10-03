/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Course;
import dao.domain.core.Subject;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author MIRA
 */
@ManagedBean
public class ThesesSearch {

    private List<Work> resultTheses;
    private String heading;
    private String keywords;
    private Subject subject;

    public List<Work> getResultTheses() {
        return resultTheses;
    }

    public void setResultTheses(List<Work> resultTheses) {
        this.resultTheses = resultTheses;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ThesesSearch() {
        resultTheses = new ArrayList<>();
    }

    public String search() {
        try {
            String[] keyw = keywords.split(",");
            List<String> keywordsList = new ArrayList<>();
            for (String k : keyw) {
                keywordsList.add(k.trim());
            }
            
//            System.out.println("\n\tKEYWORDS:: ");
//            for (String keywordsList1 : keywordsList) {
////                System.out.println("keyword: "+keywordsList1);
//            }
//            System.out.println("\n\tHEADING:: "+heading);
//            System.out.println("\n\tSUBJECT:: "+subject.getName()+"\t\n\nCLASS:: "+subject.getClass());
            
            //ovo da se iskrpi lepo jer je sklepano ne daj boze
            if("".equals(heading)) heading = null;
            if("".equals(keywords)) keywordsList = null;
            resultTheses = Controller.getInstance().searchTheses(heading, keywordsList, subject);
            System.out.println("SIZE:: "+resultTheses.size());
            for (Work tg : resultTheses) {
                System.out.println("THESIS:: "+tg.getTitle());
            }
            if (resultTheses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dobijeni radovi."));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Nije pronađen rad po zadatim kriterijumima."));
                return null;
            }

        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Greska prilikom pretrage radova."));

        }
        return null;
    }

    public String searchUnapproved() {
        try {
            resultTheses = Controller.getInstance().searchUnapprovedTheses();
            if (resultTheses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dobijeni radovi."));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Nije pronađen rad po zadatim kriterijumima."));
                return null;
            }

        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Greska prilikom pretrage radova."));

        }
        return null;
    }

    public String searchUncommisioned() {
        try {
            resultTheses = Controller.getInstance().searchUncommisionedTheses();
            if (resultTheses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dobijeni radovi."));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Nije pronađen rad po zadatim kriterijumima."));
                return null;
            }

        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Greska prilikom pretrage radova."));

        }
        return null;

    }

    public String searchUngraded() {
        try {
            resultTheses = Controller.getInstance().searchUngradedTheses();
            if (resultTheses.size() > 0) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dobijeni radovi."));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Nije pronađen rad po zadatim kriterijumima."));
                return null;
            }

        } catch (EngineDAOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Greska prilikom pretrage radova."));

        }
        return null;

    }
    
    


}
