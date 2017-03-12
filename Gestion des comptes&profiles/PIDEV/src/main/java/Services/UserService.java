/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.DBConnect;
import Interfaces.IUserService;
import Entities.User;
import java.sql.Statement;

/**
 *
 * @author salah
 */
public class UserService implements IUserService{

    @Override
    public int signUp(User u) {
        int usrExec = 0;
        DBConnect m = new DBConnect();
        Connection c;
        PreparedStatement usrPs;
        try {
            c = m.connect();
            String usrQry = "INSERT INTO user(username, password, employee_id) VALUES (?,?,?)";
            usrPs = c.prepareStatement(usrQry,Statement.RETURN_GENERATED_KEYS);
            usrPs.setString(1,u.getUsername());
            usrPs.setString(2,u.getPassword());
            usrPs.setInt   (3,u.getEmployee_id());
            usrExec = usrPs.executeUpdate();
            ResultSet rs = usrPs.getGeneratedKeys();
            while(rs.next()){
                u.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usrExec;
    }

    @Override
    public int login(User u) {
        int count = 0;
        DBConnect m = new DBConnect();
        Connection c;
        PreparedStatement loginPs;
        try {
            c = m.connect();
            String loginQry = "SELECT * FROM user WHERE username = ? and password = ?";
            loginPs = c.prepareStatement(loginQry);
            loginPs.setString(1, u.getUsername());
            loginPs.setString(2, u.getPassword());
            
            ResultSet rs = loginPs.executeQuery();
            while(rs.next()){
                count ++;
                u.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int getRole(User u)  {
        int jobId = 0;
        DBConnect m = new DBConnect();
        Connection c;
        PreparedStatement loginPs;
        try {
            c = m.connect();
            String loginQry = "select job_id from employee join user on user.employee_id = employee.id where user.id = ?";
            loginPs = c.prepareStatement(loginQry);
            loginPs.setInt(1, u.getId());
            ResultSet rs = loginPs.executeQuery();
            while(rs.next()){
                jobId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobId;
    }

    @Override
    public int findUser(User u) {
        int count = 0;
        DBConnect m = new DBConnect();
        Connection c;
        PreparedStatement loginPs;
        try {
            c = m.connect();
            String loginQry = "select * from user where username = ?";
            loginPs = c.prepareStatement(loginQry);
            loginPs.setString(1, u.getUsername());
            ResultSet rs = loginPs.executeQuery();
            while(rs.next()){
                count ++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int changePwd(User u) {
        int usrExec = 0;
        DBConnect m = new DBConnect();
        Connection c;
        PreparedStatement usrPs;
        try {
            c = m.connect();
            String usrQry = "update user set password = ? where username = ?";
            usrPs = c.prepareStatement(usrQry);
            usrPs.setString(1,u.getPassword());
            usrPs.setString(2,u.getUsername());
            usrExec = usrPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usrExec;
    }
    
}
