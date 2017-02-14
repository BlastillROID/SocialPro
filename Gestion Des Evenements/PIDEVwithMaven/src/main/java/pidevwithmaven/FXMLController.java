/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevwithmaven;

import PIDev_Entities.Evenement;
import PIDev_Sevices.Evenement_Services;
import PIDev_Sevices.Type_Evenement_Services;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLController implements Initializable {

    @FXML
    private ListView LV_Events;
    @FXML
    private Button button_one;
    @FXML
    private TableView TableV;

    @FXML
    private TabPane tab;

    @FXML
    private Tab affichage_tab;

    @FXML
    private Tab ajouter_tab;

    @FXML
    private TextField nom_evenement_tf;

    @FXML
    private TextField type_evenement_tf;

    @FXML
    private TextField addresse_evenement_tf;

    @FXML
    private DatePicker date_evenement;
    @FXML
    private Button button2;
    @FXML
    private ComboBox type_evenement_cb;

    @FXML
    private TextField media_evenement_tf;

    @FXML
    private TextArea description_evenement_tf;

    @FXML
    private Button uploadImg;

    @FXML
    private ImageView imgPreview;

    @FXML
    private MenuItem modifier_evenement_menu;

    @FXML
    private MenuItem supprimer_evenement_menu;
    @FXML
    private JFXButton modifier_evenement;
    @FXML
    private Label id_lb;
      @FXML
    private JFXTextField nom_evenement_tf2;

    @FXML
    private JFXTextField addresse_evenement_tf2;
    File file;
    private final Evenement_Services ES = new Evenement_Services();
    private Type_Evenement_Services TES = new Type_Evenement_Services();

    public void refresh(TableView T) {

        T.getItems().clear();
        T.getColumns().clear();
        ES.chargeTableauDonnees(T);
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void test(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        //fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(uploadImg.getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString(), 100, 150, true, true);
            imgPreview.setImage(img);
            imgPreview.setFitWidth(200);
            imgPreview.setFitHeight(126);
            imgPreview.setPreserveRatio(true);
            media_evenement_tf.setText(file.getPath());
        }
        System.out.println(file);

    }

    public void test2(ActionEvent event) {
        System.out.println("hello");

        button_one.setText("hello");

        Evenement E = new Evenement(nom_evenement_tf2.getText(),
                TES.getEventTypeId(type_evenement_cb.getSelectionModel().getSelectedItem().toString()),
                java.sql.Date.valueOf(date_evenement.getValue()),
                addresse_evenement_tf2.getText(),
                description_evenement_tf.getText(),
                media_evenement_tf.getText());

        ES.Ajout(E);
        refresh(TableV);
        nom_evenement_tf.setText("");
        type_evenement_cb.getSelectionModel().clearSelection();
        date_evenement.setValue(null);
        addresse_evenement_tf.setText("");
        description_evenement_tf.setText("");
        media_evenement_tf.setText("");
        //imgPreview.set

    }

    public void supprimer(ActionEvent event) {
        Evenement_Services ES = new Evenement_Services();
        String value = TableV.getSelectionModel().getSelectedItems().get(0).toString();
        System.out.println(value);
        String id = value.substring(1, value.indexOf(","));
        int Id = Integer.parseInt(id);
        ES.Suppression(Id);
        refresh(TableV);

    }

    public void modifier(ActionEvent event) {

        Evenement_Services ES = new Evenement_Services();
        String value = TableV.getSelectionModel().getSelectedItems().get(0).toString();
        System.out.println(value);
        String id = value.substring(1, value.indexOf(","));
        int Id = Integer.parseInt(id);
        Evenement E = ES.getEvenemnetById(Id);
        id_lb.setText(id);
        // System.out.println(id);
        nom_evenement_tf.setText(E.getEvenement_name());
        type_evenement_cb.setValue("");
        date_evenement.setValue(E.getDate_evenement().toLocalDate());
        addresse_evenement_tf.setText(E.getAddress_evenement());
        description_evenement_tf.setText(E.getDescription());
        media_evenement_tf.setText(E.getMedia());
        //tab.selectionModelProperty().ge;

    }

    @FXML
    void modifier_Action(ActionEvent event) {
        Evenement E2 = new Evenement(nom_evenement_tf.getText(),
                TES.getEventTypeId(type_evenement_cb.getSelectionModel().getSelectedItem().toString()),
                java.sql.Date.valueOf(date_evenement.getValue()),
                addresse_evenement_tf.getText(),
                description_evenement_tf.getText(),
                media_evenement_tf.getText());
        int id = Integer.parseInt(id_lb.getText());
        ES.Modif(E2, id);
        System.out.println("test here");
         refresh(TableV);
        nom_evenement_tf.setText("");
        type_evenement_cb.getSelectionModel().clearSelection();
        date_evenement.setValue(null);
        addresse_evenement_tf.setText("");
        description_evenement_tf.setText("");
        media_evenement_tf.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ES.chargeTableauDonnees(TableV);
        ObservableList obs = FXCollections.observableArrayList(TES.getEventType());
        type_evenement_cb.setItems(obs);

    }

}
