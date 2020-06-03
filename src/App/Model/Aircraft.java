package App.Model;

import App.GroundTerritory.ListGroundTerritory;
import App.Storage.AbstractStorage;
import App.exceptions.NotExistStorageException;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Aircraft implements Comparable<Aircraft>, Serializable {
    private String id;
    private int x;
    private int y;
    private int rangeOfViewX;
    private int rangeOfViewY;
    private String objectInfo;



    public Aircraft(int x, int y, int rangeOfViewX, int rangeOfViewY, String objectInfo) {
        this(UUID.randomUUID().toString(), x, y, rangeOfViewX, rangeOfViewY, objectInfo);
    }

    public Aircraft(String id, int x, int y, int rangeOfViewX, int rangeOfViewY, String objectInfo) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rangeOfViewX = rangeOfViewX;
        this.rangeOfViewY = rangeOfViewY;
        this.objectInfo = objectInfo;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRangeOfViewX() {
        return rangeOfViewX;
    }

    public int getRangeOfViewY() {
        return rangeOfViewY;
    }

    public String getObjectInfo() {
        return objectInfo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(ListGroundTerritory map, AbstractStorage storage, Aircraft aircraft) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        map.fillTerritory(map.getTerritory(), aircraft);
        scan(map, aircraft.getX(), aircraft.getY());
        for (int i = aircraft.getX(); i < map.getTerritory().size() - 1;) {
            for (int j = aircraft.getY(); j < map.getRowSize(i) - 1;) {
                System.out.println("UAV coordinates are: " + aircraft.getX() + " " + aircraft.getY() + "." + " Value is: " + map.getTerritory().get(i).get(j));
                String a = reader.readLine();
                switch (a) {
                    case "up":
                        if (i == 0) {
                            System.out.println("You cant go there!");
                        } else {
                            i--;
                            aircraft.setX(i);
                            map.fillTerritory(map.getTerritory(), aircraft);
                            scan(map, i, j);
                        }
                        break;
                    case "down":
                        i++;
                        aircraft.setX(i);
                        map.fillTerritory(map.getTerritory(), aircraft);
                        scan(map, i, j);
                        break;
                    case "left":
                        if (j == 0) {
                            System.out.println("You cant go there!");
                        } else {
                            j--;
                            aircraft.setY(j);
                            map.fillTerritory(map.getTerritory(), aircraft);
                            scan(map, i, j);
                        }
                        break;
                    case "right":
                        j++;
                        aircraft.setY(j);
                        map.fillTerritory(map.getTerritory(), aircraft);
                        scan(map, i, j);
                        break;
                    case "switch":
                        System.out.print("Choose aircraft id: " );
                        String line = reader1.readLine();
                        try {
                            aircraft = storage.get(line);
                            i = aircraft.getX();
                            j = aircraft.getY();
                            break;
                        } catch (NotExistStorageException e) {
                            System.out.println("No such aircraft!");
                            break;
                        }
                    default:
                        System.out.println("Error");
                        break;
                }
            }
        }
        reader1.close();
        reader.close();
    }

    private String scan(ListGroundTerritory map, int x, int y) {
        System.out.print("On coordinates " + x + " " + y + " ");
        if (compareValues(map, x, y) != -1) {
            switch (map.getTerritory().get(x).get(y)) {
                case 0:
                    return objectInfo = ("object recognized as group of humans");
                case 1:
                    return objectInfo = ("object recognized as Building");
                case 2:
                    return objectInfo = ("object recognized as Mountain");
                case 3:
                    return objectInfo = ("object recognized as Forest");
                case 4:
                    return objectInfo = ("object recognized as Car");
                default:
                    return objectInfo = ("Error!");
            }
        } else {
            return objectInfo = ("no objects found!");
        }
    }

    private int compareValues(ListGroundTerritory territory, int x, int y) {
        if (territory.getRow(x).get(y).equals(territory.getRow(x).get(y + 1)) && territory.getRow(x).get(y).equals(territory.getRow(x + 1).get(y)) && territory.getRow(x).get(y).equals(territory.getRow(x + 1).get(y + 1))) {
            return territory.getRow(x).get(y);
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return Objects.equals(id, aircraft.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, rangeOfViewX, rangeOfViewY);
    }

    @Override
    public String toString() {
        return id + '(' + x + " " + y + ')';
    }

    @Override
    public int compareTo(@NotNull Aircraft aircraft) {
        int cmp = id.compareTo(aircraft.id);
        return cmp != 0 ? cmp : id.compareTo(aircraft.id);
    }
}
