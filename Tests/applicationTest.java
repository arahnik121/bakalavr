import App.GroundTerritory.ListGroundTerritory;
import App.Model.Aircraft;
import App.Storage.SQLStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class applicationTest {
    static String user = "postgres";
    static String pass = "1";
    static String url = "jdbc:postgresql://localhost:5432/UAVs";

//    protected static final File STORAGE_DIR = App.Config.getINSTANCE().getStorageDir();
    private static SQLStorage storage = new SQLStorage(url, user, pass);
    private static List<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
    private static ListGroundTerritory territory = new ListGroundTerritory(map, 3, 4);
    private static Aircraft aircraft1 = new Aircraft("0", 0, 0, 1, 1);
    private static Aircraft aircraft2 = new Aircraft("1", 1, 1, 1, 1);

    public static void main(String[] args) throws IOException {
        aircraft1.move(territory, storage, aircraft1);
    }
}
