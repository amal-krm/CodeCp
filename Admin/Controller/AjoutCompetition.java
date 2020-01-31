/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import java.net.URL;
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
public class AjoutCompetition implements Initializable{
    @FXML
    private DatePicker Date_fin;

    @FXML
    private TextField Titre;

    @FXML
    private DatePicker Date_debut;

    @FXML
    private Button Annuler;

    @FXML
    private Button Enregistrer;

    
    @FXML
    void AfficherComp(ActionEvent event) {

    }

    @FXML
    void DeleteCompt(ActionEvent event) {

    }

    @FXML
    void DemandeAjouterComp(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
}
