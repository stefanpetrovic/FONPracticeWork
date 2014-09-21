/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import businessLogic.Controller;
import dao.domain.core.Subject;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author stefan
 */
@ManagedBean
public class SubmitFinalPaper {
    
    private String pathToUpload = "C:\\praksa\\uploads\\finalPapers";
    
    @ManagedProperty(value="#{loggedInUserBean}")
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
            work = new Work();
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
    
    
    
    public void upload() {
        //delete previous user image if exists
//        String picname = user.getLoggedInPerson().get(user.getPersonIdentifier()).getPictureURI();
//        if (!picname.equals("")) {
//            String url = pathToUpload + "\\" + picname;
//            try {
//                Files.deleteIfExists(new File(url).toPath());
//                System.out.println("Prethodna slika obrisana");
//            } catch (IOException ex) {
//                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }

        String filename = user.getUsername();
        String type = file.getFileName();
        type = type.substring(type.lastIndexOf("."));
        File saveFile;
        try {
            //saveFile = File.createTempFile(filename, type, new File(pathToImg));
            saveFile = new File(pathToUpload + "\\" + filename + type);
            try (InputStream in = file.getInputstream()) {
                Files.copy(in, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(saveFile.getName());
            } catch (IOException ex) {
                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
