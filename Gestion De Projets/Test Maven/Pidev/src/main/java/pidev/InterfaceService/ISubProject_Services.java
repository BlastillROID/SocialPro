/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.InterfaceService;

import java.util.List;
import pidev.Entities.SubProject;

/**
 *
 * @author LordGoats
 */
public interface ISubProject_Services {
    public void Ajout(SubProject O);
    public void Modif(SubProject O);
    public void Suppression(SubProject O);
    public List<SubProject> Affichage();
    
    
}
