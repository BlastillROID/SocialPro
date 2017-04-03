/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class MyConnection {
    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/PiDev";
    private static final String login = "root";
    private static final String pwd = "";
    private static final String MAX_POOL = "250";
    
    // init connection object
    private static Connection con;

    public Connection Connect(){
        if(con == null){
            try {
                con = DriverManager.getConnection(url, login, pwd);
                System.out.println("Connexion etablie avec succes ");
            } catch (SQLException ex) {
                Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
