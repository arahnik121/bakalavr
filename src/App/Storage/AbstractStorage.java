package App.Storage;

import App.Model.Aircraft;
import App.exceptions.ExistStorageException;
import App.exceptions.NotExistStorageException;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String id);

    protected abstract void doUpdate(Aircraft a, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Aircraft a, SK searchKey);

    protected abstract Aircraft doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Aircraft> doCopyAll();

    public void update(Aircraft a) {
        LOG.info("Update " + a);
        SK searchKey = getExistedSearchKey(a.getId());
        doUpdate(a, searchKey);
    }

    public void save(Aircraft a) {
        LOG.info("Save " + a);
        SK searchKey = getNotExistedSearchKey(a.getId());
        doSave(a, searchKey);
    }

    public void delete(String id) {
        LOG.info("Delete " + id);
        SK searchKey = getExistedSearchKey(id);
        doDelete(searchKey);
    }

    public Aircraft get(String id) {
        LOG.info("Get " + id);
        SK searchKey = getExistedSearchKey(id);
        return doGet(searchKey);
    }

    private SK getExistedSearchKey(String id) {
        SK searchKey = getSearchKey(id);
        if (!isExist(searchKey)) {
            LOG.warning("Aircraft " + id + " not exist");
            throw new NotExistStorageException(id);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String id) {
        SK searchKey = getSearchKey(id);
        if (isExist(searchKey)) {
            LOG.warning("Aircraft " + id + " already exist");
            throw new ExistStorageException(id);
        }
        return searchKey;
    }

    @Override
    public List<Aircraft> getAllAircraftsSorted() {
        LOG.info("getAllSorted");
        List<Aircraft> list = doCopyAll();
        Collections.sort(list);
        return list;
    }


}
