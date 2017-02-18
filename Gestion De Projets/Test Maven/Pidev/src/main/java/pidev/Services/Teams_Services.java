/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import pidev.DataBase.DataSource;
import pidev.Entities.Teams;
import pidev.InterfaceService.ITeam_Services;

/**
 *
 * @author LordGoats
 */
public class Teams_Services implements ITeam_Services{
   private Connection connection;
    private PreparedStatement ps;

    public Teams_Services() {
        connection = DataSource.getinstance().getConnection();
    }
    @Override
    public void Ajout(Teams O) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modif(Teams O) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Suppression(Teams O) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Teams> Affichage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
