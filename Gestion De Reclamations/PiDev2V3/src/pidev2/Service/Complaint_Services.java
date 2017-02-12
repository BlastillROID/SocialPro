/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import pidev2.DataBase.DataSource;
import pidev2.Entities.Complaint;
import pidev2.InterfaceService.IComplaint_Services;

/**
 *
 * @author Nizar
 */
public class Complaint_Services implements IComplaint_Services{
 private Connection connection;
    private PreparedStatement ps;
     private TableColumn col;
    ObservableList<ObservableList> obs;
    
    private  ResultSet rs;
   public Complaint_Services() {
    
        connection = DataSource.getinstance().getConnection();
    }
    
    @Override
    public void Ajout(Complaint C ) {
     try {
         
         String req = "INSERT INTO `complaint`(`title`, `description`, `employee_id`) VALUES (?,?,?)";
         ps = connection.prepareStatement(req);
         ps.setString(1, C.getTitle());
         ps.setString(2, C.getDescription());
         ps.setInt(3, C.getEmployee_id());
         ps.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(Complaint_Services.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

   

    @Override
    public void Supprimer(int id) {
 try {
            String req = "DELETE FROM complaint WHERE complaint_id=?";
            
            ps = connection.prepareStatement(req);
            
            
            ps.setInt(1, id);
            
            
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Complaint_Services.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    public static LocalDate fromDate(Date date) {
    Instant instant = Instant.ofEpochMilli(date.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        .toLocalDate();
  }

    @Override
    public List<Complaint> Affichage() {
         String requete = "SELECT * FROM `complaint` ";
        List<Complaint> Complaints = new ArrayList<>();
         try {
             ps = connection.prepareStatement(requete);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                Complaint C = new Complaint(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4),fromDate(rs.getDate(5)),rs.getString(6));
                Complaints.add(C);
            }
            return Complaints;
        } 
         catch (SQLException ex) {
            Logger.getLogger(Complaint_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
         return Complaints;
    }
     public Complaint getById(Integer id) {
        String req = "select complaint_id,title,description from complaint where complaint_id =?";
         Complaint c = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new Complaint(rs.getInt(1),rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
 @Override
     public void Modifier(int id,String titre,String description) {
         Complaint c=getById(id);
         System.out.println("****");
         System.out.println(id);
         System.out.println(titre);
         System.out.println(description);
         System.out.println("****");
         try {
            String req = "UPDATE complaint SET title=?,description=? WHERE complaint_id="+id+"";
            
            ps = connection.prepareStatement(req);
            
            ps.setString(1, titre);
            ps.setString(2,description);
          //  ps.setInt(3, c.getId());
           
            
            
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
 @Override
      public  void chargeTableauDonnees(TableView table) {
         
         obs= FXCollections.observableArrayList();
         try{
            
            
            String sqlp = "SELECT complaint_id,title,description,employee_id,date_complaint,status FROM complaint";
           rs = connection.createStatement().executeQuery(sqlp);
            // Titres de colonnes
            String[] titres = {                             
                    "id",
                    "titre",
                    "description",
                    "id employee",
                    "date de reclamation",
                    "status",
                    
 
            };
            // COLONNE DE TABLE AJOUTÉE DYNAMIQUEMENT
            
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++ ) {
                final int j = i;
                col = new TableColumn(titres[i]);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){                   
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> parametre) {                                                                                             
                        return new SimpleStringProperty((String)parametre.getValue().get(j));                       
                    }                   
                });
                table.getColumns().addAll(col);
                // Attribution de taille aux colonnes
                col.setMinWidth(100);
               // System.out.println("Column ["+i+"] ");
                // Table de données Centre
                col.setCellFactory(new Callback<TableColumn<String,String>,TableCell<String,String>>(){
                    @Override
                    public TableCell<String, String> call(TableColumn<String, String> p) {
                        TableCell cell = new TableCell(){
                            @Override
                            protected void updateItem(Object t, boolean bln) {
                                if(t != null){
                                    super.updateItem(t, bln);
                                //  System.out.println(t);
                                    setText(t.toString());
                                    setAlignment(Pos.CENTER); // Réglage de l'alignement
                                }
                            }
                        };
                      //  col.setCellFactory(TextFieldTableCell.forTableColumn());
                        return cell;
                    }
                });
                
            }
            // chargement à partir de la base de données
            while(rs.next()){
                //preparation de ligne
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++){
                    //iteration des colonnes
                    row.add(rs.getString(i));
                }
               // System.out.println("Row [1] added "+row );
                obs.addAll(row);
            }
            //ensuite l'ajout dans la tavle view
            table.setItems(obs);
          }catch(SQLException e){
              System.out.println("Erreur "+e);            
          }
    }
     
   public void recherche(String v, TableView tableProjet) {

       obs= FXCollections.observableArrayList();

        try {
             String sql = "SELECT * FROM complaint WHERE complaint_id LIKE '%"+v+"%' OR title LIKE '%"+v+"%' or employee_id LIKE '%"+v+"%'  ORDER BY complaint_id DESC";
            
            rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next()){
                //preparation de ligne
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++){
                    //iteration des colonnes
                    row.add(rs.getString(i));
                }
               // System.out.println("Row [1] added "+row );
               
                obs.addAll(row);
     
            }
            //ensuite l'ajout dans la table view
            tableProjet.setItems(obs);
                             
          }catch(SQLException e){
              System.out.println("Erreur "+e);            
          }
        
    }

    @Override
    public void repondre(int id,String reponse) {
          try {
            String req = "UPDATE complaint SET answer=? WHERE complaint_id=?";
            
            ps = connection.prepareStatement(req);
            
            ps.setString(1, reponse);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
 
  
   
    
}
