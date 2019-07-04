/**
 * ConferenceCentre.java
 *
 * Assignment 4 - Room Reservation in Conference Centre
 *
 * Class ConferenceCentre
 */

import java.util.ArrayList;

public class ConferenceCenter {
    private String name;
    private ArrayList<Room> rooms;

    public ConferenceCenter(String name) {
        this.name = name;
        this.rooms = new ArrayList<Room>();
    }

    // Constructor for finished ArrayList<Room> with rooms.
    public ConferenceCenter(String name, ArrayList<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public int getNumOfRooms() {
        return rooms.size();
    }

    public Room getRoomByIndex(int index) {
        if (index >= rooms.size() || index < 0) return null;
        return rooms.get(index);
    }

    public Room getRoomByRoomNumber(int roomNumber) {
        if (rooms.size() == 0) return null;
        for (Room r : rooms) {
            if (roomNumber == r.getRoomNumber()) return r;
        }
        return null;
    }

    public boolean bookRoom(long startTime, long endTime, int size, String name, String phoneNumber) {
        if (name == null || phoneNumber == null || name.trim().isEmpty() || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Name or phone number cannot be null or empty.");
        }

        for (Room r : rooms) {
            if (r.getSize() == size) {
                if (r.makeReservation(startTime, endTime, name, phoneNumber)) return true;
            }
        }
        return false;
    }

    public boolean addRoom(int roomNumber, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Room size cannot be less than or equal to 0");
        }

        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number cannot be less than or equal to 0");
        }

        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) return false;
        }

        Room newRoom = new Room(roomNumber, size);
        rooms.add(newRoom);
        return true;
    }

    @Override
    public String toString() {
        String result = getName();
        for (Room r : rooms) {
            result += "\n" + r + "\n";
        }
        return result;
    }
}
