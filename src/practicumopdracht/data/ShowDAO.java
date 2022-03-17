package practicumopdracht.data;

import practicumopdracht.models.Show;

import java.util.List;

public class ShowDAO implements DAO<Show> {

    protected List<Show> objects;

    public Show getById(int id) {
        return objects.get(id);
    }

    @Override
    public List<Show> getAll() {
        return objects;
    }

    @Override
    public void addOrUpdate(Show object) {
        if (!objects.contains(object)) {
            objects.add(object);
        }
    }

    @Override
    public void remove(Show object) {
        objects.remove(object);
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
