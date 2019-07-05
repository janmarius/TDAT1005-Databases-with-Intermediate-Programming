/**
* Main.java
*
* Assignment 2 - Restaurant Reservation System (Repetition)
*
* System to make table reservations in restaurant.
*/

import static javax.swing.JOptionPane.*;

public class Main {

    public static void main(String[] args) {
        // Ask the client for restaurant name.
        String restaurantNameRead = showInputDialog("What is the name of the restaurant?");
        while (restaurantNameRead == null || restaurantNameRead.isEmpty()) {
            if(restaurantNameRead == null) System.exit(0);

            restaurantNameRead = showInputDialog("Something went wrong, please try again!" +
                                                    "\n\nWhat is the name of the restaurant?");
        }


        // Ask the client for year of establishment.
        String regexYear = "[0-9]{4}";
        String yearOfEstablishmentRead = showInputDialog("What is the year of establishment?");
        while (yearOfEstablishmentRead == null || !yearOfEstablishmentRead.matches(regexYear)) {
            if(yearOfEstablishmentRead == null) System.exit(0);

            yearOfEstablishmentRead = showInputDialog("Something went wrong, please try again!" +
                                                        "\n\nWhat is the year of establishment?");
        }
        int yearOfEstablishment = Integer.parseInt(yearOfEstablishmentRead);


        // Ask the client for number of tables in the restaurant.
        String regexNumOfTables = "^[1-9]+[0-9]*"; // Regex to avoid 0.
        String numOfTablesRead = showInputDialog("How many tables are in the restaurant?");
        while (numOfTablesRead == null || !numOfTablesRead.matches(regexNumOfTables)) {
            if(numOfTablesRead == null) System.exit(0);

            numOfTablesRead = showInputDialog("Something went wrong, please try again!" +
                                                "\n\nHow many tables are in the restaurant?");
        }
        int numOfTables = Integer.parseInt(numOfTablesRead);


        Restaurant restaurant = new Restaurant(restaurantNameRead, yearOfEstablishment, numOfTables);
        String[] options = {"About the restaurant",
                            "Give the restaurant a new name",
                            "Restaurant status",
                            "Make a reservation",
                            "Find reserved tables",
                            "Make tables available",
                            "Exit"};

        final int ABOUT = 0;
        final int NEW_NAME = 1;
        final int RESTAURANT_STATUS = 2;
        final int MAKE_A_RESERVATION = 3;
        final int FIND_RESERVED_TABLES = 4;
        final int MAKE_TABLES_AVAILABLE = 5;
        final int EXIT = 6;


        int choice = showOptionDialog(null, "Choose", restaurant.getName(), YES_NO_OPTION,
                                        INFORMATION_MESSAGE, null, options, options[0]);

        while(choice != EXIT) {
            switch (choice) {
                case ABOUT:
                    showMessageDialog(null, "Restaurant name: " + restaurant.getName() + "\n" +
                                        "Year of establishment: " + restaurant.getYearOfEstablishment() + "\n" +
                                        "Restaurant age: " + restaurant.getRestaurantAge() + "\n" +
                                        "Number of tables: " + restaurant.getNumOfTables());
                    break;

                case NEW_NAME:
                    String newName = showInputDialog("Enter the new name of the restaurant:");
                    if(newName != null && !newName.equals("")) {
                        restaurant.setName(newName);
                    }
                    break;

                case RESTAURANT_STATUS:
                    showMessageDialog(null,
                                "Number of reserved tables: " + restaurant.getNumOfOccupied() +
                                        "\nNumber of available tables: " + restaurant.getNumOfAvailable() +
                                        "\n\n" + restaurant.getReservationList());
                    break;

                case MAKE_A_RESERVATION:
                    String reservationNameRead = showInputDialog("Which name do you want to registrate\n" +
                                                                "the reservation on?");
                    if(reservationNameRead == null) break;

                    String reservationNumTablRead = showInputDialog("How many tables do want to reserve?" +
                                                                    "\nNumber of available tables right now: " +
                                                                    restaurant.getNumOfAvailable());
                    if(reservationNumTablRead == null) break;
                    if(!reservationNumTablRead.matches(regexNumOfTables)) {
                        showMessageDialog(null, "You have to reserve at least one table, " +
                                                        "please try again!");
                        break;

                    }
                    int reservationNumTabl = Integer.parseInt(reservationNumTablRead);
                    if(restaurant.makeReservation(reservationNameRead, reservationNumTabl)) {
                        showMessageDialog(null, "The reservation was successful!");
                    } else {
                        showMessageDialog(null, "Something went wrong during the registration," +
                                            "please try again!");
                    }
                    break;

                case FIND_RESERVED_TABLES:
                    String reservationName = showInputDialog("Which name do you want to find tables for?");
                    int[] reservedTables = restaurant.getTableNumber(reservationName);
                    String result = "";
                    if(reservedTables[0] == -1) {
                        showMessageDialog(null, "No tables found on " + reservationName);
                        break;
                    }
                    for(int i = 0; i < reservedTables.length; i++) {
                        result += "TableHandler nr: " + reservedTables[i] + "\n";
                    }
                    showMessageDialog(null, result);
                    break;

                case MAKE_TABLES_AVAILABLE:
                    String regexNumber = "[0-9]+";
                    String numToMakeAvailableRead = showInputDialog("How many tables do you want to make available?");
                    if(numToMakeAvailableRead == null) break;
                    boolean everythingOk = true;

                    if(numToMakeAvailableRead.matches(regexNumOfTables)) {
                        int numToMakeAvailable = Integer.parseInt(numToMakeAvailableRead);
                        int[] temp = new int[numToMakeAvailable];

                        for(int i = 0; i < temp.length; i++) {
                            String tableNumberRead = showInputDialog("Enter table number:");
                            if(tableNumberRead == null || !tableNumberRead.matches(regexNumber)) {
                                everythingOk = false;
                                break;
                            }
                            int tableNumber = Integer.parseInt(tableNumberRead);
                            temp[i] = tableNumber;
                        }
                        if(everythingOk && restaurant.makeTablesAvailable(temp)) {
                            showMessageDialog(null, "All tables are now available!");
                        } else {
                            showMessageDialog(null, "Something went wrong, please try again!");
                        }

                    } else {
                        showMessageDialog(null, "Something went wrong, please try again!");
                    }
                    break;

                default:
                    break;
            }

            choice = showOptionDialog(null, "Choose", restaurant.getName(), YES_NO_OPTION,
                                        INFORMATION_MESSAGE, null, options, options[0]);
        }
    }
}