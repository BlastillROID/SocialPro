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
public class Session {
    static int Project_ID;
    static int SubProject_ID;

    public static int getProject_ID() {
        return Project_ID;
    }

    public static void setProject_ID(int Project_ID) {
        Session.Project_ID = Project_ID;
    }

    public static int getSubProject_ID() {
        return SubProject_ID;
    }

    public static void setSubProject_ID(int SubProject_ID) {
        Session.SubProject_ID = SubProject_ID;
    }
}
