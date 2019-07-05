/**
 * Reservation.java
 *
 * Assignment 4 - Room Reservation in Conference Centre (Aggregation)
 *
 * Class Reservation
 */

public class Reservation {
    private final Time startTime;
    private final Time endTime;
    private final Customer customer;


    public Reservation(Time start, Time end, Customer customer) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start time or endTime time is null.");
        }
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("Start time is equal to or after endTime time.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null.");
        }
        this.startTime = start;
        this.endTime = end;
        this.customer = customer;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public boolean overlap(Time startTime, Time endTime) {
        return (endTime.compareTo(this.startTime) > 0 && startTime.compareTo(this.endTime) < 0);
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + ", phone number: " + customer.getPhoneNumber() + ", startTime time: " +
                startTime.toString() + ", endTime time: " + endTime.toString();
    }
}
