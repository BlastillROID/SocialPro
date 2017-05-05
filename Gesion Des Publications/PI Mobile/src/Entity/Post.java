/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.ui.Image;
import java.util.Date;

/**
 *
 * @author bayrem
 */
public class Post {
    
    private int id;
    private String content;
    private String media ;
    private String time ;
    private int signal ;
    private int note ;

    public Post() {
    }

    public Post(int id, String content, String media, String time, int signal) {
        this.id = id;
        this.content = content;
        this.media = media;
        this.time = time;
        this.signal = signal;
    }

    public Post(int id, String content, String time, int signal) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.signal = signal;
    }

    public Post(String content, String time, int signal) {
        this.content = content;
        this.time = time;
        this.signal = signal;
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(String content, String media, String time, int signal) {
        this.content = content;
        this.media = media;
        this.time = time;
        this.signal = signal;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setSignal(int signal) {
        this.signal = signal;
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

    public String getTime() {
        return time;
    }

    public int getSignal() {
        return signal;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", content=" + content + ", media=" + media + ", time=" + time + ", signal=" + signal + '}';
    }
    
    
    
    
}
