/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2.Entities;

import java.util.Date;
//import java.sql.Date;


/**
 *
 * @author LordGoats
 */
public class Employee {
    
        private int ID;
        private String Name;
        private String Email;
        private String Phone;
        private String Gender;
        private Date BDate;
        private int ManagerID;
        private int Team_ID;
        private int Job_ID;
       

    @Override
    public String toString() {
        return "Employee{" + "ID=" + ID + ", Name=" + Name + ", Email=" + Email + ", Phone=" + Phone + ", Gender=" + Gender + ", BDate=" + BDate + ", ManagerID=" + ManagerID + ", Team_ID=" + Team_ID + ", Job_ID=" + Job_ID + '}';
    }


    public Employee(int ID, String Name, String Email, String Phone, String Gender, Date BDate, int ManagerID, int Team_ID, int Job_ID) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
        this.BDate = BDate;
        this.ManagerID = ManagerID;
        this.Team_ID = Team_ID;
        this.Job_ID = Job_ID;
    }
    public Employee(int ID,String Name,String Email,String Phone)
    {this.ID=ID;
    this.Name=Name;
    this.Email=Email;
    this.Phone=Phone;
    }
    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public Date getBDate() {
        return BDate;
    }

    public void setBDate(Date BDate) {
        this.BDate = BDate;
    }

    public int getManagerID() {
        return ManagerID;
    }

    public void setManagerID(int ManagerID) {
        this.ManagerID = ManagerID;
    }

    public int getTeam_ID() {
        return Team_ID;
    }

    public void setTeam_ID(int Team_ID) {
        this.Team_ID = Team_ID;
    }

    public int getJob_ID() {
        return Job_ID;
    }

    public void setJob_ID(int Job_ID) {
        this.Job_ID = Job_ID;
    }

    /**
     * Get the value of ID
     *
     * @return the value of ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the value of ID
     *
     * @param ID new value of ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

 

   
    

}
