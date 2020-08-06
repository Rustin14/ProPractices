/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.io.File;



public class CreateDocumentsFolders {
    boolean success = false;
    public String dirName = null;
    
    public boolean createStudentsFolders (String studentName) {
       dirName = "./Documents/" + studentName + "/";
       File directory = new File(dirName);
       if (directory.exists()) {
           success = false;
       } else if (directory.mkdir()) {
           success = true;
       }
       return success;
    }
    
    public boolean createDocumentsFolder (String studentName, String documentName) {
        dirName = dirName + documentName + "/";
        File directory = new File(dirName);
        if (directory.exists()) {
            success = false;
        } else if (directory.mkdir()) {
            success = true;
        }
        return success;
    }
    
    public boolean createDocumentIDFolder (String studentName, String documentName, int documentID) {
        createStudentsFolders(studentName);
        createDocumentsFolder(studentName, documentName);
        dirName = dirName + Integer.toString(documentID);
        File directory = new File(dirName);
        if (directory.exists()) {
            success = false;
        } else if (directory.mkdir()) {
            success = true;
        }
        return success;
    }
    
    
    public String getDirName() {
        return dirName;
    }
}
