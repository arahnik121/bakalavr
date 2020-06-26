package App.Model;

import App.GroundTerritory.ListGroundTerritory;
import App.Storage.Storage;
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


    public Aircraft(int x, int y, int rangeOfViewX, int rangeOfViewY) {
        this(UUID.randomUUID().toString(), x, y, rangeOfViewX, rangeOfViewY);
    }


    public Aircraft(String id, int x, int y, int rangeOfViewX, int rangeOfViewY) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rangeOfViewX = rangeOfViewX;
        this.rangeOfViewY = rangeOfViewY;
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


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    //Потоковая реализация только для хранилища на основе ArrayList`a
    public void move(ListGroundTerritory map, Storage storage, Aircraft aircraft) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        map.fillTerritory(map.getTerritory(), aircraft);
        scan(map, aircraft, storage);
        for (int i = aircraft.getX(); i < map.getTerritory().size() - 1; ) {
            for (int j = aircraft.getY(); j < map.getRowSize(i) - 1; ) {
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
                            scan(map, aircraft, storage);
                        }
                        break;
                    case "down":
                        i++;
                        aircraft.setX(i);
                        map.fillTerritory(map.getTerritory(), aircraft);
                        scan(map, aircraft, storage);
                        break;
                    case "left":
                        if (j == 0) {
                            System.out.println("You cant go there!");
                        } else {
                            j--;
                            aircraft.setY(j);
                            map.fillTerritory(map.getTerritory(), aircraft);
                            scan(map, aircraft, storage);
                        }
                        break;
                    case "right":
                        j++;
                        aircraft.setY(j);
                        map.fillTerritory(map.getTerritory(), aircraft);
                        scan(map, aircraft, storage);
                        break;
                    case "switch":
                        System.out.print("Choose aircraft id: ");
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


    //TODO: Убрать повторение кода из методов move
    public void moveUp(ListGroundTerritory map, Storage storage, Aircraft aircraft) {
        int i = aircraft.getX();
        int j = aircraft.getY();
        System.out.println("UAV coordinates are: " + aircraft.getX() + " " + aircraft.getY() + "." + " Value is: " + map.getTerritory().get(i).get(j));
        if (i == 0) {
            System.out.println("You cant go there!");
        } else {
            i--;
            aircraft.setX(i);
            map.fillTerritory(map.getTerritory(), aircraft);
            scan(map, aircraft, storage);
        }
    }


    public void moveDown(ListGroundTerritory map, Storage storage, Aircraft aircraft) {
        int i = aircraft.getX();
        int j = aircraft.getY();
        System.out.println("UAV coordinates are: " + aircraft.getX() + " " + aircraft.getY() + "." + " Value is: " + map.getTerritory().get(i).get(j));
        i++;
        aircraft.setX(i);
        map.fillTerritory(map.getTerritory(), aircraft);
        scan(map, aircraft, storage);
    }

    public void moveLeft(ListGroundTerritory map, Storage storage, Aircraft aircraft) {
        int i = aircraft.getX();
        int j = aircraft.getY();
        System.out.println("UAV coordinates are: " + aircraft.getX() + " " + aircraft.getY() + "." + " Value is: " + map.getTerritory().get(i).get(j));
        if (j == 0) {
            System.out.println("You cant go there!");
        } else {
            j--;
            aircraft.setY(j);
            map.fillTerritory(map.getTerritory(), aircraft);
            scan(map, aircraft, storage);
        }
    }

    public void moveRight(ListGroundTerritory map, Storage storage, Aircraft aircraft) {
        int i = aircraft.getX();
        int j = aircraft.getY();
        System.out.println("UAV coordinates are: " + aircraft.getX() + " " + aircraft.getY() + "." + " Value is: " + map.getTerritory().get(i).get(j));
        j++;
        aircraft.setY(j);
        map.fillTerritory(map.getTerritory(), aircraft);
        scan(map, aircraft, storage);
    }




//Метод реализован только для хранилища, реализуемого на базе ArrayList`a
//    public void Switch(ListGroundTerritory map, Storage storage, Aircraft aircraft, String id) {
//        int i = aircraft.getX();
//        int j = aircraft.getY();
//        System.out.println("UAV coordinates are: " + aircraft.getX() + " " + aircraft.getY() + "." + " Value is: " + map.getTerritory().get(i).get(j));
//        try {
//            aircraft = storage.get(id);
//        } catch (NotExistStorageException e) {
//            System.out.println("No such aircraft!");
//        }
//    }


    private String scan(ListGroundTerritory map, Aircraft aircraft, Storage storage) {
        GroundObject o = new GroundObject(aircraft.getX(), aircraft.getY(), " ");
        System.out.print("On coordinates " + aircraft.getX() + " " + aircraft.getY() + " ");
        if (compareValues(map, aircraft.getX(), aircraft.getY()) != -1) {
            switch (map.getTerritory().get(aircraft.getX()).get(aircraft.getY())) {
                case 0:
                    o.setClassification("Empty");
                    storage.updateAircraft(aircraft);
                    storage.save(aircraft, o);
                    return o.getClassification();
                case 1:
                    o.setClassification("Building");
                    storage.updateAircraft(aircraft);
                    storage.save(aircraft, o);
                    return o.getClassification();
                case 2:
                    o.setClassification("Mountain");
                    storage.updateAircraft(aircraft);
                    storage.save(aircraft, o);
                    return o.getClassification();
                case 3:
                    o.setClassification("Forest");
                    storage.updateAircraft(aircraft);
                    storage.save(aircraft, o);
                    return o.getClassification();
                case 4:
                    o.setClassification("Car");
                    storage.updateAircraft(aircraft);
                    storage.save(aircraft, o);
                    return o.getClassification();
                default:
                    o.setClassification("Error!");
                    storage.updateAircraft(aircraft);
                    storage.save(aircraft, o);
                    return o.getClassification();
            }
        } else {
            o.setClassification("Nothing found");
            storage.updateAircraft(aircraft);
            if (o.getX() == storage.getGroundObjects(o.getX(), o.getY()).getX()) {
                if (o.getX() == storage.getGroundObjects(o.getX(), o.getY()).getY()) {
                    storage.update(o);
                }
            } else {
                storage.save(aircraft, o);
            }
            return o.getClassification();
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
