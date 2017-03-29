/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Interfaces;

import java.sql.ResultSet;
import pidev.Entities.Post;
import pidev.Entities.Timeline;

/**
 *
 * @author dell
 */
public interface ITimeline {
    public void ajouterTimeline(Timeline t);
    public void modifierTimeline(Timeline t);
    public void supprimerTimeline(Timeline t);
    public void afficherTimeline(Timeline t);
    public ResultSet showTimeline(); 
    
}
