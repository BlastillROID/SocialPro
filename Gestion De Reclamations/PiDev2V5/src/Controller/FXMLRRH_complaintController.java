/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev2.Entities.Complaint;
import pidev2.Entities.Employee;
import pidev2.Service.Complaint_Services;
import pidev2.Service.Employee_Services;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class FXMLRRH_complaintController implements Initializable {

    @FXML
    private TableView<?> tableV_complaint;
    @FXML
    private JFXTextField tf_chercher_complaint;
 
    @FXML
    private JFXTextArea TA_description_info;
    @FXML
    private JFXTextField TF_titre_info;
    @FXML
    private JFXTextField TF_nom_info;
    @FXML
    private JFXTextField TF_mail_info;
    @FXML
    private JFXTextField TF_tel_info;
    
    
    public void rafraichir_table(TableView table)
    { table.getItems().clear();
       table.getColumns().clear();
      Complaint_Services cs=new Complaint_Services();
      
      cs.chargeTableauDonnees(table);
            
        }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Complaint_Services cs = new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.Affichage());
       // lvc.setItems(o);
      cs.chargeTableauDonnees(tableV_complaint);
  
    }    

    @FXML
    private void chercher_complaint(KeyEvent event) {
        tableV_complaint.refresh();
         Complaint_Services cs = new Complaint_Services();
        cs.recherche(tf_chercher_complaint.getText(), tableV_complaint);
     tableV_complaint.refresh();
    }

    @FXML //get complaint for popup
    private void getComplaint(MouseEvent event) {
          Complaint_Services cs = new Complaint_Services();
          Employee_Services es = new Employee_Services();
        String valeur = tableV_complaint.getSelectionModel().getSelectedItems().get(0).toString();
     
        String id = valeur.substring(1, valeur.indexOf(","));
      
        int ID =Integer.parseInt(id);
     
  Complaint c= cs.getComplaint_info_ById(ID);
      
       Employee e =es.getById(c.getEmployee_id());
      //  System.out.println(c.getEmployee_id());
     
      TF_nom_info.setText(e.getName());
      TF_mail_info.setText(e.getEmail());
      TF_tel_info.setText(e.getPhone());
      TF_titre_info.setText(c.getTitle());
      TA_description_info.setText(c.getDescription());
  //   LB_info_employee.setText(null);
        System.out.println(c.getEmployee_id());
    //   LB_info_employee.setText(e.getName());
        if(event.getClickCount()==2)
        {
        FXMLLoader Loader= new FXMLLoader();
        Loader.setLocation(getClass().getResource("/pidev2/FXMLRRH_complaint_repondre.fxml"));
        try {
            Loader.load();
            
            /* idComplaint_modif.setText(id);
            titreComlaint_modif.setText(c.getTitle());
            descreptifComplaint_modif.setText(c.getDescription());*/
        } catch (IOException ex) {
            Logger.getLogger(FXMLRRH_complaintController.class.getName()).log(Level.SEVERE, null, ex);
        }
       FXMLRRH_complaint_repondreConrtoller alertBoxController = Loader.getController();
        alertBoxController.setData(c.getTitle(), c.getDescription(),ID);
        Parent p =Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        rafraichir_table(tableV_complaint);
        }
    }
    
}
