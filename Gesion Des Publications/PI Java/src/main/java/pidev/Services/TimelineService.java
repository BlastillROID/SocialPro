/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.Connection.MyConnection;
import pidev.Entities.Timeline;
import pidev.Interfaces.ITimeline;

/**
 *
 * @author dell
 */
public class TimelineService implements ITimeline {
    
    private PreparedStatement pst;
    private static ResultSet rs;
    MyConnection m = new MyConnection();
    Connection c;

    @Override
    public void ajouterTimeline(Timeline t) {
        String requete = "insert into timeline (timeline_description) values (?)";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setString(1, t.getDescription());
            pst.executeUpdate();
            System.out.println("Timeline ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(TimelineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierTimeline(Timeline t) {
        String requete = "update timeline set timeline_description=? where timeline.timeline_id = ?";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setString(1,t.getDescription());
            pst.setInt(2,t.getId());
            pst.executeUpdate();
            System.out.println("Timeline modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(TimelineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerTimeline(Timeline t) {
        String requete = "delete from timeline where (timeline.timeline_id) = ?";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Timeline supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(TimelineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void afficherTimeline(Timeline t) {
        String requete = "select * from timeline where timeline.timeline_id = ?";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, t.getId());
            rs = pst.executeQuery();
            Timeline tt;
            while (rs.next()) {
                tt = new Timeline(rs.getInt(1), rs.getString(2));
                System.out.println(+tt.getId()+","+tt.getDescription());
            }
            System.out.println("Affichage terminé !");
        } catch (SQLException ex) {
            Logger.getLogger(TimelineService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public ResultSet showTimeline() {
        try {
            String requete = "select * from timeline";
            c = m.Connect();
            pst = c.prepareStatement(requete);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}