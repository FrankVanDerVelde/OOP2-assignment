package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.util.List;

public class DummyDragQueenDAO extends DragQueenDAO {
    @Override
    public boolean save() {
        return super.save();
    }

    @Override
    public boolean load() {
        List<Show> shows = MainApplication.getShowDAO().getAll();

        DragQueen angeria = new DragQueen(
                shows.get(1),
                "Angeria Paris VanMicheals",
                "Angie",
                28,
                "Male",
                "Atlanta, GA, USA",
                100.10,
                "This southern belle from ATL will light up any room with her charisma, uniqueness, nerve and talent. An old-school pageant queen, Angie is here to serve fierce fashion and face with a country charm you can't help but fall in love with."
        );

        DragQueen bob = new DragQueen(
                shows.get(2),
                "Bob the Drag Queen",
                "Caldwell Tidicue",
                35,
                "Male",
                "Columbus, GA, USA",
                200.10,
                "This guy came on stage and didn't hear my name properly, so he started to make up all different names. He asked me if it was Kate. I said, “Yeah, Kate is a drag name.” Then I started to just make it more simple, and decided Bob…Bob the Drag Queen…and I thought it was funny, and then I created my name."
        );

        DragQueen monet = new DragQueen(
                shows.get(2),
                "Monét X Change",
                "Kevin Bertin",
                32,
                "Male",
                "Brooklyn, New York City, NY, USA",
                190.00,
                "In her Season 10 Meet the Queens interview, Monét explains that the idea for her drag name came to her when she spotted a currency exchange booth across the street while eating chicken wings. Her first name is a substitution of the word money with the name Monét (stylised with an accent over the E), while her last name X Change is played on the word exchange."
        );

        DragQueen kylie = new DragQueen(
                shows.get(0),
                "Kylie Sonique Love",
                "Kylie Sonique Love",
                38,
                "Female",
                "Albany, GA, USA",
                150.00,
                "Sonique's drag name comes from the same-named British artist. She likes it because it sounds powerful (like sonic powers) and it's a one-word name, just as she wanted. She decided to go by her real full name, Kylie Sonique Love on All Stars 6, saying you only do additions, never subtractions"
        );

        DragQueen patty = new DragQueen(
                shows.get(0),
                "Patty Pam-Pam",
                "Pieter Roberts",
                35,
                "Male",
                "Amstenrade, Limburg, Netherlands",
                90.00,
                "When she did drag for the first time, a friend said she 'looked like a Patty'. Though she thought it was a horrid name, she decided to go with it. Her last name comes from her initials, P.A.M., she didn't think the surname 'Pam' was any good, so she decided to double it."
        );

        addOrUpdate(angeria);
        addOrUpdate(bob);
        addOrUpdate(monet);
        addOrUpdate(kylie);
        addOrUpdate(patty);

        return true;
    }
}
