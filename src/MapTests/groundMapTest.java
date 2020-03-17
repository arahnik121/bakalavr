package MapTests;

import Map.groundMap;
import Model.Aircraft;

import java.io.IOException;

public class groundMapTest {
    public static void main(String[] args) throws IOException {
        groundMap map = new groundMap(new int[10][20]);
        Aircraft aircraft = new Aircraft(1, 0, 0);
        map.fillMap(map.getMap());
        aircraft.move(map, aircraft.getX(), aircraft.getY());
    }
}
