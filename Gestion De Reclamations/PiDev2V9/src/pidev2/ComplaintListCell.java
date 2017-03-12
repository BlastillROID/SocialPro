/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        carIcon.setFitWidth(75);
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
    }
    
    
}
