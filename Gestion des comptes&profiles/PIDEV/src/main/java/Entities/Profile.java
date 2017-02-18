/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author salah
 */
public class Profile {
    private int id;
    private int userId;
    private String profilePicURL;
    private String timelinePicURL;

    public Profile() {
    }

    public Profile(int userId, String profilePicURL, String timelinePicURL) {
        this.userId = userId;
        this.profilePicURL = profilePicURL;
        this.timelinePicURL = timelinePicURL;
    }
    
    public Profile(int id, int userId, String profilePicURL, String timelinePicURL) {
        this.id = id;
        this.userId = userId;
        this.profilePicURL = profilePicURL;
        this.timelinePicURL = timelinePicURL;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public String getTimelinePicURL() {
        return timelinePicURL;
    }

    public void setTimelinePicURL(String timelinePicURL) {
        this.timelinePicURL = timelinePicURL;
    }
}
