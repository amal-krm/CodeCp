/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.Layout_adminController;
import DaoObj.daoSubmit;
import Obj.Submit;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class Listesubmissions implements Initializable {

    @FXML
    private AnchorPane rootPane;
     
    private daoSubmit daoM = new daoSubmit(Layout_adminController.db);
    
     @FXML
    private TableView<Submit> tableView;


    @FXML
    private TableColumn<Submit,String> Username;


    @FXML
    private TableColumn<Submit, String> Probleme; 
    @FXML
    private TableColumn<Submit, String> Language;
    @FXML
    private TableColumn<Submit, String> Resultat; 
    @FXML
    private TableColumn<Submit, Date> Date; 

    
     private void initTable() {
         System.out.println("table");
        initcol();

    }

      private void initcol() {
     System.out.println("col");
         Username.setCellValueFactory(new PropertyValueFactory<Submit,String>("Username"));
       
        Probleme.setCellValueFactory(new PropertyValueFactory<Submit,String>("Id_Probleme"));
       // Vu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Lu"));
        Language.setCellValueFactory(new PropertyValueFactory<Submit,String>("Language"));
        Resultat.setCellValueFactory(new PropertyValueFactory<Submit,String>("Verdict"));
        Date.setCellValueFactory(new PropertyValueFactory<Submit,Date>("Date"));
        tableView.getItems().addAll(loadData());
    }

    private ObservableList<Submit> loadData()
    {
        ObservableList<Submit> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoM.all();
          System.out.println("hii");
       try {               
            while (Rs.next()) {
                  data_table.add(new Submit(Rs.getString(3),Rs.getString(1),Rs.getString(2),Rs.getDate(4),Rs.getString(5),Rs.getString(6),Rs.getString(7)));
                       System.out.println(Rs.getString(3)+" "+Rs.getString(1)+" "+Rs.getString(2)+" "+Rs.getDate(4)+" "+Rs.getString(5)+" "+Rs.getString(6)+" "+Rs.getString(7));


            }
        }       catch (SQLException ex) {
            System.out.println("Error");

       }
                       

        return data_table;
    }
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initTable();
        loadData();
    }    
       
        
    
}
