/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Employee;
import Entities.Mail;
import java.sql.ResultSet;

/**
 *
 * @author salah
 */
public interface IMailService {
    public boolean envoyerMail(Mail m, Employee e);
    public ResultSet recevoirMail();
    
}
