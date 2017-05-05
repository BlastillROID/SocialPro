/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevavecmaven;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev.Entities.Post;
import pidev.Entities.Timeline;
import pidev.Services.PostService;
import pidev.Services.TimelineService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public ObservableList data;
    public String path = "";

    @FXML
    private JFXButton buttonUploaderFicier, bouttonFiltrerPub;
    @FXML
    private TableView tvPost, tvTimeline;
    @FXML
    private ImageView logOut;
    @FXML
    private AnchorPane showPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTimelineTable();
    }

    /* @FXML
    public void ajouterPost() {
        LocalDateTime ldt = LocalDateTime.now();
        String date = ldt.getDayOfMonth() + "-" + ldt.getMonth() + "-" + ldt.getYear();
        String time = ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();
        PostService ps = new PostService();
        Post p = new Post();
        p.setTimeline_id(Integer.parseInt(tfIdTimeline.getText()));
        p.setUser_id(Integer.parseInt(tfIdUser.getText()));
        p.setContent(taContenu.getText());
        p.setMedia(path);
        p.setDate(date);
        p.setTime(time);
        p.setNbr_signal(0);
        ps.ajouterPost(p);
        tfIdTimeline.setText("");
        tfIdUser.setText("");
        taContenu.setText("");
    }*/
    public void fillTimelineTable() {
        TimelineService ts = new TimelineService();
        data = FXCollections.observableArrayList();
        ResultSet rs = ts.showTimeline();
        try {
            if (tvTimeline.getColumns().isEmpty()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
                    tvTimeline.getColumns().addAll(col);
                }
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tvTimeline.setItems(data);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
            e.printStackTrace();
        }
    }

    @FXML
    public void fillSpecificPostTable() {
        PostService ps = new PostService();
        data = FXCollections.observableArrayList();

        TablePosition pos = (TablePosition) tvTimeline.getSelectionModel().getSelectedCells().get(0);
        int roww = pos.getRow();
        Object item = tvTimeline.getItems().get(roww);
        TableColumn coll = pos.getTableColumn();
        String str = coll.getCellObservableValue(item).getValue().toString();

        ResultSet rs = ps.showSpecificPost(str);
        try {
            if (tvPost.getColumns().isEmpty()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
                    tvPost.getColumns().addAll(col);
                }
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tvPost.setItems(data);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
            e.printStackTrace();
        }
    }

    /*@FXML
    public void uploadImage() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(imgUpload.getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString(), 100, 150, true, true);
            imgUpload.imageProperty().unbind();
            imgUpload.setImage(img);
            imgUpload.setFitWidth(250);
            imgUpload.setFitHeight(250);
        }
        path = file.toURI().toURL().toString();
    }*/
    
    @FXML
    public void filtrerPublications() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment filtrer les publications ?", ButtonType.OK);
        alert.setTitle("Filtre");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            PostService ps = new PostService();
            ps.filtrer();
            fillSpecificPostTable();
        }
        
    }

    @FXML
    private void logoutClicked(MouseEvent event) {
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
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
