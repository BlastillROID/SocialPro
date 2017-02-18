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
    private Button button;
    @FXML
    private TableView tableV_complaint;
    @FXML
    private TextField Titre;
 
    @FXML
    private TextArea Descriptif;
    @FXML
    private Button Benvoyer_rec;
    private Label test;
    @FXML
    private TableView<?> tableV_complaint_modif;
    @FXML
    private TextField titreComlaint_modif;
    @FXML
    private TextArea descreptifComplaint_modif;
    @FXML
    private Label idComplaint_modif;
    @FXML
    private Button enregisterB_complaint_modif;
    @FXML
    private JFXTextField tf_chercher_complaint;
    @FXML
    private MenuItem Bsupprimer_complaint;
 
    @FXML
    private JFXListView<Complaint> listview;
    @FXML
    private JFXButton teest;
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
     
       
   /* Service<String> service = new Service<String>(){
         @Override
         protected Task<String> createTask() {
return  new Task<String>() {
    @Override
    protected String call() throws Exception {
        System.out.println("start");
        
        TrayNotification tray =new TrayNotification("Notification ", "ok",NotificationType.SUCCESS);
        tray.showAndWait();
  
        
       return null;
    }
};

         }
         
    };
       service.start();*/

                
       
       
        label.setText("Hello World!");
            
    
  
    }
  
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Employee e=new Employee(2, "nizar", "nizar", "abc", "mus", new Date(0), 1 , 2, 3);
        
        Complaint_Services cs = new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.Affichage());
      
       // lvc.setItems(o);
       
      cs.chargeTableauDonnees(tableV_complaint);
      cs.chargeTableauDonnees(tableV_complaint_modif);
  //    ListView.getItems().addAll(o);
/*  for(int i=0;i<4;i++)
  {       try {
      Label lbl =new Label("Item"+i);
      lbl.setGraphic(new ImageView(new Image(new FileInputStream("C:/Users/Nizar/Desktop/img.png"))));
    
      listview.getItems().add(lbl);
    
          } catch (FileNotFoundException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          }
  }*/
//Complaint c = new Complaint(1, "titre", "desc", 5);
listview.setCellFactory(new Callback<ListView<Complaint>, ListCell<Complaint>>() {
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
listview.getItems().setAll(o);

//listview.getSelectionModel().select(c);
      //  System.out.println(listview.getSelectionModel().getSelectedItem());

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

   /* @FXML
    private void chercher(KeyEvent event) {
        tableV_complaint.refresh();
         Complaint_Services cs = new Complaint_Services();
        cs.recherche(textField.getText(), tableV_complaint);
    }*/

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
 rafraichir_table(tableV_complaint_modif);
 rafraichir_table(tableV_complaint);
        rafraichir_liste(listview);
    }

    public void recup(MouseEvent event) {
       /* Complaint_Services cs = new Complaint_Services();
        String valeur = tableV_complaint.getSelectionModel().getSelectedItems().get(0).toString();
        String id = valeur.substring(1, 2);
        int ID =Integer.parseInt(id);
        System.out.println(ID);
        Complaint c= cs.getById(ID);
        test.setText(c.getTitle());*/
        
    }

    @FXML
    public void getComplaint(MouseEvent event) {
        Complaint_Services cs = new Complaint_Services();
        String valeur = tableV_complaint_modif.getSelectionModel().getSelectedItems().get(0).toString();
        String id = valeur.substring(1, valeur.indexOf(","));
        int ID =Integer.parseInt(id);
       // System.out.println(ID);
        Complaint c= cs.getById(ID);
        idComplaint_modif.setText(id);
        titreComlaint_modif.setText(c.getTitle());
        descreptifComplaint_modif.setText(c.getDescription());
    }

    @FXML
    public void Modifier_reclamation(ActionEvent event) {
        Complaint_Services cs = new Complaint_Services();
        int ID = Integer.parseInt(idComplaint_modif.getText());
      
        cs.Modifier(ID,titreComlaint_modif.getText(), descreptifComplaint_modif.getText());
         Alert alert = new Alert(AlertType.INFORMATION);
       alert.setTitle("intformation");
       alert.setHeaderText(null);
       alert.setContentText("modification  avec succes!");
       alert.showAndWait();
        rafraichir_table(tableV_complaint_modif);
    }

    public void chercher(KeyEvent event) {
    }

    @FXML
    public void chercher_complaint(KeyEvent event) {
        //System.out.println("This is test for search function"); 
        tableV_complaint.refresh();
         Complaint_Services cs = new Complaint_Services();
        cs.recherche(tf_chercher_complaint.getText(), tableV_complaint);
       // rafraichir_table(tableV_complaint);
    }

    @FXML
    public void supprimer_complaint(ActionEvent event) {
          Complaint_Services cs = new Complaint_Services();
        String valeur = tableV_complaint_modif.getSelectionModel().getSelectedItems().get(0).toString();
        System.out.println(valeur);
        String id = valeur.substring(1, valeur.indexOf(","));
        int Id = Integer.parseInt(id);
        cs.Supprimer(Id);
        rafraichir_table(tableV_complaint_modif);
        rafraichir_table(tableV_complaint);
    }

    @FXML
    private void load(ActionEvent event) {
            
         listview.setExpanded(true);
     listview.depthProperty().set(1);
    }

    @FXML
    private void test(MouseEvent event) {
        System.out.println(listview.getSelectionModel().getSelectedItem());
        if(event.getClickCount()==2)
        {Complaint_Services cs = new Complaint_Services();
        cs.Seen(listview.getSelectionModel().getSelectedItem().getId());
Alert alert = new Alert(AlertType.INFORMATION);
alert.setGraphic(null);
alert.setTitle(null);
alert.setHeaderText(listview.getSelectionModel().getSelectedItem().getTitle());
alert.setContentText(listview.getSelectionModel().getSelectedItem().getDescription());
alert.setContentText(listview.getSelectionModel().getSelectedItem().getReponse());


alert.showAndWait();
        }
    }

    @FXML
    private void chercher_complaint_list(KeyEvent event) {
         listview.getItems().clear();
      Complaint_Services cs=new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.recherche2(tf_chercher_complaint_list.getText(), listview));
      
     listview.getItems().setAll(o);
       
    }
    
    

  

   

  
   
    
}

