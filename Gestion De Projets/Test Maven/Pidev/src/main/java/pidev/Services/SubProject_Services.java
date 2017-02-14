/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.DataBase.DataSource;
import pidev.Entities.Projects;
import pidev.Entities.SubProject;
import pidev.InterfaceService.ISubProject_Services;

/**
 *
 * @author LordGoats
 */
public class SubProject_Services extends Generenic_Class_Crud implements ISubProject_Services{
   

    public SubProject_Services() {
        connection = DataSource.getinstance().getConnection();
    }
    @Override
    public void Ajout(SubProject O) {
try {
            String req = "insert into Project values (?,?,?,?)";

            this.setPs(connection.prepareStatement(req));

            this.getPs().setInt(1, O.getSubProject_ID());
            this.getPs().setInt(3, O.getProject_ID());
            this.getPs().setString(4, O.getSubProject_Name());
            this.getPs().setInt(2, O.getTeam_ID());

            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Projects_Services.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void Modif(SubProject O) {
        try {
            String req = "UPDATE sub_Projects SET   project_id = ?, sub_project_name = ?, team_id = ? WHERE sub_project_id=?";

            this.setPs(connection.prepareStatement(req));

            
            this.getPs().setInt(1, O.getProject_ID());
            this.getPs().setString(2, O.getSubProject_Name());
            this.getPs().setInt(3, O.getTeam_ID());
            this.getPs().setInt(4, O.getSubProject_ID());
            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Suppression(SubProject O) {
        try {
            String req = "DELETE FROM sub_projects WHERE sub_project_ID=?";

            this.setPs(connection.prepareStatement(req));

            this.getPs().setInt(1, O.getSubProject_ID());

            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SubProject> Affichage() {
        String req = "select * from Sub_Projects";
        List<SubProject> SubProjectslist = new ArrayList<>();

        try {
            this.setPs(connection.prepareStatement(req));
            SubProject_Services.setRs(this.getPs().executeQuery());

            while (Projects_Services.getRs().next()) {
                SubProject P = new SubProject(SubProject_Services.getRs().getInt(1), SubProject_Services.getRs().getInt(2), SubProject_Services.getRs().getString(3), SubProject_Services.getRs().getInt(4));
                SubProjectslist.add(P);
            }
            return SubProjectslist;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubProjectslist;
    }

    
}
