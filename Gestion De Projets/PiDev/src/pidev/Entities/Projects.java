/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;

/**
 *
 * @author LordGoats
 */
public class Projects {
    private int Project_ID;
    private String Project_Name;
    private String Project_Desc;
    private int Manager_ID;

    public Projects() {

    }

    public int getProject_ID() {
        return Project_ID;
    }

    public void setProject_ID(int Project_ID) {
        this.Project_ID = Project_ID;
    }

    public String getProject_Name() {
        return Project_Name;
    }

    public void setProject_Name(String Project_Name) {
        this.Project_Name = Project_Name;
    }

    public String getProject_Desc() {
        return Project_Desc;
    }

    public void setProject_Desc(String Project_Desc) {
        this.Project_Desc = Project_Desc;
    }

    public int getManager_ID() {
        return Manager_ID;
    }

    public void setManager_ID(int Manager_ID) {
        this.Manager_ID = Manager_ID;
    }

    public Projects(int Project_ID ,String Project_Name, String Project_Desc, int Manager_ID) {
        this.Project_ID = Project_ID;
        this.Project_Name = Project_Name;
        this.Project_Desc = Project_Desc;
        this.Manager_ID = Manager_ID;
    } 
    public Projects(String Project_Name, String Project_Desc, int Manager_ID) {
        
        this.Project_Name = Project_Name;
        this.Project_Desc = Project_Desc;
        this.Manager_ID = Manager_ID;
    }

    @Override
    public String toString() {
        return "Projects{" + "Project_ID=" + Project_ID + ", Project_Name=" + Project_Name + ", Project_Desc=" + Project_Desc + ", Manager_ID=" + Manager_ID + '}';
    }
    
    
}
