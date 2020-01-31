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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;

/**
 *
 * @author Amal
 */
public class AjoutProblm {
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
    
    
 @FXML
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

    @FXML
    void AjoutPrblm(ActionEvent event) {
//        Probleme P = new Probleme(null, null, null, Level.EASY, null)
//        File dest = new File(null);
        ProblmFile = OpenChooser();

    }

    @FXML
    void AjoutSol(ActionEvent event) {
        SolFile = OpenChooser();
//        //FilenameUtils.getExtension(SolFile.getName());

    }

    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

    @FXML
    void AjoutTest(ActionEvent event) {
        TestFile = OpenChooser();
    }

    @FXML
    void EnregistrerDef(ActionEvent event) {
        if ((TestFile != null) && (SolFile != null) && (TestFile != null)) {
            Probleme P = new Probleme(Titre.getText(), Level.EASY, getFileExtension(ProblmFile), getFileExtension(TestFile));
            daoP.create(P);
            System.out.println("zed zed");
            Solution S = new Solution(P.getId_Probleme(), null, getFileExtension(SolFile));
            daoS.create(S);
            try {
                File T = new File(P.getPathTest());
                File Pr = new File(P.getPathPb());
                File Solo = new File(S.getPath());
                Files.copy(TestFile.toPath(), T.toPath(), REPLACE_EXISTING);
                Files.copy(ProblmFile.toPath(), Pr.toPath(), REPLACE_EXISTING);
                Files.copy(SolFile.toPath(), Solo.toPath(), REPLACE_EXISTING);
            } catch (IOException ex) {
                System.out.println("eroooooror");
            }

        } else {
            System.out.println("Not that okey");
        }
    }
    
    
    @FXML
    void Annuler(ActionEvent event) {
        //are you sure o dekchi 
    }
}
