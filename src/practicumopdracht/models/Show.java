package practicumopdracht.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This model defines what a show looks like and what its data types are
 *
 * @author Frank van der Velde
 */
public class Show {
    String name;
    String location;
    LocalDate date;
    boolean kidsFriendly;

    public Show(String name, String location, LocalDate date, boolean kidsFriendly) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.kidsFriendly = kidsFriendly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getIsKidsFriendly() {
        return kidsFriendly;
    }

    public void setKidsFriendly(boolean kidsFriendly) {
        this.kidsFriendly = kidsFriendly;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String parsedDate = formatter.format(date);
        return (String.format(
                "Name: %s" + "\n" +
                        "Location: %s" + "\n" +
                        "Date: %s" + "\n" +
                        "Kids friendly: %b", name, location, parsedDate, kidsFriendly));
    }
}
