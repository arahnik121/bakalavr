package Model;

import GroundTerritory.ListGroundTerritory;
import Storage.ArrayListStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aircraft {
    private int id;
    private int x;
    private int y;
    private int rangeOfViewX;
    private int rangeOfViewY;

    public Aircraft(int id, int x, int y, int rangeOfViewX, int rangeOfViewY) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rangeOfViewX = rangeOfViewX;
        this.rangeOfViewY = rangeOfViewY;
    }

    public int getId() {
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

    public void move(ListGroundTerritory map, ArrayListStorage storage, Aircraft aircraft) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
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
                        aircraft = storage.get(Integer.parseInt(reader1.readLine()));
                        i = aircraft.getX();
                        j = aircraft.getY();
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
            }
        }
        reader.close();
        reader1.close();
    }

    private void scan(ListGroundTerritory map, int x, int y) {
        System.out.print("On coordinates " + x + " " + y + " ");
        if (compareValues(map, x, y) != -1) {
            switch (map.getTerritory().get(x).get(y)) {
                case 0:
                    System.out.println("object recognized as group of humans");
                    break;
                case 1:
                    System.out.println("object recognized as Building");
                    break;
                case 2:
                    System.out.println("object recognized as Mountain");
                    break;
                case 3:
                    System.out.println("object recognized as Forest");
                    break;
                case 4:
                    System.out.println("object recognized as Car");
                    break;
                default:
                    System.out.println("Error!");
            }
        } else {
            System.out.println("no objects found!");
        }
    }

    private int compareValues(ListGroundTerritory territory, int x, int y) {
        if (territory.getRow(x).get(y).equals(territory.getRow(x).get(y + 1)) && territory.getRow(x).get(y).equals(territory.getRow(x + 1).get(y)) && territory.getRow(x).get(y).equals(territory.getRow(x + 1).get(y + 1))) {
            return territory.getRow(x).get(y);
        } else {
            return -1;
        }
    }
}
