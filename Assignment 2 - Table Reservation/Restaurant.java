

import java.time.LocalDate;

public class Restaurant {
    private String name;
    private int yearOfEstablishment;
    private TableHandler tables;
    private int numOfTables;

    public Restaurant(String name, int yearOfEstablishment, int numOfTables) {
        this.name = name;
        this.yearOfEstablishment = yearOfEstablishment;
        this.numOfTables = numOfTables;
        tables = new TableHandler(numOfTables);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public int getRestaurantAge() {
        return LocalDate.now().getYear() - yearOfEstablishment;
    }

    public int getNumOfTables() {
        return numOfTables;
    }

    public int getNumOfAvailable() {
        return tables.getNumOfAvailable();
    }

    public int getNumOfOccupied() {
        return tables.getNumOfOccupied();
    }

    public boolean makeTablesAvailable(int[] tableNumbers) {
        // Check if table numbers is valid.
        for(int i = 0; i < tableNumbers.length; i++) {
            if(tableNumbers[i] < 0 || tableNumbers[i] >= numOfTables) {
                return false;
            }
        }

        for(int i = 0; i < tableNumbers.length; i++) {
            tables.makeTableAvailable(tableNumbers[i]);
        }
        return true;
    }

    public boolean makeReservation(String name, int numOfTables) {
        return tables.makeReservation(name, numOfTables);
    }

    public String getReservationList() {
        return tables.toString();
    }

    public int[] getTableNumber(String name) {
        return tables.getTableNumber(name);
    }
}
