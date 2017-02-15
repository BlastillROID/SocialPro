/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Profile;
import java.sql.ResultSet;

/**
 *
 * @author salah
 */
public interface IProfileService {
    public void ajouterProfile(Profile p);
    public ResultSet afficherProfile(Profile p);
}
