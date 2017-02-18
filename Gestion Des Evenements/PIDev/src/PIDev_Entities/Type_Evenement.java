/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_Entities;

/**
 *
 * @author HP
 */
public class Type_Evenement {
    private int  id_type;
    private String name;

    public Type_Evenement() {
    }
 public Type_Evenement( String name) {
        this.name = name;
    }

    public Type_Evenement(int id_type) {
        this.id_type = id_type;
    }
 
    public Type_Evenement(int id_type, String name) {
        this.id_type = id_type;
        this.name = name;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type_Evenement{" + "id_type=" + id_type + ", name=" + name + '}';
    }
}
