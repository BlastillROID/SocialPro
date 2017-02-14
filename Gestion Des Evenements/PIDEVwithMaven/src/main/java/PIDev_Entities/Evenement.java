/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_Entities;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Evenement {

    private int evenement_id;
    private String evenement_name;
    private int type_id;
    private Date date_evenement;
    private String address_evenement;
    private String description;
    private String media;
    private String state;
    private int employee_id;

    public String getEvenement_name() {
        return evenement_name;
    }

    public void setEvenement_name(String evenement_name) {
        this.evenement_name = evenement_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Evenement() {
    }

    public Evenement(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public Evenement(int evenement_id, String evenement_name, int type_id, Date date_evenement, String address_evenement, String description, String media, String state, int employee_id) {
        this.evenement_id = evenement_id;
        this.evenement_name = evenement_name;
        this.type_id = type_id;
        this.date_evenement = date_evenement;
        this.address_evenement = address_evenement;
        this.description = description;
        this.media = media;
        this.state = state;
        this.employee_id = employee_id;
    }

    public Evenement(int evenement_id, int type_id, Date date_evenement, String address_evenement, String description, String media, String state, int employee_id) {
        this.evenement_id = evenement_id;
        this.type_id = type_id;
        this.date_evenement = date_evenement;
        this.address_evenement = address_evenement;
        this.description = description;
        this.media = media;
        this.state = state;
        this.employee_id = employee_id;
    }

    public Evenement(String evenement_name, int type_id, Date date_evenement, String address_evenement, String description, String media, String state, int employee_id) {
        this.evenement_name = evenement_name;
        this.type_id = type_id;
        this.date_evenement = date_evenement;
        this.address_evenement = address_evenement;
        this.description = description;
        this.media = media;
        this.state = state;
        this.employee_id = employee_id;
    }

    public Evenement(String evenement_name, int type_id, Date date_evenement, String address_evenement, String description, String media) {
        this.evenement_name = evenement_name;
        this.type_id = type_id;
        this.date_evenement = date_evenement;
        this.address_evenement = address_evenement;
        this.description = description;
        this.media = media;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getAddress_evenement() {
        return address_evenement;
    }

    public void setAdress_evenement(String sadress_evenement) {
        this.address_evenement = sadress_evenement;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Evenement{" + "evenement_id=" + evenement_id + ", evenement_name=" + evenement_name + ", type_id=" + type_id + ", date_evenement=" + date_evenement + ", address_evenement=" + address_evenement + ", description=" + description + ", media=" + media + ", state=" + state + ", employee_id=" + employee_id + '}';
    }

}
