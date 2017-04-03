/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;


import java.sql.Date;
import pidev.Services.PostService;

/**
 *
 * @author dell
 */
public class Post {
    private int id;
    private int timeline_id;
    private int user_id;
    private String content;
    private String media;
    private String date ;
    private String time;
    private int nbr_signal;

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", timeline_id=" + timeline_id + ", user_id=" + user_id + ", content=" + content + ", media=" + media + ", date=" + date + ", time=" + time + ", nbr_signal=" + nbr_signal + '}';
    }

    public Post(int id, int timeline_id, int user_id, String content, String media, String date, String time, int nbr_signal) {
        this.id = id;
        this.timeline_id = timeline_id;
        this.user_id = user_id;
        this.content = content;
        this.media = media;
        this.date = date;
        this.time = time;
        this.nbr_signal = nbr_signal;
    }
    
    public Post() {
    }

    public Post(int timeline_id, int user_id, String content, String date) {
        this.timeline_id = timeline_id;
        this.user_id = user_id;
        this.content = content;
        this.date = date;
    }

    public Post(int timeline_id, int user_id, String content, String media, String date, String time, int nbr_signal) {
        this.timeline_id = timeline_id;
        this.user_id = user_id;
        this.content = content;
        this.media = media;
        this.date = date;
        this.time = time;
        this.nbr_signal = nbr_signal;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }    

    public String getTime() {
        return time;
    }
    
    public int getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(int timeline_id) {
        this.timeline_id = timeline_id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getMedia() {
        return media;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNbr_signal() {
        return nbr_signal;
    }

    public void setNbr_signal(int nbr_signal) {
        this.nbr_signal = nbr_signal;
    }
}