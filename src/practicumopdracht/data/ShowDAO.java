package practicumopdracht.data;

import practicumopdracht.models.Show;

import java.util.ArrayList;
import java.util.List;

public class ShowDAO implements DAO<Show> {

    protected List<Show> objects;

    public ShowDAO() {
        objects = new ArrayList<>();
    }

    public Show getById(int id) {
        return objects.get(id);
    }

    public int getIdFor(Show object) {
        for (int i = 0; i < objects.size(); i++) {
            Show show = objects.get(i);
            if(show.equals(object)){
                return i;
            }
        }
        return -1;
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
