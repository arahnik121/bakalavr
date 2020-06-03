import App.GroundTerritory.ListGroundTerritory;
import App.Model.Aircraft;
import App.Storage.AbstractStorage;
import App.Storage.ArrayListStorage;

import java.io.IOException;

public class applicationTest {
//    protected static final File STORAGE_DIR = App.Config.getINSTANCE().getStorageDir();
    private static AbstractStorage storage = new ArrayListStorage();
    private static ListGroundTerritory map = new ListGroundTerritory();
    private static Aircraft aircraft1 = new Aircraft("0", 0, 0, 1, 1, " ");
    private static Aircraft aircraft2 = new Aircraft("1", 1, 1, 1, 1, " ");

    public static void main(String[] args) throws IOException {
        map.createTerritory(map.getTerritory(), 3, 4);
        storage.save(aircraft1);
        storage.save(aircraft2);
        aircraft1.move(map, storage, aircraft1);
    }
}
