/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;
/**
 *
 * @author dell
 */
public class Session {

    private static int idUser;
    private static String pin = "";

    public static void start(int currentUserID) {
        idUser = currentUserID;
    }

    public static int getCurrentSession() throws Exception {
        if (idUser != -1) {
            return idUser;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static void generatePIN() {
        int randomPIN = (int) (Math.random() * 9000) + 1000;
        pin = "" + randomPIN;
    }

    public static String getPin() {
        return pin;
    }

    public static int getIdUser() {
        return idUser;
    }

}