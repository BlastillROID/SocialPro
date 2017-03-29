/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.Connection.MyConnection;
import pidev.Entities.Post;
import pidev.Entities.User;
import pidev.Interfaces.IPost;

/**
 *
 * @author dell
 */
public class PostService implements IPost {

    private PreparedStatement pst;
    private ResultSet rs;
    MyConnection m = new MyConnection();
    Connection c;

    @Override
    public void ajouterPost(Post p) {
        String requete = "insert into posts (timeline_id, user_id, post_content, post_media, post_date,post_time,post_signal) values (?,?,?,?,?,?,?)";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, p.getTimeline_id());
            pst.setInt(2, p.getUser_id());
            pst.setString(3, p.getContent());
            pst.setString(4, p.getMedia());
            pst.setString(5, p.getDate());
            pst.setString(6, p.getTime());
            pst.setInt(7, p.getNbr_signal());
            pst.executeUpdate();
            System.out.println("Post ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierPost(Post p) {
        String requete = "update posts set timeline_id=?, user_id=?, post_content=?, post_media=?, post_time=? where (posts.post_id =?)";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, p.getTimeline_id());
            pst.setInt(2, p.getUser_id());
            pst.setString(3, p.getContent());
            pst.setString(4, p.getMedia());
            pst.setString(5, p.getTime());
            pst.setInt(6, p.getId());
            pst.setInt(7, p.getNbr_signal());
            pst.executeUpdate();
            System.out.println("Post modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerPost(Post p) {
        String requete = "delete from posts where (posts.post_id = ?)";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Post supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResultSet afficherPost(Post p) {
        String requete = "select * from posts where (posts.user_id= ?) ";
        try {
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, p.getUser_id());
            rs = pst.executeQuery();
            Post pp;
            while (rs.next()) {
                pp = new Post(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8));
                System.out.println(+pp.getId() + "," + pp.getTimeline_id() + "," +pp.getUser_id()+"," + pp.getContent() + ","+pp.getMedia()+","+pp.getDate()+"," + pp.getTime()+","+pp.getNbr_signal());
            }
            System.out.println("Affichage terminé !");
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet showPost() {
        try {
            String requete = "select * from posts";
            c = m.Connect();
            pst = c.prepareStatement(requete);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet showSpecificPost(String id) {
        try {
            String requete = "SELECT * FROM posts WHERE timeline_id=?";
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet showSpecificPosts(String id) {
        User u;
        int var = 3;
        try {
            String requete = "SELECT * FROM user WHERE id=?";
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setString(1, id);
            ResultSet rs2 = pst.executeQuery();
            while (rs2.next()) {
                u = new User(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getInt(4), rs2.getInt(5));
                System.out.println(u.toString());
                var = u.getTimeline_id();
                System.out.println(var);
            }
            
            String requete2 = "SELECT * FROM posts WHERE timeline_id=?";
            c = m.Connect();
            pst = c.prepareStatement(requete2);
            pst.setInt(1, var);
            rs = pst.executeQuery();
            while (rs.next()) {
                Post post = new Post(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)) ;
                System.out.println(post.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public void filtrer() {
        try {
            String requete = "DELETE FROM posts WHERE post_signal > 5";
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Publications filtrées");

        } catch (SQLException ex) {
            Logger.getLogger(PostService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean signaler(int Id) {
        int sg=0;
        try {
            String requete = "SELECT * FROM posts WHERE post_id=?";
            c = m.Connect();
            pst = c.prepareStatement(requete);
            pst.setInt(1, Id);
            rs = pst.executeQuery();
             while (rs.next()) {
                 sg = rs.getInt(1);
             }
            sg++;
            
            String requete2 = "UPDATE posts SET post_signal=?";
            pst = c.prepareStatement(requete2);
            pst.setInt(1, sg);
            pst.executeUpdate();
            System.out.println("Publication signalée");
            return true ;
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
