/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;

import java.util.Date;

/**
 *
 * @author LordGoats
 */
public class Directeur extends  Employee{
    
    public Directeur(int ID, String Name, String Email, String Phone, String Gender, Date BDate, int ManagerID, int Team_ID, int Job_ID) {
        super(ID, Name, Email, Phone, Gender, BDate, ManagerID, Team_ID, Job_ID);
    }
    
    
}
