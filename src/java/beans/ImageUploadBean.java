/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author stefan
 */
@ManagedBean
public class ImageUploadBean {

    private String pathToImg = "C:\\praksa\\uploads\\profilePictures";
    private UploadedFile file;
    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean loggedInUserBean;

    public UploadedFile getFile() {
        return file;
    }

    public LoggedInUserBean getLoggedInUserBean() {
        return loggedInUserBean;
    }

    public void setLoggedInUserBean(LoggedInUserBean loggedInUserBean) {
        this.loggedInUserBean = loggedInUserBean;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        //delete previous user image if exists
        String picname = loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier()).getPictureURI();
        String filename = loggedInUserBean.getUsername();
        String type = file.getFileName();

        if (!picname.equals("")) {
           
            String url = pathToImg + "\\" + picname;
            try { 
                //check if new file is selected or not - throws StringIndexOfBoudns...
                type = type.substring(type.lastIndexOf("."));
               
                Files.deleteIfExists(new File(url).toPath());
                System.out.println("Prethodna slika obrisana");
            } catch (StringIndexOutOfBoundsException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", "Morate odabrati fajl."));
                return;
            } catch (IOException ex) {
                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("vvvv");
        }

        File saveFile;
        try {

            System.out.println("DDDAA");
            //saveFile = File.createTempFile(filename, type, new File(pathToImg));
            saveFile = new File(pathToImg + "\\" + filename + type);
            try (InputStream in = file.getInputstream()) {
                Files.copy(in, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(saveFile.getName());
                loggedInUserBean.getLoggedInPerson().get(loggedInUserBean.getPersonIdentifier()).setPictureURI(saveFile.getName());
                loggedInUserBean.updateUser();
            } catch (IOException ex) {
                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        File saveFile = new File(folder + "resources\\images\\" + filename + type);
//        long size = file.getSize();
//        try {
//            InputStream in = file.getInputstream();
//            System.out.println(saveFile.toPath().toString());
//            Files.copy(in, saveFile.toPath());
//            System.out.println("Successfully uploaded file");
//        } catch (IOException ex) {
//            Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
