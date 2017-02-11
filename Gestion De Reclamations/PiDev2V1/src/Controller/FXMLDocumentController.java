/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pidev2.DataBase.DataSource;
import pidev2.Entities.Complaint;
import pidev2.Entities.Employee;
import pidev2.Service.Complaint_Services;
import pidev2.Service.Employee_Services;


/**
 *
 * @author Nizar
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TableView tableV_complaint;
    @FXML
    private TextField textField,Titre;
 
    @FXML
    private TextArea Descriptif;
    @FXML
    private Button Benvoyer_rec;
    @FXML
    private Label test;
    @FXML
    private void handleButtonAction(ActionEvent event) {
    /*     Employee e=new Employee(1, "nizar", "nizar", "abc", "mus", new Date(0), 1 , 2, 3);
                  Employee e2=new Employee(2, "nizar2", "nizar", "abc", "mus", new Date(0), 1 , 2, 3);

     Employee_Services IE = new Employee_Services();
    /* Complaint c= new Complaint("test", "test", e.getID());
     Complaint c2= new Complaint("test2", "test2", e2.getID());*/
    
     Complaint_Services cs = new Complaint_Services();
     
        DataSource DS = null;
        DataSource.getinstance();
        //IE.Ajout(e);
       //  System.out.println("You clicked me!!!!!!!!!!!!!!!!!!!!!!!!!");
       
        
         cs.Affichage().forEach(System.out::println);


       // cs.Ajout(c2);
        System.out.println("You clicked me!");
       // cs.Affichage().forEach(System.out::println);
      // System.err.println(cs.getById(1));
      // cs.Modifier(1, "modif", "modif");
    //  cs.Supprimer(1);
      
      
       // System.out.println(c.getId());
       //System.err.println(cs.getById(1));
       
        label.setText("Hello World!");
    }
  
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Complaint_Services cs = new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.Affichage());
       // lvc.setItems(o);
      cs.chargeTableauDonnees(tableV_complaint);
      
    }    

    @FXML
    private void chercher(KeyEvent event) {
        tableV_complaint.refresh();
         Complaint_Services cs = new Complaint_Services();
        cs.recherche(textField.getText(), tableV_complaint);
    }

    @FXML
    private void Envoyer_reclamation(ActionEvent event) {
       Employee e=new Employee(2, "nizar", "nizar", "abc", "mus", new Date(0), 1 , 2, 3);
        Complaint_Services cs = new Complaint_Services();
        Complaint c= new Complaint(e.getID(),Titre.getText(), Descriptif.getText());
        cs.Ajout(c);
        Alert alert = new Alert(AlertType.INFORMATION);
        
alert.setTitle("intformation");
alert.setHeaderText(null);
alert.setContentText("ajout avec succes!");
alert.showAndWait();
    }

    @FXML
    private void recup(MouseEvent event) {
        Complaint_Services cs = new Complaint_Services();
        String valeur = tableV_complaint.getSelectionModel().getSelectedItems().get(0).toString();
        String id = valeur.substring(1, 2);
        int ID =Integer.parseInt(id);
        System.out.println(ID);
        Complaint c= cs.getById(ID);
        test.setText(c.getTitle());
        
    }

  

   

  
   
    
}
