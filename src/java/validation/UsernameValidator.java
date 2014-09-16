/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validation;

import businessLogic.Controller;



/**
 *
 * @author stefan
 */
public class UsernameValidator{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean isValid() {
        return Controller.getInstance().isUsernameUnique(username);
    }
    
}
