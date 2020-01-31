/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.Layout_adminController;
import DaoObj.daoUser;
import Obj.User;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextFlow;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class ModifierProfileController implements Initializable {
     @FXML
    private TextField Prenom;

    @FXML
    private ImageView img;

    @FXML
    private Button AddPic ;

    @FXML
    private TextField Email;

    @FXML
    private TextField Usename;

    @FXML
    private TextField LanguageFv;

    @FXML
    private TextField Tel;

    @FXML
    private TextField Nom;
    private File ProPic;
    
    
     private daoUser daoU = new daoUser(Layout_adminController.db);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetProfile();
    }    
    
    
    private void SetProfile(){
         ResultSet U = daoU.find(layoutController.Id);
         
         try {
            Usename.setText(U.getString("Username"));
            LanguageFv.setText(U.getString(9));
            Tel.setText(U.getString(5));
            Email.setText(U.getString(4));
            Nom.setText(U.getString(7));
            Prenom.setText(U.getString(8));
            AddPic.setText(U.getString(13));
            
        } catch (SQLException ex) {
            System.out.println("hna muchkil");
        }
     }
    
    

    @FXML
    private void EnregistrerModification(ActionEvent event) {
        // code update of data in the DB
        
        User U = new User(Usename.getText(),  Email.getText(), Tel.getText(), AddPic.getText(), Nom.getText(), Prenom.getText(), LanguageFv.getText());
        if(daoU.update(U, layoutController.Id)){
        //si tout passe bien 
        //revenir vers profile
        ((Node)event.getSource()).getScene().getWindow().hide();
        // et afficher alerte success
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/success_icon.png").toString()));
        alert.setHeaderText("Vous profile a été bien modifier ");
        ButtonType OK = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(OK);
        Optional<ButtonType> result = alert.showAndWait();
        }
        
        
    }

    @FXML
    private void AnnulerModification(ActionEvent event) {
        //revenir vers profile
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
      private File OpenChooser() {
        JFileChooser fs = new JFileChooser(new File("c:\\"));
        fs.setDialogTitle("Save a File");
        int result = fs.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File source = fs.getSelectedFile();
            return source;
//            File dest = new File("submissions/Readit.jpg");
//            try {
//                Files.copy(source.toPath(), dest.toPath(),  REPLACE_EXISTING);
//            } catch (IOException ex) {
//                System.out.println("eroooooror");
//            }
        }
        return null;
    }
    
    public  void AjoutPic(ActionEvent event) {
        ProPic= OpenChooser();
        
    }
}
