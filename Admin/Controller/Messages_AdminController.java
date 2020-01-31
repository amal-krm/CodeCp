
package Admin.Controller;

import Connect.LoginController;
import DaoObj.daoMessage;
import DaoObj.daoMessagePerso;
import DaoObj.daoUser;
import DaoObj.daoMessagePerso;
import Database.Database;
import Obj.Message_Perso;
import User.Controller.Profile;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class Messages_AdminController implements Initializable {

    
    @FXML
    private AnchorPane rootPane;
    
//    public static  Database db;
//
//    public static String Id;
    
    private daoMessagePerso daoM = new daoMessagePerso(Layout_adminController.db);
    @FXML
    private TextArea Msg;
    @FXML
    private TextField Recep;
    @FXML
    private TextField Obj;
    @FXML
    private Button Annuler;
    @FXML
    private Button Enregistrer;
     
   
  
    
    //yousra
    
 @FXML
    private TableView<Message_Perso> tableView;

    @FXML
    private TableColumn<Message_Perso,String > Contenu;

    @FXML
    private TableColumn<Message_Perso, Date> Date;

//    @FXML
//    private TableColumn<Message_Perso, String> Vu;///////////khaas tkon bolllean makiyakhdhach

    @FXML
    private TableColumn<Message_Perso, String> Objet; 
     @FXML
    private TableColumn<Message_Perso, String> Emeteur; 
    
    private daoMessagePerso DaoM = new daoMessagePerso(Layout_adminController.db);
    
     private void initTable() {
        initcol();

    }

      private void initcol() {

         Emeteur.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Username_eme"));
        Contenu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Contenu"));
        Date.setCellValueFactory(new PropertyValueFactory<Message_Perso,Date>("date"));
       // Vu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Lu"));
        Objet.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Objet"));
        tableView.getItems().addAll(loadData());
    }

    private ObservableList<Message_Perso> loadData() {
        ObservableList<Message_Perso> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoM.allRec("admin");
        try {               

            while (Rs.next()) {
                data_table.add(new Message_Perso( Rs.getString(1), Rs.getString(2),Rs.getString(4),Rs.getString(7)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages_AdminController.class.getName()).log(Level.SEVERE, null, ex);

        }
                       

        return data_table;
    }
    
    //yousra

    
   
    @FXML
    public void afficherClarification(ActionEvent event) {
        
    }

   @FXML
    void EnvoyerMsg(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Envoi_message.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    
    void EnvoyerDef(ActionEvent event){
         if(Recep.getText() != null){
             if(createMsg()){
                 //mesage sent
                 
             }else{
                 //msg Non envoye erreur
             }
         } 
    }
    private boolean createMsg(){
        daoUser U = new daoUser(Layout_adminController.db);
        String Id_Recep = U.findId(Recep.getText());
        if(Id_Recep.equals("")){
            //username incorrect
            return false;
        }
        Message_Perso Ms = new Message_Perso(Layout_adminController.Id,Id_Recep , Msg.getText(), Obj.getText());
        //System.out.println(Layout_adminController.Id+" "+Recep.getText()+" "+Msg.getText()+" "+Obj.getText());
        if(DaoM.create(Ms)){
             //System.out.println("Msg envoyer");
             return true;
         }
             //System.out.println("Msg Non envoye");
             return false;
   }
   @FXML
    void showmsgenvoye(ActionEvent event) {
        //voir les msg envoye
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/fxml/MsgRecu.fxml"));
        } catch (IOException ex) {
            System.out.println("Bouton new msg : probleme Load()");
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nouveau message");
        stage.setScene(scene);
        stage.show();
        
    } 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadData();
        // TODO
    }
//    public void initData(String Id , Database db) {
//        this.db = db;
//        DaoM = new daoMessage(db);
//        this.Id = Id;
//        System.out.println("Id f west initdata= "+ Id);
//        System.out.println("hihohu");
//  }
     
}
