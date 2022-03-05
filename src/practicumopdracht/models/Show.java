package practicumopdracht.models;

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String parsedDate = formatter.format(date);

        return(String.format("Added a new show with the values:\n" +
                "Name: %s" + "\n" +
                "Location: %s" + "\n" +
                "Date: %s" + "\n" +
                "Kids friendly: %b", name, location, parsedDate, kidsFriendly));
    }
}
