/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev2.ComplaintListCell;
import pidev2.Entities.Complaint;
import pidev2.Entities.Employee;
import pidev2.Service.Complaint_Services;


/**
 *
 * @author Nizar
 */
public class FXMLDocumentController implements Initializable {
   
    
    
    
    
   
   
    @FXML
    private TextField Titre;
 
    @FXML
    private TextArea Descriptif;
    @FXML
    private Button Benvoyer_rec;
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
      
      
/*LV_complaints.setCellFactory(new Callback<ListView<Complaint>, ListCell<Complaint>>() {
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
        });*/
LV_complaints.getItems().setAll(o);
LV_complaints.setCellFactory(new Callback<ListView<Complaint>,ListCell<Complaint>>() {
            @Override
            public ListCell<Complaint> call(ListView<Complaint
                    > param) {
                return new ComplaintListCell();
            }
        });



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
alert.setHeaderText("Titre : "+LV_complaints.getSelectionModel().getSelectedItem().getTitle());
//alert.setContentText("Descriptif :"+LV_complaints.getSelectionModel().getSelectedItem().getDescription());
//alert.setContentText("Reponse : "+LV_complaints.getSelectionModel().getSelectedItem().getReponse());
 JFXTextField DESCRIP = new JFXTextField();
    DESCRIP.setText(LV_complaints.getSelectionModel().getSelectedItem().getDescription());
    DESCRIP.setEditable(false);
    DESCRIP.setPromptText("Descriptif");
    
    JFXTextField REP = new JFXTextField();
    REP.setEditable(false);
    
    REP.setText(LV_complaints.getSelectionModel().getSelectedItem().getReponse());
     GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(20, 150, 10, 10));
    
 gridPane.add(DESCRIP, 13, 0);
 gridPane.add(REP, 13, 2);
alert.getDialogPane().setContent(gridPane);

alert.showAndWait();
        }
          rafraichir_liste(LV_complaints);
         
    }

    @FXML
    private void chercher_complaint_list(KeyEvent event) {
         LV_complaints.getItems().clear();
      Complaint_Services cs=new Complaint_Services();
      ObservableList o= FXCollections.observableArrayList(cs.recherche2(tf_chercher_complaint_list.getText(), LV_complaints));
      
     LV_complaints.getItems().setAll(o);
       
    }
    public void generatePDF(String titre,String desc,String reponse) throws IOException,
			FileNotFoundException, DocumentException {
		PdfReader pdfTemplate = new PdfReader("mytemplate.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream("test3.pdf");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
		stamper.setFormFlattening(true);

		stamper.getAcroFields().setField("titre", titre);
		stamper.getAcroFields().setField("descriptif", desc);
		stamper.getAcroFields().setField("reponse",reponse);
		stamper.close();
		pdfTemplate.close();

	}
    
    

  

   

  
   
    
}

