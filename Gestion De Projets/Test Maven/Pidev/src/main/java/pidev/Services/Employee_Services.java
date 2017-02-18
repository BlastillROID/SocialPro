/*
 * To change this license header, choose License Headethis.getRs() in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.DataBase.DataSource;
import pidev.Entities.Employee;
import pidev.InterfaceService.IEmployee_Services;

/**
 *
 * @author LordGoats
 */
public class Employee_Services extends Generenic_Class_Crud implements IEmployee_Services{
   
    public Employee_Services() {
        connection = DataSource.getinstance().getConnection();
    }

    @Override
    public void Ajout(Employee O) {
        try {
            String req = "insert into Employee values (?,?,?,?,?,?,?,?,?)";
            
            this.setPs(connection.prepareStatement(req));
            this.getPs().setInt(1,O.getID());
            this.getPs().setString(2, O.getName());
            this.getPs().setString(3,O.getEmail());
            this.getPs().setString(4, O.getPhone());
            this.getPs().setString(5,O.getGender()) ;
            this.getPs().setDate(6, (Date) O.getBDate());
            this.getPs().setInt(7, O.getManagerID());
            this.getPs().setInt(8, O.getTeam_ID());
            this.getPs().setInt(9,O.getJob_ID());
            
            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Modif(Employee O) {
          try {
            String req = "UPDATE employee SET name=?,email=?,phone=?,gender= ?, birthday=? WHERE ID=?";
            
            this.setPs(connection.prepareStatement(req));
            
            this.getPs().setString(1, O.getName());
            this.getPs().setString(2,O.getEmail());
            this.getPs().setString(3, O.getPhone());
            this.getPs().setString(4,O.getGender()) ;
            this.getPs().setDate(5, (Date) O.getBDate());
            this.getPs().setInt(6, O.getID());
            
            
            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Suppression(Employee O) {
        try {
            String req = "DELETE FROM employee WHERE ID=?";
            
            this.setPs(connection.prepareStatement(req));
            
            
            this.getPs().setInt(1, O.getID());
            
            
            //this.getPs().setInt(2, product.getCreator().getId());
            this.getPs().executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Employee> Affichage() {
                   String req = "select * from employee";
        List<Employee> Employees = new ArrayList<>();

        try {
            this.setPs(connection.prepareStatement(req));
            Employee_Services.setRs(this.getPs().executeQuery());
           
            while (Employee_Services.getRs().next()) {
                Employee P = new Employee(Employee_Services.getRs().getInt(1), Employee_Services.getRs().getString(2), Employee_Services.getRs().getString(3), Employee_Services.getRs().getString(4), Employee_Services.getRs().getString(5), Employee_Services.getRs().getDate(6), Employee_Services.getRs().getInt(7), Employee_Services.getRs().getInt(8), Employee_Services.getRs().getInt(9));
                Employees.add(P);
            }
            return Employees;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Employees;
    }
    
    
    public List<String> ListByJob(){
         String req = "select ID, Name  from employee where Job_ID = 2 ";
        List<String> List_Job = new ArrayList<>();

        try {
            this.setPs(connection.prepareStatement(req));
            Employee_Services.setRs(this.getPs().executeQuery());
           
            while (Employee_Services.getRs().next()) {
                String P = Integer.toString(Employee_Services.getRs().getInt(1))+" : "+Employee_Services.getRs().getNString(2) ;
                List_Job.add(P);
            }
            return List_Job;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return List_Job;
    
    }
    
}
