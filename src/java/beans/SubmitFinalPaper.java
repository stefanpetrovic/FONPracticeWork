/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Work;
import dao.exception.EngineDAOException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import utils.Paths;

/**
 *
 * @author stefan
 */
@ManagedBean
public class SubmitFinalPaper {

    private final String pathToUpload = Paths.PATH_TO_FINAL_PAPERS_FILES;

    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean user;
    private Work work;
    private UploadedFile file;

    @PostConstruct
    public void init() {
        try {
            work = Controller.getInstance().getStudentsCurrentWork(user.getLoggedInPerson().get(user.getPersonIdentifier()).getStudent());
        } catch (EngineDAOException ex) {
            Logger.getLogger(SubmitFinalPaper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("nema rada");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ne mozete vise puta slati finalnu verziju rada."));
        }
    }

    public LoggedInUserBean getUser() {
        return user;
    }

    public void setUser(LoggedInUserBean user) {
        this.user = user;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String upload() {
        if (work.getFinalFileURI() == null || !work.getFinalFileURI().equals("")) {
            String filename = user.getUsername();
            String type = file.getFileName();
            type = type.substring(type.lastIndexOf("."));
            File saveFile;
            File userFolder;
            try {
                userFolder = new File(pathToUpload + "\\" + filename);
                if (!userFolder.exists()) {
                    userFolder.mkdir();
                }
                //saveFile = File.createTempFile(filename, type, new File(pathToImg));
                saveFile = new File(pathToUpload + "\\" + filename + "\\" + filename + type);
                System.out.println(saveFile.toPath());
                try (InputStream in = file.getInputstream()) {
                    Files.copy(in, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(saveFile.getName());
                    work.setFinalFileURI(filename + "\\" + filename + type);
                    Controller.getInstance().updateWork(work);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspešno ste prosledili završnu verziju rada."));
                    return null;
                } catch (IOException ex) {
                    Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nije moguće sačuvati završnu verziju rada."));
                    return null;
                }
            } catch (Exception ex) {
                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nije moguće sačuvati završnu verziju rada."));
                return null;
            }
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ne mozete dva puta dodati finalnu verziju."));
            return null;
        }
        // return null;
    }
}
