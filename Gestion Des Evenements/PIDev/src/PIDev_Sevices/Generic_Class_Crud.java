/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_Sevices;

import PIDev_DataBase.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class Generic_Class_Crud {
    Connection connection;
    private PreparedStatement ps;
    private static ResultSet rs;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static void setRs(ResultSet rs) {
        Generic_Class_Crud.rs = rs;
    }
    public Generic_Class_Crud()
    {
        connection=DataSource.getinstance().getConnection();
    }
    
}
