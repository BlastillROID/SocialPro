/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.InterfaceService;

import java.util.List;
import pidev.Entities.Teams;

/**
 *
 * @author LordGoats
 */
public interface ITeam_Services {
    public void Ajout(Teams O);
    public void Modif(Teams O);
    public void Suppression(Teams O);
    public List<Teams> Affichage();
    
}
