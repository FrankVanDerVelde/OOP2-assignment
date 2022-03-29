package practicumopdracht.comparators;

import practicumopdracht.models.DragQueen;

import java.util.Comparator;

/**
 *  Comparator for sorting Salary from low to high
 *
 * @ Author Frank van der Velde
 */
public class SalaryComparator implements Comparator<DragQueen> {
    @Override
    public int compare(DragQueen o1, DragQueen o2) {
        return Double.compare(o1.getSalary(), o2.getSalary());
    }
}
