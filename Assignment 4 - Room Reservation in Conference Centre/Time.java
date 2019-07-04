/**
 * Time.java
 *
 * Assignment 4 - Room Reservation in Conference Centre
 *
 * Class Time
 */

public class Time implements Comparable<Time> {
    private final long time; // format: yyyyMMddTTmm.

    public Time(long time) {
        this.time = time;
    }

    public String toString() {
        int date = (int) (time / 10_000);
        int hour = (int) (time % 10_000);
        int year = date / 10_000;
        int monthDay = date % 10_000;
        int month = monthDay / 100;
        int day = monthDay % 100;

        String time = "";
        if (day < 10) {
            time += "0";
        }
        time += day + "-";
        if (month < 10) {
            time += "0";
        }
        time += month + "-" + year + " ";
        if(hour < 1000) {
            time += "0";
        }
        time += hour;
        return time;
    }

    public int compareTo(Time other) {
        if (time < other.time) {
            return -1;
        } else if (time > other.time) {
            return 1;
        } else {
            return 0;
        }
    }
}
