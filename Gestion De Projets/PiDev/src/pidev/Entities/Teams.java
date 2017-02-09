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
public class Teams {
    private int Team_ID;
    private String Team_Name;
    private int Leader_ID;
    private int TimeLine_ID;

    @Override
    public String toString() {
        return "Teams{" + "Team_ID=" + Team_ID + ", Team_Name=" + Team_Name + ", Leader_ID=" + Leader_ID + ", TimeLine_ID=" + TimeLine_ID + '}';
    }

    public int getTeam_ID() {
        return Team_ID;
    }

    public void setTeam_ID(int Team_ID) {
        this.Team_ID = Team_ID;
    }

    public String getTeam_Name() {
        return Team_Name;
    }

    public void setTeam_Name(String Team_Name) {
        this.Team_Name = Team_Name;
    }

    public int getLeader_ID() {
        return Leader_ID;
    }

    public void setLeader_ID(int Leader_ID) {
        this.Leader_ID = Leader_ID;
    }

    public int getTimeLine_ID() {
        return TimeLine_ID;
    }

    public void setTimeLine_ID(int TimeLine_ID) {
        this.TimeLine_ID = TimeLine_ID;
    }

    public Teams(int Team_ID, String Team_Name, int Leader_ID, int TimeLine_ID) {
        this.Team_ID = Team_ID;
        this.Team_Name = Team_Name;
        this.Leader_ID = Leader_ID;
        this.TimeLine_ID = TimeLine_ID;
    }
    
}
