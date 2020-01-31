/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoProbleme;
import DaoObj.daoUser;
import Database.Database;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class DashboardController implements Initializable {

    @FXML
    private Label NbreUsers;

    @FXML
    private Label name4;

    @FXML
    private Label NbreProb;

    @FXML
    private Label name3;

    @FXML
    private Label name2;

    @FXML
    private Label name1;
    daoUser daoU = new daoUser(Layout_adminController.db);
   daoProbleme daoP = new daoProbleme(Layout_adminController.db);
    public void TheLast() throws SQLException{
       ResultSet Rs=  daoU.LastUsers();
      // Rs.next();
       name1.setText(Rs.getString(2));
       Rs.next();
       name2.setText(Rs.getString(2));
       Rs.next();
       name3.setText(Rs.getString(2));
       Rs.next();
       name4.setText(Rs.getString(2));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            NbreUsers.setText(daoUser.getCount().toString());
//            NbreProb.setText(daoProbleme.getCount().toString());

            TheLast();
            
            //NbreUsersActive.setText(null);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
