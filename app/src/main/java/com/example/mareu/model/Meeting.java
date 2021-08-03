package com.example.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/07/2021
 */
public class Meeting implements Parcelable {

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
    public Meeting(long id, String sujetreu, String date, String location, String users){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected Meeting(Parcel in) {
        id = in.readLong();
        sujetreu = in.readString();
        date = in.readString();
        location = in.readString();
        users = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(sujetreu);
        dest.writeString(date);
        dest.writeString(location);
        dest.writeString(users);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Meeting> CREATOR = new Parcelable.Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };
}