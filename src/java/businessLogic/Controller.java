/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic;

import dao.domain.core.Person;
import dao.exception.EngineDAOException;
import dao.hibernate.HibernatePersonDAO;

/**
 * Class responsible for communication with database.
 * @author stefan
 */

public class Controller {
    private static Controller instance;
    
    private Controller() {
        
    }
    
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }
    
    public Person login(String username, String password) throws EngineDAOException {
        HibernatePersonDAO hpDAO = new HibernatePersonDAO();
        return hpDAO.getPersonByUsernameAndPassword(username, password);
    }
}
