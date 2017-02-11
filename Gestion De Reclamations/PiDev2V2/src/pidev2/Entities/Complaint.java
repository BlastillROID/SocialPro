/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2.Entities;

import java.time.LocalDate;

/**
 *
 * @author Nizar
 */
public class Complaint {
     private int Id;
        private String Title;
        private String Description;
        private int Employee_id;
        private LocalDate Date_complaint;
        private String Status;

    public Complaint(String Title, String Description, int Employee_id,LocalDate Date_complaint,String Status) {
        this.Title = Title;
        this.Description = Description;
        this.Employee_id = Employee_id;
        this.Date_complaint=Date_complaint;
        this.Status=Status;
    }
      
     public Complaint(int Id,String Title, String Description, int Employee_id,LocalDate Date_complaint,String Status) {
        this.Id=Id;
         this.Title = Title;
        this.Description = Description;
        this.Employee_id = Employee_id;
        this.Date_complaint=Date_complaint;
        this.Status=Status;
    } 
     public Complaint(int Employee_id,String Title, String Description) {
        this.Employee_id = Employee_id;
        this.Title = Title;
        this.Description = Description;
    
    } 

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public LocalDate getDate_complaint() {
        return Date_complaint;
    }

    public void setDate_complaint(LocalDate Date_complaint) {
        this.Date_complaint = Date_complaint;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Complaint{" + "Id=" + Id + ", Title=" + Title + ", Description=" + Description + ", Employee_id=" + Employee_id + ", Date_complaint=" + Date_complaint + ", Status=" + Status + '}';
    }

  
     
     
    
}
