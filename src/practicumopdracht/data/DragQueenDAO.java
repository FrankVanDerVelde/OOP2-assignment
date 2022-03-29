package practicumopdracht.data;

import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  The abstact DAO for dragqueens that has all of the functionality a dragqueen DAO can use
 *
 * @ Author Frank van der Velde
 */
public abstract class DragQueenDAO implements DAO<DragQueen> {

    protected List<DragQueen> objects;

    public DragQueenDAO() {
        objects = new ArrayList<>();
    }

    public DragQueen getById(int id) {
        return objects.get(id);
    }

    /**
     *  Gets all dragqueens part of a given show by making a stream and filtering it
     */
    public List<DragQueen> getAllFor(Show object) {
        return objects.stream().filter(dragQueen -> (dragQueen.getBelongsTo() == object)).collect(Collectors.toList());
    }

    @Override
    public List<DragQueen> getAll() {
        return objects;
    }

    @Override
    public void addOrUpdate(DragQueen object) {
        if (!objects.contains(object)) {
            objects.add(object);
        }
    }

    @Override
    public void remove(DragQueen object) {

    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
