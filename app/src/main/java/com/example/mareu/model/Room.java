package com.example.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 18/08/2021
 */

public class Room {

    private String name;

    private Room(String roomName) {
        this.name = roomName;
    }

    private String getRoomName() {
        return name;
    }

    private static List<Room> ListRooms = Arrays.asList(
            new Room("Peach"),
            new Room("Mario"),
            new Room("Luigi"),
            new Room("Donkey"),
            new Room("Bowser"),
            new Room("Wario"),
            new Room("Toad"),
            new Room("Daisy"),
            new Room("Yoshi"),
            new Room("Bowser Jr.")
    );

    static public List<String> getRooms() {
        List<String> roomList = new ArrayList<>();

        for (Room room : ListRooms) {
            roomList.add(room.getRoomName());
        }
        return roomList;
    }
}
