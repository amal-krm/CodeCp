/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoProbleme;
import DaoObj.daoSolution;
import Enumeration.Level;
import Obj.Probleme;
import Obj.Solution;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

/**
 *
 * @author Amal
 */
public class ProController implements Initializable {

    @FXML
    private TableView<Probleme> tableView;

    @FXML
    private TableColumn<Probleme, String> col_id;

    @FXML
    private TableColumn<Probleme, String> col_titre;

    @FXML
    private TableColumn<Probleme, Level> col_niveau;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button Sol;

    @FXML
    private TextField Titre;

    @FXML
    private Button Test;

    @FXML
    private TextField Niveau;

    @FXML
    private Button Annuler;

    @FXML
    private Button Enregistrer;

    @FXML
    private Button Prblm;

    File ProblmFile;
    File SolFile;
    File TestFile;
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);
    daoSolution daoS = new daoSolution(Layout_adminController.db);
    Alert Al = new Alert();
    static String id;

    private void initTable() {
        initcol();

    }

    private void initcol() {
        col_id.setCellValueFactory(new PropertyValueFactory<Probleme, String>("Id_Probleme"));
        col_titre.setCellValueFactory(new PropertyValueFactory<Probleme, String>("Titre"));
        col_niveau.setCellValueFactory(new PropertyValueFactory<Probleme, Level>("Level"));
        //editTable();

        tableView.setItems(loadData());

    }

    private ObservableList<Probleme> loadData() {
        ObservableList<Probleme> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoP.all();
        try {
            while (Rs.next()) {
                data_table.add(new Probleme(Rs.getString(1), Rs.getString(2), Rs.getString(4), Level.valueOf(Rs.getString(3)), Rs.getString(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeUsersController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//Level b=null;
//           data_table.add(new Probleme("aa",b, "rr","yy"));
        return data_table;

    }

    @FXML
    private void AjouterPro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/AjouterPro.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AfficherDetail(ActionEvent event) throws IOException {
        Probleme P = tableView.getSelectionModel().getSelectedItem();
        this.id = P.getId_Probleme();
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/DetailPro.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void delete(ActionEvent event) {
        //alert are you sure??

        Probleme P = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(P);
        if (daoS.delete(P.getId_Probleme())) {
            if (daoP.delete(P.getId_Probleme())) {
                System.out.println("deleted");
            } else {
                System.out.println("error");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadData();
    }

}
