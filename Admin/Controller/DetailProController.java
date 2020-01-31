/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoProbleme;
import DaoObj.daoSolution;
import User.Controller.Listeproblemes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 *
 * @author Amal
 */
public class DetailProController implements Initializable {
    
    @FXML
    private Text Probleme;

    @FXML
    private Text Solutions;
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);
    void loadPr(String path ){
        File file = new File(path);
        String Res= new String() ;
        BufferedReader br;
        try {
            br = new BufferedReader (new InputStreamReader(new FileInputStream(file), "UTF8"));
            String st;
        
        while ((st = br.readLine()) != null) {
  
            Res += st+"\n";
        }
            //(new FileReader(file));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Probleme.setText(Res);
        
    }
    void loadSo(String path){
        File file = new File(path);
        String Res= new String() ;
        BufferedReader br;
        try {
            br = new BufferedReader (new InputStreamReader(new FileInputStream(file), "UTF8"));
            String st;
        
        while ((st = br.readLine()) != null) {
            //System.out.println(st);
            Res += st+"\n";
        }
            //(new FileReader(file));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Solutions.setText(Res);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        daoSolution daoS = new daoSolution(Layout_adminController.db);
        
        try {
            ResultSet Rs = daoP.find(Listeproblemes.id);
            loadPr(Rs.getString(2));
            ResultSet R = daoS.find(Rs.getString(2));
            loadSo(R.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    
}
