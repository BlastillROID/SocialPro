/*
 * To change this license header, choose License Headethis.getRs() in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import pidev.DataBase.DataSource;
import pidev.Entities.Employee;
import pidev.Entities.Projects;
import pidev.InterfaceService.IProjects_Sevices;

/**
 *
 * @author LordGoats
 */
public class Projects_Services extends Generenic_Class_Crud implements IProjects_Sevices {

    public Projects_Services() {
        connection = DataSource.getinstance().getConnection();
    }

    @Override
    public void Ajout(Projects O) {
        try {
            String req = "insert into Projects values (?,?,?,?)";

            this.setPs(connection.prepareStatement(req));

            this.getPs().setInt(1, O.getProject_ID());
            this.getPs().setString(2, O.getProject_Name());
            this.getPs().setString(3, O.getProject_Desc());
            this.getPs().setInt(4, O.getManager_ID());

            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Projects_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Modif(Projects O) {
        try {
            String req = "UPDATE Projects SET  project_name = ? , project_desc =?, manager_id =? WHERE  project_id=?";

            this.setPs(connection.prepareStatement(req));

            this.getPs().setString(1, O.getProject_Name());
            this.getPs().setString(2, O.getProject_Desc());
            this.getPs().setInt(3, O.getManager_ID());
            this.getPs().setInt(4, O.getProject_ID());

            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Suppression(Projects O) {
        try {
            String req = "DELETE FROM projects WHERE project_ID=?";

            this.setPs(connection.prepareStatement(req));

            this.getPs().setInt(1, O.getProject_ID());

            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Projects> Affichage() {
        String req = "select * from Projects";
        List<Projects> Projectslist = new ArrayList<>();

        try {
            this.setPs(connection.prepareStatement(req));
            Projects_Services.setRs(this.getPs().executeQuery());

            while (Projects_Services.getRs().next()) {
                Projects P = new Projects(Projects_Services.getRs().getInt(1), Projects_Services.getRs().getString(2), Projects_Services.getRs().getString(3), Projects_Services.getRs().getInt(4));
                Projectslist.add(P);
            }
            return Projectslist;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Projectslist;
    }
    public ObservableList<Projects> FillTV(){
        ObservableList data = FXCollections.observableArrayList();

    String req = "select * from Projects";
        try {
            this.setPs(connection.prepareStatement(req));
            Projects_Services.setRs(this.getPs().executeQuery());
           // Projects P= new Projects();
               
             while (Projects_Services.getRs().next()) {
                                 Projects P = new Projects(Projects_Services.getRs().getInt(1), Projects_Services.getRs().getString(2), Projects_Services.getRs().getString(3), Projects_Services.getRs().getInt(4));

                data.add(P);
                }
                
            
             return data;
            //tabla.setItems(data);          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    public ObservableList SuperFill(String Column)
    {   
        ObservableList data = FXCollections.observableArrayList();
    String req = "select "+Column+" from Projects";
        try {
            this.setPs(connection.prepareStatement(req));
            Projects_Services.setRs(this.getPs().executeQuery());
           // Projects P= new Projects();
               
             while (Projects_Services.getRs().next()) {
                                 if ("Project_ID".equals(Column))
                                 { data.add( Projects_Services.getRs().getInt(1));}
                                 
                                 else
                                 {
                                   data.add( Projects_Services.getRs().getString(1));
                                 }

               
                }
                
            
             return data;
            //tabla.setItems(data);          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    public  void chargeTableauDonnees(TableView tableProjet) {

        ObservableList<ObservableList> obs = FXCollections.observableArrayList();
         try{
            
            
            String sqlp = "SELECT P.Project_ID,P.Project_Name,P.Project_Desc,E.Name FROM Projects As P Join Employee As E on P.manager_ID=E.ID";
           Projects_Services.setRs(connection.createStatement().executeQuery(sqlp));
            // Titres de colonnes
            String[] titres = {                             
                    "id",
                    "titre",
                    "description",
                    "id employee",
                    
 
            };
            // COLONNE DE TABLE AJOUTÉE DYNAMIQUEMENT
            
            for (int i = 0; i < Projects_Services.getRs().getMetaData().getColumnCount(); i++ ) {
                final int j = i;
                TableColumn col = new TableColumn(titres[i]);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){                   
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> parametre) {                                                                                             
                        return new SimpleStringProperty((String)parametre.getValue().get(j));                       
                    }                   
                });
                tableProjet.getColumns().addAll(col);
                // Attribution de taille aux colonnes
                col.setMinWidth(100);
                System.out.println("Column ["+i+"] ");
                // Table de données Centre
                col.setCellFactory(new Callback<TableColumn<String,String>,TableCell<String,String>>(){
                    @Override
                    public TableCell<String, String> call(TableColumn<String, String> p) {
                        TableCell cell = new TableCell(){
                            @Override
                            protected void updateItem(Object t, boolean bln) {
                                if(t != null){
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
            // chargement à partir de la base de données
            while(Projects_Services.getRs().next()){
                //preparation de ligne
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1 ; i <= Projects_Services.getRs().getMetaData().getColumnCount(); i++){
                    //iteration des colonnes
                    row.add(Projects_Services.getRs().getString(i));
                }
                System.out.println("Row [1] added "+row );
                obs.addAll(row);
            }
            //ensuite l'ajout dans la tavle view
            tableProjet.setItems(obs);
          }catch(SQLException e){
              System.out.println("Erreur "+e);            
          }
    }

}
