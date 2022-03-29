package practicumopdracht.comparators;

import practicumopdracht.models.Show;

import java.util.Comparator;

public class ShowDateComparator implements Comparator<Show> {

    /**
     *  Comparator for sorting show dates from low to high
     *
     * @ Author Frank van der Velde
     */
    @Override
    public int compare(Show o1, Show o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
