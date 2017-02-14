/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_InterfaceServices;

import PIDev_Entities.Type_Evenement;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IType_Evenement_Services {
    public void Ajout(Type_Evenement TE);
      public List<String> getEventType();
          public int getEventTypeId(String nom);
    public void Modif(Type_Evenement TE);
    public void Suppression(Type_Evenement TE);
    public List<Type_Evenement> Affichage();
}
