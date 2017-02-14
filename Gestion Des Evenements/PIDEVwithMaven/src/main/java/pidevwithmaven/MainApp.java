/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevwithmaven;

import PIDev_Entities.Evenement;
import PIDev_Entities.Type_Evenement;
import PIDev_Sevices.Evenement_Services;
import PIDev_Sevices.Type_Evenement_Services;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author HP
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        //Type_Evenement TE = new Type_Evenement(1,"Professional");
        Type_Evenement_Services TES = new Type_Evenement_Services();
        Evenement_Services ES = new Evenement_Services();
        LocalDate d;
        d = LocalDate.of(2016, 2, 7);
        Date date = java.sql.Date.valueOf(d);
        Blob b = null;
        //Evenement E = new Evenement(1, date, "address", "description", b, "pending");
        //Evenement E1 = new Evenement(2, date,"address2","description2",b, "done");

//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//                //TES.Ajout(TE);
//                ES.Ajout(E1);
//                //ES.Modif(E, 2);
//                ES.Suppression(E, 2);
//                System.out.println("test here");
//              TES.Affichage().forEach(System.out::println);
//              ES.Affichage().forEach(System.out::println);
//
//            }
//        });
//
 //StackPane root = new StackPane();
   //root.getChildren().add(btn);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
