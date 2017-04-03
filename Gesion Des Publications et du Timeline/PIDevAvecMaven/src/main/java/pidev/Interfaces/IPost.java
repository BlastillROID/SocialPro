/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Interfaces;

import java.sql.ResultSet;
import pidev.Entities.Post;

/**
 *
 * @author dell
 */
public interface IPost {
    public void ajouterPost(Post p);
    public void modifierPost(Post p );
    public void supprimerPost(Post p);
    public ResultSet afficherPost(Post p);
    public void filtrer();
    public boolean signaler(int Id);
    public ResultSet showPost(); 
    public ResultSet showSpecificPost(String id);
    public ResultSet showSpecificPosts(String id);
    
}
