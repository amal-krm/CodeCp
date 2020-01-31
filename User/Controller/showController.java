/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.CompetitionController;
import Admin.Controller.ListeUsersController;
import DaoObj.DaoCompetition;
import Enumeration.Level;
import Obj.Probleme;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class showController implements Initializable {

    @FXML
    private TableView<Probleme> listeProbleme;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonParticiper;
    @FXML
    private Label Titre;
    @FXML
    private Label Niveau;
    @FXML
    private Label DateFin;
    @FXML
    private Label DateDebut;
    @FXML
    private Label NParticipant;
    DaoCompetition daoC = new DaoCompetition(layoutController.db);

    @FXML
    private TableColumn<Probleme, String> Probleme;
    @FXML
    private TableColumn<Probleme, Level> NiveauPro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        loadData();
        ResultSet Comp = daoC.find(Listecompetitions.id);
        try {
            // Msg.next();
            Titre.setText(Comp.getString(6));
            Niveau.setText(Comp.getString(2));
            DateFin.setText(Comp.getString(4));
            DateDebut.setText(Comp.getString(5));
            NParticipant.setText(DaoCompetition.NbreTotalParticipantInCompeti(Listecompetitions.id).toString());
        } catch (SQLException ex) {
            System.out.println("error initialize showController");
        }
    }

    private void initTable() {
        initcol();

    }

    private void initcol() {
        Probleme.setCellValueFactory(new PropertyValueFactory<Probleme, String>("Titre"));
        NiveauPro.setCellValueFactory(new PropertyValueFactory<Probleme, Level>("Level"));
        listeProbleme.setItems(loadData());
    }

    private ObservableList<Probleme> loadData() {
        ObservableList<Probleme> data_table = FXCollections.observableArrayList();

        ResultSet Rs = daoC.AffcherListeProbleme(Listecompetitions.id);
        try {
            while (Rs.next()) {
                data_table.add(new Probleme(Rs.getString(1), "", Rs.getString(2), Level.valueOf(Rs.getString(3)), ""));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeUsersController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return data_table;
    }

    @FXML
    void Participer(ActionEvent event) {
        String IdUser = layoutController.Id;
        String Id  = Listecompetitions.id;
//        System.out.println("Id user "+ IdUser);
//            System.out.println("Id Compt "+ Id);
        if(daoC.AddUser(IdUser, Id)){
            System.out.println("Tu particiiiipe db ");
        }else{
            System.out.println("Tu participe pas");
        }
    }
        @FXML
    void Rank(ActionEvent event) throws IOException {
            RankControllerUser.id = Listecompetitions.id;
            System.out.println( "JHD" +RankControllerUser.id);
           Parent root = FXMLLoader.load(getClass().getResource("/User/fxml/Rank.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}
