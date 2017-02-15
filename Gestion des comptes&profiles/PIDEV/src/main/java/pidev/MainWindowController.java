/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Employee;
import Entities.Mail;
import Services.EmployeeService;
import Services.MailService;
import Services.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author salah
 */
public class MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static int stCount  = 0,
                       endCount = 20;
    
    private ObservableList<Object> data;
    @FXML
    private Label idLabel,jobLabel;
    @FXML
    private Tab tabModifier;
    @FXML
    private TableView empTV;
    @FXML
    private JFXTabPane EmpSubTabPane;
    @FXML
    private JFXHamburger hamberger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView inboxTable;

    /**********************************
    *     Labels for modification     *
    ***********************************/
    @FXML
    private TextField nameFieldMod,emailFieldMod,phoneFieldMod;
    @FXML
    private JFXDatePicker datePickMod;
    @FXML
    private JFXButton modifyButton;
    @FXML
    private JFXButton chgDonnesBtn;
    @FXML
    private JFXButton okBtn;
    /*
     * Elements of send a mail tab
     */
    @FXML
    private JFXTextField sendMailTo;
    @FXML
    private JFXTextField sendMailSub;
    @FXML
    private JFXButton sendMailBtn;
    @FXML
    private HTMLEditor sendMailBody;
    @FXML
    private JFXButton sendMailCancel;
    @FXML
    private VBox mailVbox;
    @FXML
    private WebView emailReader;
    /*
     * Table view data grabber function
     */
    
    
    public void readEmail(){
        mailVbox.getChildren().stream().forEach((n) ->{
            n.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if(e.getClickCount() == 2){
                    emailReader.getEngine().loadContent(n.getAccessibleText());
                }
            });
        });
    }
    public void mailListFactory(String from, String subject,String content,String image){
        HBox h = new HBox();
        Label subjectL = new Label(),
                 fromL = new Label();
        ImageView i = new ImageView(getClass().getResource("/fxml/assets/Untitled.png").toExternalForm());
        
        GridPane gp = new GridPane();
        
        subjectL.setText(subject);
        fromL.setText(from);
        
        h.setPrefWidth(304);
        h.setPrefHeight(60);
        h.setAccessibleText(content);
        i.setFitWidth (100);
        i.setFitHeight(100);
        i.setPreserveRatio(false);
        
        
        gp.getColumnConstraints().add(new ColumnConstraints(100,
                                                            300, 
                                                            Double.MAX_VALUE, 
                                                            Priority.ALWAYS, 
                                                            HPos.LEFT,
                                                            true)
                                    );
        gp.add(fromL,    0, 0);
        gp.add(subjectL, 0, 1);
        
        h.getChildren().add(i);
        h.getChildren().add(gp);
        
        mailVbox.getChildren().add(h);
    }
    @FXML
    private void sendMail() throws Exception{
        Employee e = new Employee();
        EmployeeService es = new EmployeeService();
        MailService ms     = new MailService();
        Mail m = new Mail(sendMailBody.getHtmlText(),
                          sendMailSub.getText());
        e.setId(es.getIdByUser(sendMailTo.getText()));
        System.out.println(e.getId());
        if(ms.envoyerMail(m, e) == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Query Result:");
            alert.setContentText("Mail sent!");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Query Result:");
            alert.setContentText("Error while handling request :(");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void logout() throws IOException{

    }
    @FXML
    private void okClicked(){
        nameFieldMod.setDisable(true);
        emailFieldMod.setDisable(true);
        phoneFieldMod.setDisable(true);
        datePickMod.setDisable(true);
        modifyButton.setVisible(false);
        EmpSubTabPane.getSelectionModel().select(0);
        tabModifier.setDisable(true);
        tabModifier.setText("Selectionnez un employee!");
    }
    @FXML
    private void changerClicked(){
        nameFieldMod.setDisable(false);
        emailFieldMod.setDisable(false);
        phoneFieldMod.setDisable(false);
        datePickMod.setDisable(false);
        modifyButton.setVisible(true);
        
    }
    @FXML
    private void modifyButtonClicked(){
        Employee e = new Employee(nameFieldMod.getText(),
                                  emailFieldMod.getText(), 
                                  phoneFieldMod.getText(), 
                                  "placeholder", 
                                  Date.valueOf(datePickMod.getValue().toString())
                                 );
        e.setId(Integer.parseInt(idLabel.getText()));
        EmployeeService es = new EmployeeService();
        int updateExec = es.modifierEmployee(e);
        if(updateExec == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Query Result:");
            alert.setContentText("Modified");
            alert.showAndWait();
            empTV.getItems().clear();
            fillTable();
            okClicked();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Query Result:");
            alert.setContentText("Error while handling request :(");
            alert.showAndWait();
        }
    }
    @FXML
    private void tableViewAction(){
        
        empTV.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(e.getClickCount() == 2){
                            
                tabModifier.setDisable(false);   
                EmployeeService es = new EmployeeService();
                //selection model to select tabpane
                SingleSelectionModel<Tab> sm = EmpSubTabPane.getSelectionModel();
                //change tabpane index (starting from 0)
                sm.select(1);
                /*We split*/
                String[] split = empTV.getSelectionModel().getSelectedItems().toString().replaceAll("\\s","").split(",");

                tabModifier  .setText(split[1]);
                nameFieldMod .setText(split[1]);
                emailFieldMod.setText(split[2]);
                phoneFieldMod.setText(split[3]);
                
                datePickMod.setValue(LocalDate.of(Integer.parseInt(split[5].split("-")[0]),
                                                  Integer.parseInt(split[5].split("-")[1]),
                                                  Integer.parseInt(split[5].split("-")[2])
                                                 )
                                    );
                idLabel.setText(split[0].replaceAll("\\W+", ""));
            jobLabel.setText(es.getJobTitle(Integer.parseInt(idLabel.getText())));
            }
        });
    }
    @FXML
    private void pagePreviousClicked(){
        stCount -= 20;
        if(stCount <= 0)
            stCount = 0;
        endCount -= 20;
        if(endCount <= 20)
            endCount = 20;
        empTV.getItems().clear();
        fillTable();
    }
    @FXML
    private void pageNextClicked(){
        empTV.getItems().clear();
        stCount += 20;
        endCount += 20;
        fillTable();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modifyButton.setVisible(false);
        MailService ms = new MailService();
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/sidepanel.fxml"));
            drawer.setSidePane(box);
            box.getChildren().stream().filter((node) -> (node.getAccessibleText() != null )).map((node) -> {
                return node;
            }).forEach((node) -> {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    switch(node.getAccessibleText()){
                        case "Logout" :
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
                                    stage = (Stage)hamberger.getScene().getWindow();
                                    stage.setScene(scene);
                                    Session.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                    }
                });
            });
            tabModifier.setDisable(true);
            HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamberger);
            burgerTask.setRate(-1);
            hamberger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
                if(drawer.isShown()){
                    drawer.close();
                    drawer.toBack();
                }else{
                    drawer.toFront();
                    drawer.open();
                }
            });
            fillTable();
           /*
            * Reading email part of the initializer
            */
            if(Session.getCurrentSession() != 0){
                ResultSet rs = ms.recevoirMail();
            while(rs.next()){
                System.out.println(rs.getString(4));
                mailListFactory(rs.getString(3),
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(4)
                                );
            }
            }
                
            mailVbox.getChildren().stream().forEach((n) ->{
                n.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    if(e.getClickCount() == 2){
                        emailReader.getEngine().loadContent(n.getAccessibleText());
                    }
                });
            });
            System.out.println(Session.getCurrentSession());
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } /*catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    public void fillTable(){
        EmployeeService es = new EmployeeService();
        data = FXCollections.observableArrayList();
        try{
            ResultSet rs = es.afficherEmployee(stCount, endCount);
            if(empTV.getColumns().isEmpty()){ 
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                    final int j = i;               
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                             
                            return new SimpleStringProperty(param.getValue().get(j).toString());                       
                        }                   
                    });
                    empTV.getColumns().addAll(col);
                }
            }
            empTV.setItems(data);

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            
        }catch(Exception e){
            System.out.println("Error on Building Data");
            e.printStackTrace();
        }
    }
}
