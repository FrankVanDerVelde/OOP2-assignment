package practicumopdracht.comparators;

import practicumopdracht.models.DragQueen;

import java.util.Comparator;

/**
 *  Comparator for sorting genders and then age low to high
 *
 * @ Author Frank van der Velde
 */
public class DragQueenAgeAndGenderComparator implements Comparator<DragQueen> {
    @Override
    public int compare(DragQueen o1, DragQueen o2) {
        int comparison = o1.getGender().compareTo(o2.getGender());
        if (comparison == 0) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
        return comparison;
    }
}
