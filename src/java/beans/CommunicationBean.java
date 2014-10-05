/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import businessLogic.Controller;
import dao.domain.core.Communication;
import dao.domain.core.Employee;
import dao.domain.core.Message;
import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.UploadedFile;
import utils.Paths;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class CommunicationBean implements Serializable {
    
    private final String pathToUpload = Paths.PATH_TO_MESSAGE_FILES;
    private Long id;

    private Communication communication;
    private Message newMessage;

    @ManagedProperty(value = "#{loggedInUserBean}")
    private LoggedInUserBean user;

    private UploadedFile file;

    public CommunicationBean() {
//        loadCommunication();
//       prepareMessage();
    }

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        id = Long.parseLong(req.getParameter("id"));
        loadCommunication(id);
        prepareMessage();
    }

    public LoggedInUserBean getUser() {
        return user;
    }

    public void setUser(LoggedInUserBean user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(Message newMessage) {
        this.newMessage = newMessage;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isSenderEmployee(Message message) {
        if (message.getSender().getEmployee() instanceof Employee) {
            return true;
        }
        return false;
    }

    public void loadCommunication(Long id) {
        try {
            communication = Controller.getInstance().getCommunicationByID(id);
        } catch (EngineDAOException ex) {
            Logger.getLogger(CommunicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage() {
        try {
//            Controller.getInstance().addThesisRequest(work);\
            System.out.println("File size: " + file.getSize());
            if (file.getSize() > 0) {
                uploadFile();
            }
            Controller.getInstance().createMessage(newMessage);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Poruka uspešno poslata."));
            communication.getMessageList().add(newMessage);
            prepareMessage();
        } catch (EngineDAOException ex) {
            Logger.getLogger(ThesisRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nije moguće poslati poruku."));
        } catch (Exception ex) {
            Logger.getLogger(CommunicationBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nije moguće proslediti fajl."));
        }
    }

    public void prepareMessage() {
        newMessage = new Message();
        newMessage.setText("");
        newMessage.setIsRead(false);
        Person sender = user.getLoggedInPerson().get(user.getPersonIdentifier());
        if (sender.getStudent() != null) {
            newMessage.setReciever(communication.getEmployee().getPerson());
        } else {
            newMessage.setReciever(communication.getStudent().getPerson());
        }
        newMessage.setSender(sender);
        //  newMessage.setFileURI("");
        newMessage.setCommunication(communication);
    }

    public void uploadFile() throws Exception {
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
            Date date = new Date();
            saveFile = new File(pathToUpload + "\\" + filename + "\\" + filename + date.getTime() + type);
            System.out.println(saveFile.toPath());
            try (InputStream in = file.getInputstream()) {
                Files.copy(in, saveFile.toPath());
                System.out.println(saveFile.getName());
                newMessage.setFileURI(filename + "\\" + filename + type);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspešno ste prosledili fajl."));
            } catch (IOException ex) {
                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        } catch (Exception ex) {
            Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void downloadFile(String file) {
        File fileForDownload = new File(pathToUpload + "\\" + file);
        System.out.println(fileForDownload.toPath());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename=" + file.substring(file.indexOf("\\") + 1));
        response.setContentLength((int) fileForDownload.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(fileForDownload);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
    
    public String getFilename(String file) {
        int index = file.indexOf("\\");
        return file.substring(index + 1);
    }

}
