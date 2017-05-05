/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevavecmaven;

import pidev.Entities.Profile;
import pidev.Services.EmployeeService;
import pidev.Services.ProfileService;
import pidev.Services.Session;
import pidev.Services.PostService;
import pidev.Entities.Post;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.Entities.Feed;
import pidev.Entities.FeedMessage;
import pidev.Services.RSSService;

/**
 * FXML Controller class
 *
 * @author salah
 */
public class TimelineController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String path = "";
    public int Id = 0;
    @FXML
    private AnchorPane showPane;

    @FXML
    private ScrollPane profileTab;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label profileNameLabel;

    @FXML
    private ImageView imgPostBtn;

    @FXML
    private JFXTextArea taPost;

    @FXML
    private Label jobTitleLabel;

    private VBox postsArea;

    @FXML
    private JFXButton postItBtn;

    @FXML
    private VBox vbAfficher;
    @FXML
    private Hyperlink hFile;
    @FXML
    private ImageView logOut;
    public VBox vbRSS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Id = Session.getCurrentSession();
            System.out.println(Id);
            remplissageChamps();
            afficherPublications(Id);
        } catch (Exception ex) {
            Logger.getLogger(TimelineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void postItButtonClicked() throws IOException, Exception {
        Profile profil = new Profile();
        ProfileService prs = new ProfileService();
        LocalDateTime ldt = LocalDateTime.now();
        String date = ldt.getDayOfMonth() + "-" + ldt.getMonth() + "-" + ldt.getYear();
        String time = ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();
        PostService ps = new PostService();
        Post p = new Post(3, Session.getCurrentSession(), taPost.getText(), path, date, time, 1);
        ps.ajouterPost(p);
        vbAfficher.getChildren().clear();
        afficherPublications(Id);
        taPost.setText("");
        hFile.setText("");
    }

    @FXML
    public void uploadImage() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(showPane.getScene().getWindow());
        if (file != null) {
            path = file.toURI().toURL().toString();
            hFile.setText(path);
        }
    }

    @FXML
    private void logoutClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment vous déconnecter ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Se Déconnecter");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                Stage stage;
                Parent indexPage = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
                Scene scene = new Scene(indexPage);
                stage = (Stage) showPane.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(TimelineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void remplissageChamps() {
        Profile p = new Profile();
        ProfileService ps = new ProfileService();
        EmployeeService es = new EmployeeService();
        try {
            p.setUserId(Session.getCurrentSession());
            ResultSet rs = ps.afficherProfile(p);
            System.out.println(Session.getCurrentSession());
            profileNameLabel.setText(es.getName(Session.getCurrentSession()));
            jobTitleLabel.setText(es.getJobTitle(Session.getCurrentSession()));
            while (rs.next()) {
                String img = rs.getString(3);
                Image image = new Image(img, 300, 300, true, true);
                profilePic.imageProperty().unbind();
                profilePic.setImage(image);
                System.out.println(rs.getString(3));
            }
            profilePic.setPreserveRatio(false);
            profilePic.prefWidth(300);
            profilePic.prefHeight(300);
        } catch (Exception ex) {
            Logger.getLogger(TimelineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherPublications(int Id) throws Exception {
        Profile pf = new Profile();
        ProfileService pfs = new ProfileService();
        String str = Integer.toString(Id);
        PostService ps = new PostService();
        ResultSet rs = ps.showSpecificPosts(str);
        int i = 1;
        while (rs.absolute(i) == true) {
            int count = rs.getRow();
            System.out.println(count);
            int aaa = rs.getInt(3);
            pf.setUserId(aaa);
            ResultSet rs2 = pfs.afficherProfile(pf);
            while (rs2.next()) {
                String img = rs2.getString(3);
                postListFactory(rs.getString(4), rs.getString(6), rs.getString(7), img, rs.getString(5), Id);
                i++;
            }
        }
    }

    public void postListFactory(String contenu, String date, String time, String image, String file, int userId) throws SQLException {
        int id = 0;
        PostService ps = new PostService();
        Post post = new Post();
        post.setUser_id(userId);
        ResultSet rs = ps.afficherPost(post);
        while (rs.next()) {
            id = rs.getInt(1);
        }
        final int idd = id;

        HBox h = new HBox(), hh = new HBox();
        Label dateL = new Label(), contenuL = new Label(), timeL = new Label(), lineL = new Label(), fileL = new Label();
        Label sepL = new Label(), signalerL = new Label();
        String str = " at ";
        String str2 = "";
        String str3 = "Signaler";

        Image img = new Image(image);
        ImageView i = new ImageView(img);
        GridPane gp = new GridPane();

        hh.setPrefWidth(300);
        hh.setPrefHeight(3);

        contenuL.setText(contenu);
        dateL.setText(date);
        timeL.setText(time);
        lineL.setText(str);
        fileL.setText(file);
        fileL.setStyle("-fx-text-fill: #2320e8");
        sepL.setText(str2);
        signalerL.setText(str3);
        signalerL.setStyle("-fx-text-fill: #cc4848");
        signalerL.setOnMouseClicked(e -> ps.signaler(idd));

        h.setPrefWidth(300);
        h.setPrefHeight(60);
        h.setAccessibleText(Integer.toString(userId));
        h.setStyle("-fx-background-color: #ffffff;");

        i.setFitWidth(100);
        i.setFitHeight(100);
        i.setPreserveRatio(false);

        gp.getColumnConstraints().add(new ColumnConstraints(100, 300, 300, Priority.ALWAYS, HPos.LEFT, true));
        gp.add(contenuL, 0, 1, 3, 3);
        gp.add(dateL, 1, 0, 1, 1);
        gp.add(lineL, 2, 0, 1, 1);
        gp.add(timeL, 3, 0, 1, 1);
        gp.add(fileL, 0, 8);
        gp.add(signalerL, 3, 8);

        h.getChildren().add(i);
        h.getChildren().add(gp);
        hh.getChildren().add(sepL);
        vbAfficher.getChildren().add(h);
        vbAfficher.getChildren().add(hh);
    }

}
