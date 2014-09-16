/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validation;

import businessLogic.Controller;
import java.util.regex.Pattern;

/**
 *
 * @author stefan
 */
public class JMBGValidator {
    
    private String jmbg;

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
    
    public boolean isValid() {
        if (jmbg.length() != 13) return false;
        if (Pattern.compile("[^0-9]").matcher(jmbg).matches()) return false;
        if (!Controller.getInstance().isJMBGUnique(jmbg)) return false;
        return true;
    }
    
    public static void main(String[] args) {
        JMBGValidator jv = new JMBGValidator();
        jv.setJmbg("1909991710096");
        System.out.println(jv.isValid());
    }
}
