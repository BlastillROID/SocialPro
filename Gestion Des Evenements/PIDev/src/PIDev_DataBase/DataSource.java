/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DataSource {
    private String url="jdbc:mysql://127.0.0.1:3306/pidev";
    private String login="root";
    private String pwd="";
    private Connection connection;
    private static DataSource Data;
    
    private DataSource()
    {
        try {
            connection=DriverManager.getConnection(url,login,pwd);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Connection getConnection() {
        return connection;
    }

   public static DataSource getinstance()
   {
       if (Data==null)
       {
           Data=new DataSource();
       }
       return Data;
   }
    
}
