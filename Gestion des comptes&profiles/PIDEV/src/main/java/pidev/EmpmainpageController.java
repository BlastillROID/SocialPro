/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Services.EmployeeService;
import Services.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author salah
 */
public class EmpmainpageController implements Initializable {
    
    @FXML
    private JFXButton profileViewBtn;

    @FXML
    private AnchorPane showPane;

    @FXML
    private void showProfile() throws IOException, Exception{
        EmployeeService es = new EmployeeService();
        AnchorPane newPane = FXMLLoader.load(getClass().getResource("/fxml/profile.fxml"));
        showPane.getChildren().setAll(newPane);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void logoutClicked(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure you wish to exit?", ButtonType.YES,
                                 ButtonType.NO);
        alert.setTitle("Exit Program");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                Stage stage;
                Parent indexPage = FXMLLoader.load(getClass().getResource("/fxml/index.fxml"));
                Scene scene = new Scene(indexPage);
                stage = (Stage)showPane.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
