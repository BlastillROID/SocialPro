/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import java.io.File;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import pidev2.Entities.Complaint;

/**
 *
 * @author Nizar
 */
public class ComplaintListCell extends ListCell<Complaint>{
  private final GridPane gridPane = new GridPane();
    private final ImageView carIcon = new ImageView();
    private final Label DateLabel = new Label();
    private final Label EventNameLabel = new Label();
    private final Label typeLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final AnchorPane content = new AnchorPane();
    

    public ComplaintListCell() {
        carIcon.setFitWidth(32);
        carIcon.setPreserveRatio(true);
        GridPane.setConstraints(carIcon, 0, 0, 1, 3);
        GridPane.setValignment(carIcon, VPos.TOP);
        EventNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        GridPane.setConstraints(EventNameLabel, 1, 0);
        // 
        DateLabel.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
        GridPane.setConstraints(DateLabel, 2, 0);
        GridPane.setConstraints(typeLabel, 2, 1);

        descriptionLabel.setStyle("-fx-opacity: 0.75;");

        GridPane.setConstraints(descriptionLabel, 1, 1);
        GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE);
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));

        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.getChildren().setAll(carIcon, EventNameLabel, DateLabel, typeLabel, descriptionLabel);
        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);
        content.getChildren().add(gridPane);
     //  content.setId("pane");
      // content.getStyleClass().add("pane");
    }
  @Override
    protected void updateItem(Complaint item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {

            DateLabel.setText("Date : "+item.getDate_complaint().toString());
            EventNameLabel.setText("Titre : "+item.getTitle());
            if(item.getVu().equalsIgnoreCase("non vue"))
            {   typeLabel.setTextFill(Color.web("red"));
             typeLabel.setText(item.getVu());}
            else if(item.getVu().equalsIgnoreCase("vu"))
               {  typeLabel.setTextFill(Color.web("green"));
            typeLabel.setText(item.getVu());}
            else {
                typeLabel.setText("");
            }
            descriptionLabel.setText("Descriptif : "+item.getDescription());
         
            //  System.out.println(item.getMedia());
         //   if(item.getStatus().equals("terminé"))
           File file = new File("C:/Users/Nizar/Documents/NetBeansProjects/PiDev2/Style/tick.png");
           File file2 = new File("C:/Users/Nizar/Documents/NetBeansProjects/PiDev2/Style/loading.png");
           // Image img = new Image(file.toURI().toString(), 32, 32, true, true);
       if(item.getStatus().equalsIgnoreCase("terminé"))
            carIcon.setImage(new Image(file.toURI().toString(), 32, 32, true, true));
       else
            carIcon.setImage(new Image(file2.toURI().toString(), 32, 32, true, true));

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                            VBox vbox = new VBox(new Text(item.getEvenement_name()), new Text(item.getAddress_evenement()));
//                            vbox.setSpacing(15);
//                            HBox hbox = new HBox(new Label(""), vbox);
//                            hbox.setSpacing(10);
//                            setGraphic(hbox);

        }
    }
    
}
