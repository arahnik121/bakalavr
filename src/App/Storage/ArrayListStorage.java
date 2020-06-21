package App.Storage;

import App.Model.Aircraft;
import App.Model.GroundObject;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStorage extends AbstractStorage<Integer> {
    private List<Aircraft> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void save(Aircraft a, GroundObject o) {

    }

    @Override
    public void saveAircraft(Aircraft a) {

    }

    @Override
    public void update(GroundObject o) {

    }

    @Override
    public void updateAircraft(Aircraft r) {

    }

    @Override
    public GroundObject getGroundObjects(int x, int y) {
        return null;
    }

    @Override
    public List<GroundObject> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected Integer getSearchKey(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Aircraft a, Integer searchKey) {
        list.set(searchKey, a);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Aircraft a, Integer searchKey) {
        list.add(a);
    }

    @Override
    protected Aircraft doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove(searchKey.intValue());
    }

    @Override
    protected List<Aircraft> doCopyAll() {
        return new ArrayList<>(list);
    }
}
