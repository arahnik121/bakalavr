package Model;

import Map.groundMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aircraft {
    private int id;
    private int X;
    private int Y;

    public int getId() {
        return id;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Aircraft(int id, int X, int Y) {
        this.id = id;
        this.X = X;
        this.Y = Y;
    }

    public void move(groundMap map, int x, int y) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = x; i < map.getMap().length - 1; i++) {
            for (int j = y; j < map.getMap()[i].length - 1; j++) {
                System.out.println("UAV coordinates are: " + x + " " + y);
                String a = reader.readLine();
                switch (a) {
                    case "up":
                        if (x == 0) {
                            System.out.println("You cant go there!");
                        } else {
                            x--;
                            scan(map, x, y);
                        }
                        break;
                    case "down":
                        if (x == map.getMap().length - 1) {
                            System.out.println("You cant go there!");
                        } else {
                            x++;
                            scan(map, x, y);
                        }
                        break;
                    case "left":
                        if (y == 0) {
                            System.out.println("You cant go there!");
                        } else {
                            y--;
                            scan(map, x, y);
                        }
                        break;
                    case "right":
                        if (y == map.getMap()[i].length) {
                            System.out.println("You cant go there!");
                        } else {
                            y++;
                            scan(map, x, y);
                        }
                        break;
                    default:
                        System.out.println("Error");
                }
            }
        }
    }
    
    public void scan(groundMap map, int x, int y) {
        for (int i = x; i < map.getMap().length - 1;) {
            for (int j = y; j < map.getMap()[i].length - 1;) {
                if (map.getMap()[i][j] == map.getMap()[i][j + 1] && map.getMap()[i][j] == map.getMap()[i + 1][j] && map.getMap()[i][j] == map.getMap()[i + 1][j + 1]) {
                    System.out.print("On coordinates " + i + " " + j + " ");
                    switch (map.getMap()[i][j]) {
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
                            System.out.println("no objects found");
                    }
                }
            }
        }
    }
}
