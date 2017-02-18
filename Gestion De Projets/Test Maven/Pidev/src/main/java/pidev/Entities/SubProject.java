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
public class SubProject {
    private int SubProject_ID;
    private int Project_ID;
    private String SubProject_Name;
    private int Team_ID;

    public SubProject(int SubProject_ID, int Project_ID, String SubProject_Name, int Team_ID) {
        this.SubProject_ID = SubProject_ID;
        this.Project_ID = Project_ID;
        this.SubProject_Name = SubProject_Name;
        this.Team_ID = Team_ID;
    }

    public int getSubProject_ID() {
        return SubProject_ID;
    }

    public void setSubProject_ID(int SubProject_ID) {
        this.SubProject_ID = SubProject_ID;
    }

    public int getProject_ID() {
        return Project_ID;
    }

    public void setProject_ID(int Project_ID) {
        this.Project_ID = Project_ID;
    }

    public String getSubProject_Name() {
        return SubProject_Name;
    }

    public void setSubProject_Name(String SubProject_Name) {
        this.SubProject_Name = SubProject_Name;
    }

    public int getTeam_ID() {
        return Team_ID;
    }

    public void setTeam_ID(int Team_ID) {
        this.Team_ID = Team_ID;
    }

    @Override
    public String toString() {
        return "SubProject{" + "SubProject_ID=" + SubProject_ID + ", Project_ID=" + Project_ID + ", SubProject_Name=" + SubProject_Name + ", Team_ID=" + Team_ID + '}';
    }
    
}
