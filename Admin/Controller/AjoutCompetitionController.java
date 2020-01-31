/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.DaoCompetition;
import Enumeration.Level;
import Obj.Competition;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Amal
 */
public class AjoutCompetitionController implements Initializable{
    @FXML
    private DatePicker Date_fin;

    @FXML
    private TextField Titre;

    @FXML
    private DatePicker Date_debut;

    @FXML
    private Button Annuler;
     private DaoCompetition daoC = new DaoCompetition(Layout_adminController.db);

    @FXML
    private Button Enregistrer;

    @FXML
    void AjoutCompetition(ActionEvent event) {
        //date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date DD = Date.from(Date_debut.getValue().atStartOfDay(defaultZoneId).toInstant());
        System.out.println(DD);
        Date DF = Date.from(Date_fin.getValue().atStartOfDay(defaultZoneId).toInstant());
        if (DD.compareTo(DF) > 0) {
            //DD kbira men DF
        }
        Competition C = new Competition(Level.EASY, true, Titre.getText(), DD, DF);
        if (daoC.create(C)) {
            AjouterPrblInCompetition();
            System.out.println("competition ajouter");
        } else {
            System.out.println("Fuck");
        }

    }
    void AjouterPrblInCompetition(){
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
