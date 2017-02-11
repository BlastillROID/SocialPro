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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev2.DataBase.DataSource;
import pidev2.Entities.Employee;
import pidev2.InterfaceService.IEmployee_Services;

/**
 *
 * @author LordGoats
 */
public class Employee_Services implements IEmployee_Services{
    private Connection connection;
    private PreparedStatement ps;
    private static ResultSet rs;

    public Employee_Services() {
        connection = DataSource.getinstance().getConnection();
    }

    @Override
    public void Ajout(Employee O) {
        try {
            String req = "insert into Employee values (?,?,?,?,?,?,?,?,?)";
            
            ps = connection.prepareStatement(req);
            ps.setInt(1,O.getID());
            ps.setString(2, O.getName());
            ps.setString(3,O.getEmail());
            ps.setString(4, O.getPhone());
            ps.setString(5,O.getGender()) ;
            ps.setDate(6, (Date) O.getBDate());
            ps.setInt(7, O.getManagerID());
            ps.setInt(8, O.getTeam_ID());
            ps.setInt(9,O.getJob_ID());
            
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Modif(Employee O) {
          try {
            String req = "UPDATE employee SET name=?,email=?,phone=?,gender= ?, birthday=? WHERE ID=?";
            
            ps = connection.prepareStatement(req);
            
            ps.setString(1, O.getName());
            ps.setString(2,O.getEmail());
            ps.setString(3, O.getPhone());
            ps.setString(4,O.getGender()) ;
            ps.setDate(5, (Date) O.getBDate());
            ps.setInt(6, O.getID());
            
            
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Suppression(Employee O) {
        try {
            String req = "DELETE FROM employee WHERE ID=?";
            
            ps = connection.prepareStatement(req);
            
            
            ps.setInt(1, O.getID());
            
            
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Employee> Affichage() {
                   String requete = "select * from employee";
        List<Employee> Employees = new ArrayList<>();

        try {
             ps = connection.prepareStatement(requete);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                Employee P = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                Employees.add(P);
            }
            return Employees;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Employees;
    }
    
}
