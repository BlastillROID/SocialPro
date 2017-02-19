/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import static java.awt.Color.red;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;
import pidev2.DataBase.DataSource;
import pidev2.Entities.Complaint;
import pidev2.Entities.Employee;
import pidev2.Service.Complaint_Services;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author Nizar
 */
public class FXMLDocumentController implements Initializable {
    /*
    style listview
    */
       private final GridPane gridPane = new GridPane(); 
    private final ImageView brandIcon = new ImageView(); 
    private final Label brandLabel = new Label(); 
    private final Label modelLabel = new Label(); 
 
    private final Label descriptionLabel = new Label(); 
    private final ImageView carIcon = new ImageView(); 
    private final AnchorPane content = new AnchorPane(); 
      /*
    style listview
    */
    
    
    
    @FXML
    private Label label;
   
   
    @FXML
    private TextField Titre;
 
    @FXML
    private TextArea Descriptif;
    @FXML
    private Button Benvoyer_rec;
    private Label test;
    
  
   
    @FXML
    private Label idComplaint_modif;

  
   
 
    @FXML
    private JFXListView<Complaint> LV_complaints;
   
    @FXML
    private JFXTextField tf_chercher_complaint_list;
    public void rafraichir_table(TableView table)
    { table.getItems().clear();
       table.getColumns().clear();
      Complaint_Services cs=new Complaint_Services();
      
      cs.chargeTableauDonnees(table);
            
    }
    public void rafraichir_liste(ListView list)
    { list.getItems().clear();
       
      Complaint_Services cs=new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.Affichage());
      
      list.getItems().setAll(o);
            
    }
   
  
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       LV_complaints.setExpanded(true);
     LV_complaints.depthProperty().set(1);
     Employee e=new Employee(2, "nizar", "nizar", "abc", "mus", new Date(0), 1 , 2, 3);
        
        Complaint_Services cs = new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.Affichage());
      
      
LV_complaints.setCellFactory(new Callback<ListView<Complaint>, ListCell<Complaint>>() {
            @Override
            public ListCell<Complaint> call(ListView<Complaint> param) {
ListCell<Complaint> cell = new ListCell<Complaint>(){

@Override
protected void updateItem(Complaint comp , boolean bt1)
        {super.updateItem(comp, bt1);
//(comp.getVu().equalsIgnoreCase("non vue"))
    //    {setTextFill(red);}
      setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if(comp!=null){
            System.out.println(comp.getVu());
Label lbl =new Label("");
 if(comp.getStatus().equalsIgnoreCase("terminé"))
            try {
               
                lbl.setGraphic(new ImageView(new Image(new FileInputStream("C:/Users/Nizar/Desktop/img2.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
 Text txt= new Text("non vue");
       
 VBox vBox = new VBox(new Text(comp.getTitle()), new Text(comp.getDescription()));
   //     if(comp.getVu().equalsIgnoreCase("non vue")) 
             
                            HBox hBox = new HBox(lbl, vBox);
                            hBox.setSpacing(10);
         if(comp.getVu().equals("non vue")) 
        {hBox.setStyle("-fx-background-color: #336699;"); }
          
          
                            setGraphic(hBox);
   
        }
      }
}  ;       
                return cell;
            }
        });
LV_complaints.getItems().setAll(o);



          Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        int res = cs.NbrNotSeen(e.getID());
        if(res!=0)
        { Notifications n = Notifications.create().
                title("Notification")
                .text("vous avez "+res+"reclamations no lu")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent eventt) -> {
                    System.out.println("clicked");
      });
         n.darkStyle();
         
  n.showConfirm();   
    }}
}));
fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
fiveSecondsWonder.play();
      
    }    

 

    @FXML
   public void Envoyer_reclamation(ActionEvent event) {
       Employee e=new Employee(2, "nizar", "nizar", "abc", "mus", new Date(0), 1 , 2, 3);
        System.out.println(e.getID()+"zzzz"+Titre.getText()+"zzzz"+Descriptif.getText());
        Complaint_Services cs = new Complaint_Services();
        Complaint c= new Complaint(e.getID(),Titre.getText(), Descriptif.getText());
        cs.Ajout(c);
        Alert alert = new Alert(AlertType.INFORMATION);
        
alert.setTitle("intformation");
alert.setHeaderText(null);
alert.setContentText("ajout avec succes!");
alert.showAndWait();


        rafraichir_liste(LV_complaints);
    }

 

    @FXML
    private void getComplaint_details(MouseEvent event) {
        System.out.println(LV_complaints.getSelectionModel().getSelectedItem());
        if(event.getClickCount()==2)
        {Complaint_Services cs = new Complaint_Services();
        cs.Seen(LV_complaints.getSelectionModel().getSelectedItem().getId());
Alert alert = new Alert(AlertType.INFORMATION);
alert.setGraphic(null);
alert.setTitle(null);
alert.setHeaderText(LV_complaints.getSelectionModel().getSelectedItem().getTitle());
alert.setContentText(LV_complaints.getSelectionModel().getSelectedItem().getDescription());
alert.setContentText(LV_complaints.getSelectionModel().getSelectedItem().getReponse());


alert.showAndWait();
        }
    }

    @FXML
    private void chercher_complaint_list(KeyEvent event) {
         LV_complaints.getItems().clear();
      Complaint_Services cs=new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.recherche2(tf_chercher_complaint_list.getText(), LV_complaints));
      
     LV_complaints.getItems().setAll(o);
       
    }
    
    

  

   

  
   
    
}

