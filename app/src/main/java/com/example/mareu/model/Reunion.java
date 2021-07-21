package com.example.mareu.model;

import java.util.Objects;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/07/2021
 */
public class Reunion {

    /** Identifier */
    private long id;

    /** sujetreu */
    private String sujetreu;

    /** date */
    private String date;

    /** location */
    private String location;

    /** Full users */
    private String users;

    /**
     * Constructor
     * @param id
     * @param sujetreu
     * @param date
     */
    public Reunion(long id, String sujetreu, String date, String location, String users){
        this.id = id;
        this.sujetreu = sujetreu;
        this.date = date;
        this.location = location;
        this.users = users;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSujetreu() {
        return sujetreu;
    }

    public void setSujetreu(String sujetreu) {
        this.sujetreu = sujetreu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String phoneNumber) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
