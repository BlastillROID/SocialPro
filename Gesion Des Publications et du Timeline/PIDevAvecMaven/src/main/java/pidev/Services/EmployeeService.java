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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.Connection.MyConnection;
import pidev.Entities.Employee;
import pidev.Interfaces.IEmployeeService;

/**
 *
 * @author salah
 */
public class EmployeeService implements IEmployeeService{

    @Override
    public int ajouterEmployee(Employee e) {
        int executeUpdate = 0;
        MyConnection m = new MyConnection();
        Connection c;
        try {
            c = m.Connect();
            PreparedStatement ps;
            String query = "INSERT INTO employee (name, email, phone, gender, birthday) VALUES (?,?,?,?,?)";
            ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,e.getFullName());
            ps.setString(2,e.getEmail());
            ps.setString  (3,e.getPhoneNumber());
            ps.setString(4,e.getGender());
            ps.setDate  (5,e.getDob());
            executeUpdate = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                  e.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeUpdate;
    }

    @Override
    public int modifierEmployee(Employee e) {
        int executeUpdate = 0;
        MyConnection m = new MyConnection();
        Connection c;
        try {
            c = m.Connect();
            PreparedStatement ps;
            String query = "UPDATE employee SET name = ?,email = ?,phone = ?,birthday  = ? WHERE id = ?";
            ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString (1, e.getFullName());
            ps.setString (2, e.getEmail());
            ps.setString (3, e.getPhoneNumber());
            ps.setDate   (4, e.getDob());
            ps.setInt    (5, e.getId());
            executeUpdate = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeUpdate;
    }

    @Override
    public void supprimerEmployee(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet afficherEmployee(int sc, int ec) {
        Connection c;
        ResultSet rs = null;
        MyConnection m = new MyConnection();
        try {
            c = m.Connect();
            String SQL = "SELECT * from employee where id > ? and id <= ?";
            PreparedStatement ps;
            ps = c.prepareCall(SQL);
            ps.setInt(1, sc);
            ps.setInt(2, ec);
            rs = ps.executeQuery();
        }catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public String getJobTitle(int id) {
        String jobTitle = "";
        MyConnection m = new MyConnection();
        Connection c;
        try {
            c = m.Connect();
            PreparedStatement ps,jobPs;
            String query = "SELECT employee.job_id, jobs.job_desc FROM employee join jobs on employee.job_id =jobs.job_id where employee.id = ?";
            ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                jobTitle = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobTitle;
    }

    @Override
    public String getName(int id) {
        String empName = "";
        MyConnection m = new MyConnection();
        Connection c;
        try {
            c = m.Connect();
            PreparedStatement ps,jobPs;
            String query = "select name from employee join user on user.employee_id = employee.id where user.id = ?";
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                empName = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empName;
    }

    @Override
    public int getIdByUser(String user) {
        int id = 0;
        MyConnection m = new MyConnection();
        Connection c;
        try {
            c = m.Connect();
            PreparedStatement ps,jobPs;
            String query = "select employee.id from employee join user on user.employee_id = employee.id where user.username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    @Override
    public String getPhoneByUser(String user) {
        String phone = "";
        try{
            String query = "select phone from employee join user on user.employee_id = employee.id where user.username = ?";
            MyConnection m = new MyConnection();
            Connection c = m.Connect();
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                phone = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phone;
    }
    
}
