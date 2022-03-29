package practicumopdracht.comparators;

import practicumopdracht.models.Show;

import java.util.Comparator;
/**
 *  Comparator for sorting kids friendly shows by true and false
 *
 * @ Author Frank van der Velde
 */
public class KidsFriendlyComparator implements Comparator<Show> {

    @Override
    public int compare(Show o1, Show o2) {
        return Boolean.compare(o1.getIsKidsFriendly(), o2.getIsKidsFriendly());
    }
}
