/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.InterfaceService;

import java.util.List;
import pidev.Entities.Projects;

/**
 *
 * @author LordGoats
 */
public interface IProjects_Sevices {
    public void Ajout(Projects O);
    public void Modif(Projects O);
    public void Suppression(Projects O);
    public List<Projects> Affichage();
    
}
