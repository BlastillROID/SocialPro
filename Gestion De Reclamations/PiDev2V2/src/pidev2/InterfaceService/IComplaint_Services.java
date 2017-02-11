/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2.InterfaceService;
import java.util.List;
import javafx.scene.control.TableView;
import  pidev2.Entities.Complaint;
/**
 *
 * @author Nizar
 */
public interface IComplaint_Services {
   public void Ajout(Complaint C);
   public void Modifier(int id,String titre,String description);
   public void Supprimer(int id);
   public List<Complaint> Affichage();
   public  void chargeTableauDonnees(TableView tableProjet);
}

