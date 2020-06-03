package storage;

import App.Model.Aircraft;
import App.Storage.Storage;
import App.exceptions.ExistStorageException;
import App.exceptions.NotExistStorageException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Data.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Aircraft> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        List<Aircraft> sortedAircraft = Arrays.asList(R1, R2, R3);
        Collections.sort(sortedAircraft);
        Assert.assertEquals(sortedAircraft, list);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Aircraft newAircraft = new Aircraft(ID_1, 0, 1, 1, 1, "tree");
        storage.update(newAircraft);
        Assert.assertTrue(newAircraft.equals(storage.get(ID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(ID_1);
        assertSize(2);
        storage.get(ID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }


    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Aircraft r) {
        assertEquals(r, storage.get(r.getId()));
    }
}