/**
 * Room.java
 *
 * Assignment 4 - Room Reservation in Conference Centre
 *
 * Class Room
 */

import java.util.ArrayList;

public class Room {
    private final int roomNumber;
    private final int size; // Given in number of people.
    private ArrayList<Reservation> reservations;

    public Room(int roomNumber, int size) {
        this.roomNumber = roomNumber;
        this.size = size;
        reservations = new ArrayList<Reservation>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getSize() {
        return size;
    }

    public boolean makeReservation(long startTime, long endTime, String name, String phoneNumber) {
        if (name == null || phoneNumber == null || name.trim().isEmpty() || phoneNumber.trim().isEmpty()) return false;

        Time start = new Time(startTime);
        Time end = new Time(endTime);
        for (Reservation r : reservations) {
            if (r.overlap(start, end)) return false;
        }
        Customer customer = new Customer(name, phoneNumber);
        Reservation reservation = new Reservation(start, end, customer);
        reservations.add(reservation);
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Room)) return false;

        if (this == object) return true;

        Room other = (Room) object;
        return (roomNumber == other.getRoomNumber());
    }

    @Override
    public String toString() {
        String result = "Room number: " + roomNumber + "\n" +
                        "Room size: " + size;
        if (reservations.size() != 0) {
            for (Reservation r : reservations) {
                result += "\n" + r;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Room room = new Room(1, 4);

        if (room.makeReservation(200302011000L, 200302011100L, "Ole Olsen", "12345678") &&
            room.makeReservation(200302021000L, 200302021100L, "Ole Olsen", "12345678") &&
            !room.makeReservation(200302011030L, 200302011100L, "Ole Olsen", "12345678") &&
            !room.makeReservation(200302010930L, 200302011030L, "Ole Olsen", "12345678")) {
            System.out.println("Test 1 - Successful!");
        }
    }
}
