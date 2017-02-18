/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Profile;
import Services.EmployeeService;
import Services.ProfileService;
import Services.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author salah
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label profileNameLabel;
    
    @FXML
    private VBox postsArea;
    
    @FXML
    private JFXButton postItBtn;
    
    @FXML
    private ImageView profilePic;
    
    @FXML
    private Label jobTitleLabel;

    @FXML
    private JFXTabPane profileTab;
    @FXML
    private JFXButton modiftyBtn;
    
    @FXML
    public void modifyBtnClicked(){
        profileTab.getSelectionModel().select(1);
    }
    @FXML
    public void postItButtonClicked() throws IOException{
        Parent page2 = FXMLLoader.load(getClass().getResource("/fxml/post.fxml"));
        postsArea.getChildren().add(page2);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Profile p = new Profile();
            ProfileService ps = new ProfileService();
            p.setUserId(Session.getCurrentSession());
            ResultSet rs = ps.afficherProfile(p);
            EmployeeService es = new EmployeeService();
            System.out.println(Session.getCurrentSession());
            profileNameLabel.setText(es.getName(Session.getCurrentSession()));
            jobTitleLabel.setText(es.getJobTitle(Session.getCurrentSession()));
            while(rs.next()){
                profilePic.setImage(new Image(rs.getString(3)));
            }
            profilePic.setPreserveRatio(false);
            profilePic.prefWidth(216);
            profilePic.prefHeight(213);
        } catch (Exception ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
