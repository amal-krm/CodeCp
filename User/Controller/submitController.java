/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import DaoObj.DaoCompetition;
import DaoObj.daoProbleme;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import com.google.gson.Gson;
import ApiHackerEarth.com.hackerearth.heapi.sdk.client.HackerEarthAPI;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.CompileOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.SupportedLanguages;
import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.CompileRequest;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.CompileResponse;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class submitController implements Initializable {

    @FXML
    private Button btn_submit;
    @FXML
    private Button bnt_annuler;
     @FXML
    private TextArea editor;
    
    @FXML
    private MenuButton Language;
    
    public static String  IdPro;
    String IdUser ;
    daoProbleme daoP ;
    DaoCompetition daoC ;
    Vector<String> ListeCompetUserParticipe ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String IdUser = layoutController.Id;
    
     daoC = new DaoCompetition(layoutController.db);
     daoP = new daoProbleme(layoutController.db);
     ListeCompetUserParticipe = new Vector<String>();
     
        
    }
   
    void CompetUserParticipeWithPrblm(){
        //ga3 les competitions li fihum had prblm
        Vector<String> ListeCompet =daoC.existPr(IdPro);
       
        for(int i=0 ; i< ListeCompet.size() ; i++){
            //if had user kayparticiper l chi we7da men dek compet 
            if(DaoCompetition.existUser(IdUser, ListeCompet.elementAt(i))){
                ListeCompetUserParticipe.add(ListeCompet.elementAt(i));
            }
        }
        
    }

    @FXML
    private void submit(ActionEvent event) {
         //recuperer solution
        CompetUserParticipeWithPrblm();
        if(ListeCompetUserParticipe.size() == 0){
            //submit 3adeya
           NormalSubmit();
            //System.out.println("NormalSubmit");
        }else{
            //submit in competition
            SubmitInCompetition();
            //System.out.println("SubmitInCompetition");
        }
    }
    void NormalSubmit(){
        
    }
    
    void SubmitInCompetition(){
        String source = editor.getText();
        //SupportedLanguages LanguageSup = SupportedLanguages.valueOf(Language.gets);
        //HackerEarthAPI API = new HackerEarthAPI("efebd479faa2a20e2003269dea2a644e515190c8");
        CompileOptions CO = new CompileOptions(source, SupportedLanguages.PYTHON);
        CompileRequest CR = new CompileRequest("efebd479faa2a20e2003269dea2a644e515190c8", CO);
        CompileResponse CResp = CR.Execute();
        System.out.println(CResp.getCompileStatus());
    }
        
        
        
        
        
        
//        //message verdict
//        String msg="resultat verdict";
//        //revenir vers la page precedente
//        //si tout passe bien 
//        //revenir vers profile
//        ((Node)event.getSource()).getScene().getWindow().hide();
//        // et afficher alerte success
//        Alert alert = new Alert(Alert.AlertType.NONE);
//        alert.setGraphic(new ImageView(this.getClass().getResource("/images/success_icon.png").toString()));
//        alert.setHeaderText(msg);
//        ButtonType OK = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
//        alert.getButtonTypes().setAll(OK);
//        Optional<ButtonType> result = alert.showAndWait();
    

    @FXML
    private void Annuler(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
}
