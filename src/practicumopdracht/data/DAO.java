package practicumopdracht.data;

import java.util.List;

/**
 *  The interface that defines all the methods DAO's must have
 *
 * @ Author Frank van der Velde
 */
public interface DAO<T> {
    List<T> getAll();

    void addOrUpdate(T object);

    void remove(T object);

    boolean save();

    boolean load();
}
