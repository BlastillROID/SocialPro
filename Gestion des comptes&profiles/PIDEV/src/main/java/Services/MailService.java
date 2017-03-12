/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.DBConnect;
import Entities.Employee;
import Entities.Mail;
import Interfaces.IMailService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salah
 */
public class MailService implements IMailService{

    @Override
    public boolean envoyerMail(Mail m, Employee e) {
        try {
            int mailId = 0;
            String SQL = "INSERT INTO mails(content, subject) VALUES (?, ?)";
            DBConnect c = new DBConnect();
            Connection driver = c.connect();
            PreparedStatement ps = driver.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getSubject());
            ps.setString(2, m.getContent());
            if(ps.executeUpdate() == 1){
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next()){
                    mailId = rs.getInt(1);
                }
                System.out.println(mailId);
                SQL = "INSERT INTO mail_list(mail_id, to_id, from_id) VALUES (?, ?, ?)";
                ps = driver.prepareStatement(SQL);
                ps.setInt(1, mailId);
                ps.setInt(2, e.getId());
                ps.setInt(3, Session.getCurrentSession());
                System.out.println(ps);
                return (ps.executeUpdate() == 1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ResultSet recevoirMail() {
        ResultSet rs = null;
        /**/
        try {
            String sql = "select mails.content,mails.subject,employee.name,profile.profile_picture "
                        + "from mails join mail_list on mail_list.mail_id = mails.mail_id "
                        + "join employee on employee.id = mail_list.from_id "
                        + "join user on user.employee_id = employee.id "
                        + "join profile on user.id = profile.user_id where mail_list.to_id = ?";
            DBConnect c = new DBConnect();
            Connection driver = c.connect();
            PreparedStatement ps = driver.prepareStatement(sql);
            System.out.println("Session read from mail : "+Session.getCurrentSession());
            ps.setInt(1, Session.getCurrentSession());
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
