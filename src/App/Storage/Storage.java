package App.Storage;

import App.Model.Aircraft;

import java.util.List;

public interface Storage {

    void clear();

    void save(Aircraft a);

    void update(Aircraft r);

    Aircraft get(String id);

    void delete(String id);

    List<Aircraft> getAllSorted();

    /**
     * @return array, contains only Requests in storage (without null)
     */

    int size();
}
