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
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private String pathToImg = "C:\\praksa\\images";
    private UploadedFile file;
    private String destination;
    @ManagedProperty(value="#{loggedInUserBean}")
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
        

        String filename = loggedInUserBean.getUsername();
        String type = file.getFileName();
        type = type.substring(type.lastIndexOf("."));
        try {
            File saveFile = File.createTempFile(filename, type,new File(pathToImg));
            InputStream in = file.getInputstream();
            Files.copy(in, saveFile.toPath());
        } catch (IOException ex) {
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
