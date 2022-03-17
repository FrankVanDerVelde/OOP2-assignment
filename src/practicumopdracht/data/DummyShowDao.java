package practicumopdracht.data;

import practicumopdracht.models.Show;

import java.time.LocalDate;

public class DummyShowDao extends ShowDAO{
    @Override
    public boolean save() {
        return super.save();
    }

    @Override
    public boolean load() {
        Show fempire = new Show("Fempire", "Amsterdam", LocalDate.parse("2022-05-17"), true);
        Show dragBrunch = new Show("Drag Brunch", "Amsterdam", LocalDate.parse("2022-07-24"), true);
//        Show smokeAndMirrors = new Show("Smoke and Mirrors", "Berlijn", LocalDate.parse("2022-04-30"), true);
        Show siblingRivalry = new Show("Sibling Rivalry", "New York", LocalDate.parse("2022-12-12"), false);

        addOrUpdate(fempire);
        addOrUpdate(dragBrunch);
//        addOrUpdate(smokeAndMirrors);
        addOrUpdate(siblingRivalry);

        return true;
    }
}
