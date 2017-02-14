/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_InterfaceServices;

import PIDev_Entities.Evenement;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public interface IEvenement_Services {
    public void Ajout(Evenement E);
    public void Modif(Evenement E,int id);
    public void Suppression(int id);
    public List<Evenement> Affichage();
    public ObservableList<Evenement> Affichage_TableView();
}
