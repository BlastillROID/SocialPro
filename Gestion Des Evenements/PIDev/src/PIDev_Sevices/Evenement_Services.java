/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_Sevices;

import PIDev_DataBase.DataSource;
import PIDev_Entities.Evenement;
import PIDev_InterfaceServices.IEvenement_Services;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 *
 * @author HP
 */
public class Evenement_Services extends Generic_Class_Crud implements IEvenement_Services {

    public Evenement_Services() {
        connection = DataSource.getinstance().getConnection();
    }

    @Override
    public void Ajout(Evenement E) {
        try {
            String req = "insert into `events` (`type_id`, `event_name`, `event_date`, `event_address`, `event_description`, `event_media`, `state`,`id_employee`) values (?,?,?,?,?,?,?,?)";

            this.setPs(connection.prepareStatement(req));
            this.getPs().setInt(1, E.getType_id());
            this.getPs().setString(2, E.getEvenement_name());
            this.getPs().setDate(3, E.getDate_evenement());
            this.getPs().setString(4, E.getAddress_evenement());
            this.getPs().setString(5, E.getDescription());
            this.getPs().setString(6, E.getMedia());
            this.getPs().setString(7, "pending");
            this.getPs().setInt(8, 1);
            System.out.println(req);
            this.getPs().executeUpdate();
            System.out.println("DONE");
        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chargeTableauDonnees(TableView tableProjet) {

        ObservableList<ObservableList> obs = FXCollections.observableArrayList();
        try {

            String sqlp = "SELECT E.event_id,A.event_type,E.event_date,E.event_address FROM events as E JOIN event_type as A on E.type_id=A.event_id";

            Evenement_Services.setRs(connection.createStatement().executeQuery(sqlp));
            // Titres de colonnes
            String[] titres = {
                "Id Event",
                "Event Type",
                "Date",
                "Address"

            };
            // COLONNE DE TABLE AJOUTÉE DYNAMIQUEMENT

            for (int i = 0; i < Evenement_Services.getRs().getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(titres[i]);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> parametre) {
                        return new SimpleStringProperty((String) parametre.getValue().get(j));
                    }
                });
                tableProjet.getColumns().addAll(col);
                // Attribution de taille aux colonnes
                col.setMinWidth(100);
                System.out.println("Column [" + i + "] ");
                // Table de données Centre
                col.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                    @Override
                    public TableCell<String, String> call(TableColumn<String, String> p) {
                        TableCell cell = new TableCell() {
                            @Override
                            protected void updateItem(Object t, boolean bln) {
                                if (t != null) {
                                    super.updateItem(t, bln);
                                    System.out.println(t);
                                    setText(t.toString());
                                    setAlignment(Pos.CENTER); // Réglage de l'alignement
                                }
                            }
                        };
                        return cell;
                    }
                });
            }
            System.out.println(" i am here ");

            // chargement à partir de la base de données
            while (Evenement_Services.getRs().next()) {
                //preparation de ligne
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= Evenement_Services.getRs().getMetaData().getColumnCount(); i++) {
                    //iteration des colonnes
                    row.add(Evenement_Services.getRs().getString(i));
                }
                System.out.println("Row [1] added " + row);
                obs.addAll(row);
            }
            //ensuite l'ajout dans la tavle view
            tableProjet.setItems(obs);
        } catch (SQLException e) {
            System.out.println("Erreur " + e);
        }
    }

//    @Override
//    public void Modif(Evenement E, int id) {
//        try {
//            String req = " UPDATE events SET type_id = ? , event_date = ? , event_adress= ? , event_description = ? , event_media = ? , state = ?  WHERE event_id = ? ";
//
//            this.setPs(connection.prepareStatement(req));
//            this.getPs().setInt(1, E.getType_id());
//            this.getPs().setDate(2, E.getDate_evenement());
//            this.getPs().setString(3, E.getAddress_evenement());
//            this.getPs().setString(4, E.getDescription());
//            this.getPs().set(5, E.getMedia());
//            this.getPs().setString(6, E.getState());
//            this.getPs().setInt(7, id);
//            this.getPs().executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public Evenement getEvenemnetById(int id) {

        String req = "Select * from events where event_id=" + id;
        System.out.println(req);

        try {
            this.setPs(connection.prepareStatement(req));
            Type_Evenement_Services.setRs(this.getPs().executeQuery());

            while (Evenement_Services.getRs().next()) {
                Evenement E = new Evenement(Evenement_Services.getRs().getInt(1),
                        Evenement_Services.getRs().getString(2),
                        Evenement_Services.getRs().getInt(3),
                        Evenement_Services.getRs().getDate(4),
                        Evenement_Services.getRs().getString(5),
                        Evenement_Services.getRs().getString(6),
                        Evenement_Services.getRs().getString(7),
                        Evenement_Services.getRs().getString(8),
                        Evenement_Services.getRs().getInt(9));
                return E;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void Suppression(int id) {
        try {

            String req = "DELETE from events  WHERE event_id = ? ";
            this.setPs(connection.prepareStatement(req));
            this.getPs().setInt(1, id);
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Evenement> Affichage() {
        String req = "select * from events ";
        List<Evenement> LE = new ArrayList<>();
        try {
            this.setPs(connection.prepareStatement(req));
            Evenement_Services.setRs(this.getPs().executeQuery());
            while (Evenement_Services.getRs().next()) {
                Evenement E = new Evenement(Evenement_Services.getRs().getInt(1), Evenement_Services.getRs().getInt(2), Evenement_Services.getRs().getDate(3), Evenement_Services.getRs().getString(4), Evenement_Services.getRs().getString(5), Evenement_Services.getRs().getString(6), Evenement_Services.getRs().getString(7), Evenement_Services.getRs().getInt(8));
                LE.add(E);
            }
            return LE;
        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LE;

    }

    @Override
    public ObservableList Affichage_TableView() {

        ObservableList OL = FXCollections.observableArrayList();
        String req = "select * from events ";
        try {
            this.setPs(connection.prepareStatement(req));

            Evenement_Services.setRs(this.getPs().executeQuery());
            while (Evenement_Services.getRs().next()) {
                Evenement E = new Evenement(Evenement_Services.getRs().getInt(1));
                OL.add(E);

            }
            return OL;
        } catch (SQLException ex) {
            Logger.getLogger(Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return OL;
    }

    @Override
    public void Modif(Evenement E, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
