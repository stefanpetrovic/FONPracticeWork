/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author MIRA
 */
public class Paths {
    
    public static String PATH_TO_REPOSITORY = "uploads/profilePictures/";
    public static final String PATH_TO_MESSAGE_FILES = "C:\\praksa\\uploads\\messageFiles\\";
    public static final String PATH_TO_FINAL_PAPERS_FILES = "C:\\praksa\\uploads\\finalPapers";
    private static Paths instance;
    private Paths (){
        
    }
    
    public static Paths getInstance(){
        if(instance==null)
            instance = new Paths();
        return instance;
    }
    
}
