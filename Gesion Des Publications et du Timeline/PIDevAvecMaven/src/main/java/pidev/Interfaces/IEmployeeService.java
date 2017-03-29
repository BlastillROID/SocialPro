/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Interfaces;

import pidev.Entities.Employee;
import java.sql.ResultSet;

/**
 *
 * @author salah
 */
public interface IEmployeeService {
    public int ajouterEmployee(Employee e);
    public int modifierEmployee(Employee e);
    public void supprimerEmployee(int id);
    public ResultSet afficherEmployee(int sc, int ec);
    public String getJobTitle(int id);
    public String getName(int id);
    public int getIdByUser(String user);
    public String getPhoneByUser(String user);
}
