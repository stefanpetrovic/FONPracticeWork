/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.domain.core.Subject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private String title;
    private Subject subject;
    private UploadedFile file;

    public LoggedInUserBean getUser() {
        return user;
    }

    public void setUser(LoggedInUserBean user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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
