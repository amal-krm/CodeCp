/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;
import Connect.LoginController;
import DaoObj.*;
import Database.Database;
import Obj.User;
import apigmail1.JavaMailUtil;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.*;
import apigmail1.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class AjouterUserController implements Initializable {


    @FXML
    private Button enregistrer;

     @FXML
    private TextField ConfirmerEmail;

    @FXML
    private Button Annuler;

    @FXML
    private TextField email;
    
   
    daoUser daoU;
    
    private Alert Al= new Alert();
    
    public AjouterUserController(){
        
        daoU = new daoUser(Layout_adminController.db);
    }
    public static String RandomPassword() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
 
    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
    return (generatedString);
}
    @FXML
    private void Enregistrer(ActionEvent event) throws IOException {
      String E = email.getText();
      String EC = ConfirmerEmail.getText();
      if(E.equals(EC)){
          if(daoU.findEmail(EC)){
              //user deja enregistrer
              
          }else{
              //enregistrer User
              String Password = RandomPassword();
              

              User U = new User(Password, E );

              if(daoU.create(U)){
                  try {
                      JavaMailUtil.sendMail(E , Password);
                  } catch (Exception ex) {
                      Logger.getLogger(AjouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                   Al.popupEnregistrer("" , event);


              }else{
                  Al.AlerErreur("" , event);
              }
          }
      }else{
          //email o confirmation machi bhal bhal 
      }
        
       
     }
    @FXML
    private void Annuler (ActionEvent event){
        Al.Annuler("", event);
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
    }
   
    
}
