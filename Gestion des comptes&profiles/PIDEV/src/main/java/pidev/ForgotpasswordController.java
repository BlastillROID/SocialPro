/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import Entities.User;
import Services.Session;
import Services.SmsSender;
import static Services.SmsSender.ACCOUNT_SID;
import static Services.SmsSender.AUTH_TOKEN;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author salah
 */
public class ForgotpasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTabPane resetTab;

    @FXML
    private Tab fyuTab;

    @FXML
    private JFXTextField forgotPwdLabel;
    
    @FXML
    private JFXTextField verifCodeField;
    

    @FXML
    private JFXButton forgotBtnLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Tab verifTab;


    @FXML
    private Label errorLabelCode;

    @FXML
    private Tab resetPTab;

    @FXML
    private JFXPasswordField newPassField;

    @FXML
    private Label errorPwdLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        verifTab.setDisable(true);
        resetPTab.setDisable(true);      
    }

    
    @FXML
    private void returnHomeClickedAction() throws IOException{
        Stage stage;
        Parent signUpPage = FXMLLoader.load(getClass().getResource("/fxml/index.fxml"));
        Scene scene = new Scene(signUpPage);
        stage = (Stage)forgotBtnLabel.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    private void nextCliecked() throws URISyntaxException{
        User u = new User();
        String pin = "";
        UserService us = new UserService();
        u.setUsername(forgotPwdLabel.getText());
        if(us.findUser(u) == 1){
            Session.generatePIN();
            Twilio.setUsername(ACCOUNT_SID);
            Twilio.setPassword(AUTH_TOKEN);
            SmsSender.SendSMS("+21628840550",
                              "+16147964164", 
                              "Your code is : "+Session.getPin());
            resetTab.getSelectionModel().select(1);
            fyuTab.setDisable(true);
            verifTab.setDisable(false);
        }else
            errorLabel.setText("Please verify your username!");
        
    }
    @FXML
    private void verifyClicked(){
        if(!Session.getPin().equals(verifCodeField.getText())){
            errorLabelCode.setText("Code not correct! please try again");
        }else{
            resetTab.getSelectionModel().select(2);
            verifTab.setDisable(true);
            resetPTab.setDisable(false);
        }
    }
    @FXML
    private void resetClicked() throws IOException{
        if(newPassField.getText().isEmpty()){
            errorPwdLabel.setText("Please verify your new password!");
        }else{
            User u = new User();
            UserService us = new UserService();
            u.setUsername(forgotPwdLabel.getText());
            u.setPassword(newPassField.getText());
            if(us.changePwd(u) == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Password reset successful!");
                alert.showAndWait();
                returnHomeClickedAction();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Error! please try again");
                alert.showAndWait();
            }
        }
    }
}
