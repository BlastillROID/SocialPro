/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nizar
 */
public class PiDev2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLRRH_complaint.fxml"));
        
        Scene scene = new Scene(root);
        Scene scene2=new Scene(root2);
        
        Stage stage2=new Stage();
                stage2.setScene(scene2);
       
      // Scene scene2 = new Scene(root2);
        
        stage.setScene(scene);
        stage.show();
        stage2.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
