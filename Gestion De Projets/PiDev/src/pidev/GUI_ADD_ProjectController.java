/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pidev.DataBase.DataSource;
import pidev.Entities.Employee;
import pidev.Entities.Projects;
import pidev.Services.Employee_Services;
import pidev.Services.Projects_Services;

/**
 *
 * @author LordGoats
 */
public class GUI_ADD_ProjectController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField FT_ProjectName;

    @FXML
    private TextArea FT_Project_Description;

    @FXML
    private ComboBox ManagerLists;
    @FXML
    private ComboBox Ahmed;
    @FXML
    private ListView LV_Projects;
    @FXML
    private Button Submit_Project;    
    @FXML
    private TableView Table_ViewP;

    @FXML
    private TableColumn  ID;

    @FXML
    private TableColumn ProjectName;

    @FXML
    private TableColumn Manager;

    @FXML
    private TableColumn  Description;
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Date BD = new Date(1995, 6, 11);
        Employee E = new Employee(5, "Name", "Email", "Phone", "Gender", BD, 2, 2, 1);
        Employee_Services IE = new Employee_Services();
        Projects_Services IP = new Projects_Services();
        DataSource DS = null;
        DataSource.getinstance();
        String MngID = ManagerLists.getSelectionModel().getSelectedItem().toString().substring(0, 1);
        Projects P = new Projects(5, FT_ProjectName.getText(), FT_Project_Description.getText(), Integer.parseInt(MngID));
        IP.Ajout(P);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Employee_Services IE = new Employee_Services();
        DataSource DS = null;
        DataSource.getinstance();
        Projects_Services IP = new Projects_Services();
        IE.ListByJob().forEach(System.out::println);
        ObservableList<String> Obs = FXCollections.observableArrayList(IE.ListByJob());
       /* ObservableList Obs2 = FXCollections.observableArrayList(IP.FillTV());
        LV_Projects.setItems(Obs2);
    Obs.forEach(System.out::println);
*/   IP.chargeTableauDonnees(Table_ViewP);

   
        ManagerLists.setItems(Obs);
        ManagerLists.setPromptText("Managers");

    }

}
