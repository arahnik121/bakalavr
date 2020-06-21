package App.Storage;

import App.Model.Aircraft;
import App.Model.GroundObject;

import java.util.List;

public interface Storage {

    void clear();

    void save(Aircraft a, GroundObject o);

    void saveAircraft(Aircraft a);

    void update(GroundObject o);

    void updateAircraft(Aircraft r);

    GroundObject getGroundObjects(int x, int y);

    Aircraft get(String id);

    void delete(String id);

    List<GroundObject> getAllSorted();

    List<Aircraft> getAllAircraftsSorted();

    /**
     * @return array, contains only Requests in storage (without null)
     */

    int size();
}
