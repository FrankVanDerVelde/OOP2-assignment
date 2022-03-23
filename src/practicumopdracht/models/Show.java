package practicumopdracht.models;

import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean getIsKidsFriendly() {
        return kidsFriendly;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setKidsFriendly(boolean kidsFriendly) {
        this.kidsFriendly = kidsFriendly;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String parsedDate = formatter.format(date);
//        "Added a new show with the values:\n" +
        return(String.format(
                "Name: %s" + "\n" +
                "Location: %s" + "\n" +
                "Date: %s" + "\n" +
                "Kids friendly: %b", name, location, parsedDate, kidsFriendly));
    }

}
