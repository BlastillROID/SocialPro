/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevavecmaven;

/*import pidev.Entities.User;
import pidev.Entities.Employee;
import pidev.Entities.Profile;

import pidev.Services.UserService;
import pidev.Services.EmployeeService;
import pidev.Services.ProfileService;
import pidev.Services.Session;
import com.github.sarxos.webcam.Webcam;*/

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidev.Entities.User;
import pidev.Services.Session;
import pidev.Services.UserService;

/**
 *
 * @author salah
 */
public class LoginController implements Initializable {

    private int picName;
    private File file;
    @FXML
    private TextField fnField,emField,pnField,usrnField;
    @FXML
    private JFXDatePicker dobField;
    @FXML
    private ToggleGroup genderToggle;
    @FXML
    private Button loginButtion;
    @FXML
    private TextField pwdField;
    @FXML
    private TextField loginField;
    @FXML
    private JFXButton uploadImg, stopTwoBtn, takePicSignUp;
    @FXML
    private ImageView imgPreview;
    @FXML
    private JFXTabPane signUpTab;
    @FXML
    private Label timerLabel;
    @FXML
    private BufferedImage grabbedImage;
    
   /*
    @FXML
    private void signUpClickedAction() throws SQLException {
        RadioButton selectedRadio = (RadioButton) genderToggle.getSelectedToggle();
        EmployeeService es = new EmployeeService();
        ProfileService ps = new ProfileService();
        System.out.println(selectedRadio.getText());
        Employee e = new Employee(fnField.getText(),emField.getText(),pnField.getText(),selectedRadio.getText(),Date.valueOf(dobField.getValue().toString()));
        int executeUpdate = es.ajouterEmployee(e);
        if (executeUpdate == 1) {
            UserService us = new UserService();
            User u = new User();
            u.setUsername(usrnField.getText());
            u.setPassword(pwdField.getText());
            u.setEmployee_id(e.getId());
            int usrExec = us.signUp(u);
            Profile p = new Profile(u.getId(), file.toURI().toString(), null);
            if (usrExec == 1) {
                ps.ajouterProfile(p);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Welcome to SocialPro!");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Sign up successful");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Query Result:");
            alert.setContentText("Error while signing up :(");
            alert.showAndWait();
        }
    }

    @FXML
    private void signInClickedAction() throws IOException {
        Stage stage;
        Parent signUpPage = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        Scene scene = new Scene(signUpPage);
        stage = (Stage) loginButtion.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void forgotPasswordClicked() throws IOException {
        Stage stage;
        Parent signUpPage = FXMLLoader.load(getClass().getResource("/fxml/forgotpassword.fxml"));
        Scene scene = new Scene(signUpPage);
        stage = (Stage) loginButtion.getScene().getWindow();
        stage.setScene(scene);
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        int count = 0;
        int role = 0;
        UserService us = new UserService();
        User u = new User();
        u.setUsername(loginField.getText());
        u.setPassword(pwdField.getText());
        count = us.login(u);
        Stage stage;
        Parent page2 = null ;
        if (count == 1) {
            Session.start(u.getId());
            role = us.getRole(u);
            if (role == 1) {
                page2 = FXMLLoader.load(getClass().getResource("/fxml/Timeline.fxml"));
            } else if (role == 2) {
                page2 = FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml"));
            }
            Scene scene = new Scene(page2);
            stage = (Stage) loginButtion.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not login");
            alert.setContentText("Please check your credentials!");
            alert.showAndWait();
        }
    }

    @FXML
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(uploadImg.getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString(), 100, 150, true, true);
            imgPreview.imageProperty().unbind();
            imgPreview.setImage(img);
            imgPreview.setFitWidth(252);
            imgPreview.setFitHeight(238);

            Circle clip = new Circle(imgPreview.getFitWidth() / 2,
                    imgPreview.getFitHeight() / 2,
                    95);
            imgPreview.setClip(clip);
        }
        System.out.println(file);
    }

    @FXML
    private void returnHomeClickedAction() throws IOException {
        Stage stage;
        Parent signUpPage = FXMLLoader.load(getClass().getResource("/fxml/index.fxml"));
        Scene scene = new Scene(signUpPage);
        stage = (Stage) fnField.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void goBackLinkClicked() throws IOException {
        signUpTab.getSelectionModel().select(0);
    }

    @FXML
    private void nextClicked() {
        signUpTab.getSelectionModel().select(1);
    }

    

}
