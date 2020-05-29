package GroundTerritory;

public class groundMap {
    private int rows;
    private int columns;
    private int[][] size;

    public groundMap(int[][] size) {
        this.size = size;
    }

    public groundMap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int[][] getSize() {
        return size;
    }

    public void fillMap(int[][] GMap) {
        for (int i = 0; i < GMap.length; i++) {
            for (int j = 0; j < GMap[i].length; j++) {
                if (GMap[i][j] == 0) {
                    GMap[i][j] = ((int) (Math.random() * 5 + 1));
                    System.out.print(GMap[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


}