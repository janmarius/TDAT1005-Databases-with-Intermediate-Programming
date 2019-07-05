/**
 * Main.java
 *
 * Assignment 4 - Room Reservation in Conference Centre (Aggregation)
 *
 * System to manage room reservations in conference centre.
 */

import static javax.swing.JOptionPane.*;

public class Main {

    public static void main(String[] args) {
        ConferenceCenter cc = new ConferenceCenter("NYC Seminar and Conference Center");

        String[] options = {"Add New Room", "Book Room", "Detailed Overview of Rooms", "Find Room", "Exit"};

        final int ADD_NEW_ROOM = 0;
        final int BOOK_ROOM = 1;
        final int DETAILED_OVERVIEW = 2;
        final int FIND_ROOM = 3;
        final int EXIT = 4;

        int choice = showOptionDialog(null, "Welcome to " + cc.getName() + "!" +
                                        "\n Number of rooms: " + cc.getNumOfRooms(), cc.getName(),
                                        YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[0]);


        String regexNumOverZero = "[1-9]+[0-9]*";

        // Regex date and time. Format: yyyy-mm-dd HH:MM
        String regexTime = "[12][0-9]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]) ([0-1][0-9]|2[0-4]):([0-5][0-9])";

        // Regex norwegian phone number.
        String regexPhoneNumber = "[1-9][0-9]{7}";
        while(choice != EXIT) {
            switch (choice) {
                case ADD_NEW_ROOM:
                    // Ask client for room number. Only numbers over zero is valid.
                    String roomNumberRead = null;
                    do {
                        roomNumberRead = showInputDialog("Enter room number:");
                        if (roomNumberRead == null) break;
                        if (!roomNumberRead.matches(regexNumOverZero)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                                ERROR_MESSAGE);
                        }
                    } while (!roomNumberRead.matches(regexNumOverZero));
                    if (roomNumberRead == null) break;
                    int roomNumber = Integer.parseInt(roomNumberRead);


                    // Ask client for room size. Only size over zero is valid.
                    String sizeRead = null;
                    do {
                        sizeRead = showInputDialog("Enter room size:");
                        if (sizeRead == null) break;
                        if (!sizeRead.matches(regexNumOverZero)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while (!sizeRead.matches(regexNumOverZero));
                    if (sizeRead == null) break;
                    int size = Integer.parseInt(sizeRead);

                    if (cc.addRoom(roomNumber, size)) {
                        showMessageDialog(null, "Room nr. " + roomNumber + " is succeesfully added.");
                    } else {
                        showMessageDialog(null, "Something went wrong, room not added!", "Error",
                                ERROR_MESSAGE);
                    }
                    break;

                case BOOK_ROOM:
                    // Ask client for booking start time.
                    String startTimeRead = null;
                    do {
                        startTimeRead = showInputDialog("Start time (yyyy-mm-dd hh:mm):");
                        if (startTimeRead == null) break;
                        if (!startTimeRead.matches(regexTime)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while(!startTimeRead.matches(regexTime));
                    if (startTimeRead == null) break;
                    long startTime = timeFormatToLong(startTimeRead);


                    // Ask client for booking end time.
                    String endTimeRead = null;
                    do {
                        endTimeRead = showInputDialog("End time (yyyy-mm-dd hh:mm):");
                        if (endTimeRead == null) break;
                        if (!endTimeRead.matches(regexTime)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while(!endTimeRead.matches(regexTime));
                    if (endTimeRead == null) break;
                    long endTime = timeFormatToLong(endTimeRead);

                    // Ask client for required room size.
                    String sizeRead2 = null;
                    do {
                        sizeRead2 = showInputDialog("Enter required room size:");
                        if (sizeRead2 == null) break;
                        if (!sizeRead2.matches(regexNumOverZero)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while(!sizeRead2.matches(regexNumOverZero));
                    if (sizeRead2 == null) break;
                    int size2 = Integer.parseInt(sizeRead2);

                    // Ask client for name.
                    String name = null;
                    do {
                        name = showInputDialog("Enter name");
                        if (name == null) break;
                        if (name.trim().isEmpty()) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while(name.trim().isEmpty());
                    if (name == null) break;

                    // Ask client for phone number.
                    String phoneNumber = null;
                    do {
                        phoneNumber = showInputDialog("Enter phone number:");
                        if (phoneNumber == null) break;
                        if (!phoneNumber.matches(regexPhoneNumber)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while (!phoneNumber.matches(regexPhoneNumber));
                    if (phoneNumber == null) break;

                    if (cc.bookRoom(startTime, endTime, size2, name, phoneNumber)) {
                        showMessageDialog(null, "You have successfully booked a room!");
                    } else {
                        showMessageDialog(null, "Something went wrong, booking not complete!", "Error",
                                ERROR_MESSAGE);
                    }
                    break;

                case DETAILED_OVERVIEW:
                    // Easier solution to the problem, but not what the exercise asked for.
                    /*
                    showMessageDialog(null, cc);
                     */

                    String result = cc.getName();
                    for (int i = 0; i < cc.getNumOfRooms(); i++) {
                        result += "\n" + cc.getRoomByIndex(i) + "\n";
                    }
                    showMessageDialog(null, result);
                    break;

                case FIND_ROOM:
                    // Ask client for which room number to find.
                    String findRoomNumberRead = null;
                    do {
                        findRoomNumberRead = showInputDialog("Enter the room number you want to find:");
                        if (findRoomNumberRead == null) break;
                        if (!findRoomNumberRead.matches(regexNumOverZero)) {
                            showMessageDialog(null, "Invalid input, please try again!", "Error",
                                    ERROR_MESSAGE);
                        }
                    } while (!findRoomNumberRead.matches(regexNumOverZero));
                    if (findRoomNumberRead == null) break;
                    int findRoomNumber = Integer.parseInt(findRoomNumberRead);

                    Room room = cc.getRoomByRoomNumber(findRoomNumber);
                    if (room != null) {
                        showMessageDialog(null, room);
                    } else {
                        showMessageDialog(null, "Something went wrong, please try again!", "Error",
                                ERROR_MESSAGE);
                    }
                    break;

                default:
                    break;
            }
            choice = showOptionDialog(null, "Welcome to " + cc.getName() + "!" +
                            "\n Number of rooms: " + cc.getNumOfRooms(), cc.getName(),
                    YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[0]);
        }
    }

    public static long timeFormatToLong(String time) {
        String temp = time.substring(0, 4); // year
        temp += time.substring(5, 7); // month
        temp += time.substring(8, 10); // day
        temp += time.substring(11,13); // hour
        temp += time.substring(14, 16); // minute
        long timeToLong = Long.parseLong(temp);
        return timeToLong;
    }
}
