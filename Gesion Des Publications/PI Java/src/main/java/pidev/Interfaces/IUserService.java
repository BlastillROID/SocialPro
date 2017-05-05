/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Interfaces;

import pidev.Entities.User;

/**
 *
 * @author dell
 */
public interface IUserService {
    public int signUp(User u);
    public int login(User u);
    public int getRole(User u);
    public int findUser(User u);
    public int changePwd(User u);
    public int getID(int id);
}
