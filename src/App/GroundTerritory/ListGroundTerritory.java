package App.GroundTerritory;

import App.Model.Aircraft;

import java.util.ArrayList;
import java.util.List;

public class ListGroundTerritory {
    private List<ArrayList<Integer>> territory = new ArrayList<ArrayList<Integer>>();
    private String obj;

    public String getObj() {
        return obj;
    }

    public int getRowSize(int x) {
        return territory.get(x).size();
    }

    public ArrayList<Integer> getRow(int x) {
        return territory.get(x);
    }

    public List<ArrayList<Integer>> getTerritory() {
        return territory;
    }

    public void createTerritory(List<ArrayList<Integer>> map, int width, int length) {
        for (int i = 0; i < width; i++) {       //Создаем в List`e ArrayList`ы
            map.add(new ArrayList<Integer>());
            for (int j = 0; j < length; j++) {   //Создаем в ArrayList`e Integer`ы
                int randomNumber = (int) (Math.random() * 5 + 1);
                map.get(i).add(randomNumber);    //Каждый Integer генерируется рандомно от 1 до 5
                System.out.print(map.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void fillTerritory(List<ArrayList<Integer>> map, Aircraft aircraft) {
        int indexOfNewObjectY = aircraft.getY() + aircraft.getRangeOfViewY();
        int indexOfNewObjectX = aircraft.getX() + aircraft.getRangeOfViewX();

        expandTerritory(map, aircraft);
        for (int i = aircraft.getX() - aircraft.getRangeOfViewX(); i <= indexOfNewObjectX; i++) {
            for (int j = aircraft.getY() - aircraft.getRangeOfViewY(); j <= indexOfNewObjectY; j++) {
                try {
                    if (i >= 0 && j >= 0 && map.get(i).get(j) == 0) {
                        int randomNumber = (int) (Math.random() * 5 + 1);
                        map.get(i).set(j, randomNumber);
                    }
                } catch (IndexOutOfBoundsException e) {
                    if (map.get(i).size() < map.get(aircraft.getX()).size()) {
                        map.get(i).add(0);
                        j--;
                    }
                }
            }
        }
        showTerritory(map);
    }


    private void showTerritory(List<ArrayList<Integer>> map) {
        for (ArrayList<Integer> integers : map) {
            for (Integer integer : integers) {
                if (integer == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(integer + " ");
                }
            }
            System.out.println();
        }
    }

    private void expandTerritory(List<ArrayList<Integer>> map, Aircraft aircraft) {
        int expandPositionY = map.get(aircraft.getX()).size() - aircraft.getRangeOfViewY();
        int expandPositionX = map.size() - aircraft.getRangeOfViewX();
        int indexOfNewObjectY = aircraft.getY() + aircraft.getRangeOfViewY();
        int indexOfNewObjectX = aircraft.getX() + aircraft.getRangeOfViewX();

        //Расширяемся, если дошли до крайней точни обзора
        if (aircraft.getX() == expandPositionX) {
            for (int i = indexOfNewObjectX; i < indexOfNewObjectX + 1; i++) {
                map.add(i, new ArrayList<Integer>());
                for (int j = 0; j < indexOfNewObjectY; j++) {
                    map.get(i).add(0);
                }
            }

        } else if (aircraft.getY() == expandPositionY) {
            for (int i = aircraft.getX(); i < aircraft.getX() + aircraft.getRangeOfViewY(); i++) {
                for (int j = map.get(i).size() - 1; j < indexOfNewObjectY; j++) {
                    int randomNumber = (int) (Math.random() * 5 + 1);
                    map.get(i).add(randomNumber);
                }
            }
        }
    }
}