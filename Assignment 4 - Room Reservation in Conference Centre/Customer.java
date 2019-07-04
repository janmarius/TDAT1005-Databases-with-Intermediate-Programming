/**
 * Customer.java
 *
 * Assignment 4 - Room Reservation in Conference Centre
 *
 * Class Customer
 */

public class Customer {
    private final String name;
    private final String phoneNumber;

    public Customer(String name, String phoneNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must be provided.");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number must be provided.");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name + ", phone number " + phoneNumber;
    }
}
