/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Timeline {
    
    private int id ;
    private String description;
    //private ArrayList <Post> Posts;

    public Timeline() {
        //Posts = new ArrayList();
    }

    public Timeline(int id, String description) {
        this.id = id;
        this.description = description;
        //Posts = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   
   /* public ArrayList<Post> getPosts() {
        return Posts;
    } 
    
    public void setPosts(ArrayList<Post> Posts) {
        this.Posts = Posts;
    }*/
}
