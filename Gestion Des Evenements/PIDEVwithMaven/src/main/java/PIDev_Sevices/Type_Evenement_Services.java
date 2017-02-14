/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_Sevices;

import PIDev_DataBase.DataSource;
import PIDev_Entities.Evenement;
import PIDev_Entities.Type_Evenement;
import PIDev_InterfaceServices.IType_Evenement_Services;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Type_Evenement_Services extends Generic_Class_Crud implements IType_Evenement_Services {

    public Type_Evenement_Services() {
        connection = DataSource.getinstance().getConnection();
    }

    @Override
    public void Ajout(Type_Evenement TE) {
        try {
            String req = "insert into event_type values (?,?)";
            this.setPs(connection.prepareStatement(req));
            this.getPs().setInt(1, TE.getId_type());
             this.getPs().setString(2, TE.getName());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getEventType()
    {
        
            String req = "Select event_type from event_type";
             List<String> LTE = new ArrayList<>();
        
     try {
            this.setPs(connection.prepareStatement(req));
             Type_Evenement_Services.setRs(this.getPs().executeQuery());

            while (Evenement_Services.getRs().next()) {
              String TE = Evenement_Services.getRs().getString(1);
                LTE.add(TE);
                
          }
            
            return LTE;
         
        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
             return LTE;
    }
    
    
    @Override
     public int getEventTypeId(String nom)
    {
        
            String req = "Select event_id from event_type where event_type='"+nom+"'" ;
           System.out.println(req);
        
     try {
            this.setPs(connection.prepareStatement(req));
             Type_Evenement_Services.setRs(this.getPs().executeQuery());

            while (Evenement_Services.getRs().next()) {
             int id = Evenement_Services.getRs().getInt(1);
      
             return id; 
                
          }
            
     
         
        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
     return 0;
    }
    @Override
    public void Modif(Type_Evenement TE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Suppression(Type_Evenement TE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Type_Evenement> Affichage() {
        String req = "select * from event_type ";
        List<Type_Evenement> LTE = new ArrayList<>();
        try {
            this.setPs(connection.prepareStatement(req));
            Type_Evenement_Services.setRs(this.getPs().executeQuery());
            while (Type_Evenement_Services.getRs().next()) {
                Type_Evenement TE = new Type_Evenement(Type_Evenement_Services.getRs().getInt(1), Type_Evenement_Services.getRs().getString(2));
                LTE.add(TE);
            }
            return LTE;
        } catch (SQLException ex) {
            Logger.getLogger(Type_Evenement_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LTE;
    }

}
