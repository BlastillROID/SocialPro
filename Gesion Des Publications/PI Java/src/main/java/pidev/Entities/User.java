/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;

/**
 *
 * @author dell
 */
public class User {

    private int id;
    private String username;
    private String password;
    private int employee_id;
    private int timeline_id;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", employee_id=" + employee_id + ", timeline_id=" + timeline_id + '}';
    }

    public void setTimeline_id(int timeline_id) {
        this.timeline_id = timeline_id;
    }

    public int getTimeline_id() {
        return timeline_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String username, String password, int employee_id, int timeline_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employee_id = employee_id;
        this.timeline_id = timeline_id;
    }

    public User() {
    }
    
    
}
