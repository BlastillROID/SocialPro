/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import pidev.Connection.MyConnection;
import pidev.Entities.Profile;
import pidev.Interfaces.IProfileService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salah
 */
public class ProfileService implements IProfileService{

    @Override
    public void ajouterProfile(Profile p) {
        MyConnection m = new MyConnection();
        Connection c;
        PreparedStatement usrPs;
        try {
            c = m.Connect();
            String usrQry = "INSERT INTO profile(user_id, profile_picture, timeline_picture) "
                          + "VALUES (?,?,?)";
            usrPs = c.prepareStatement(usrQry);
            usrPs.setInt    (1,p.getUserId());
            usrPs.setString (2,p.getProfilePicURL());
            usrPs.setString (3,p.getTimelinePicURL());
            usrPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResultSet afficherProfile(Profile p) {
        Connection c;
        ResultSet rs = null;
        MyConnection m = new MyConnection();
        PreparedStatement pst;
        try {
            c = m.Connect();
            String requete = "SELECT * FROM profile WHERE user_id=?";
            pst = c.prepareStatement(requete);
            pst.setInt(1, p.getUserId());
            rs = pst.executeQuery();
        }catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
