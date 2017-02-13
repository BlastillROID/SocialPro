/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pidev2.Service.Complaint_Services;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class FXMLRRH_complaint_repondreConrtoller implements Initializable {

    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea descreptif;
    @FXML
    private Button Benvoyer;
    @FXML
    private Button Bannuler;
    @FXML
    private JFXTextArea TAreponse;
    @FXML
    private Label Lid;

 

    /**
     * Initializes the controller class.
     */
    
      

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void envoyer_complaint_reponse(ActionEvent event) {
         Complaint_Services cs = new Complaint_Services();
         cs.repondre(Integer.parseInt(Lid.getText()), TAreponse.getText());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
alert.setTitle("intformation");
alert.setHeaderText(null);
alert.setContentText("Message envoyé!");
alert.showAndWait();
         annuler(event);
         
    }
    public void setData(String titre,String descreptif,int id)
    {
        this.titre.setText(titre);
     this.descreptif.setText(descreptif);
     this.Lid.setText(Integer.toString(id));
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage =(Stage) titre.getScene().getWindow();
       stage.close();
    }
    
}
