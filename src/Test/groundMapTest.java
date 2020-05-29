package Test;

import GroundTerritory.ListGroundTerritory;
import Model.Aircraft;
import Storage.ArrayListStorage;

import java.io.IOException;

public class groundMapTest {
    public static void main(String[] args) throws IOException {

        ArrayListStorage storage = new ArrayListStorage();
        ListGroundTerritory map = new ListGroundTerritory();
        Aircraft aircraft1 = new Aircraft(0, 0, 0, 1, 1);
        Aircraft aircraft2 = new Aircraft(1, 1, 1, 1, 1);
        map.fillTerritory(map.getTerritory(), 3, 4);
        storage.add(aircraft1);
        storage.add(aircraft2);
        aircraft1.move(map, storage, aircraft1);
    }
}
