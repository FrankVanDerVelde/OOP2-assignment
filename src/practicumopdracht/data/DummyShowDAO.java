package practicumopdracht.data;

import practicumopdracht.models.Show;

import java.time.LocalDate;

/**
 *  The DAO for dummy show data
 *
 * @ Author Frank van der Velde
 */
public class DummyShowDAO extends ShowDAO {

    @Override
    public boolean save() {
        return super.save();
    }

    @Override
    public boolean load() {
        Show fempire = new Show("Fempire", "Amsterdam", LocalDate.parse("2022-05-17"), true);
        Show dragBrunch = new Show("Drag Brunch", "Amsterdam", LocalDate.parse("2022-07-24"), true);
        Show siblingRivalry = new Show("Sibling Rivalry", "New York", LocalDate.parse("2022-12-12"), false);

        addOrUpdate(fempire);
        addOrUpdate(dragBrunch);
        addOrUpdate(siblingRivalry);

        return true;
    }
}
