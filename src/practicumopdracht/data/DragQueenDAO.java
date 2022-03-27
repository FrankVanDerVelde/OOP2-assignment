package practicumopdracht.data;

import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DragQueenDAO implements DAO<DragQueen> {

    protected List<DragQueen> objects;

    public DragQueenDAO() {
        objects = new ArrayList<>();
    }

    public DragQueen getById(int id) {
        return objects.get(id);
    }

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
