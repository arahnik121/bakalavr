package Map;

public class groundMap {
    private int[][] Map;

    public groundMap(int[][] map) {
        Map = map;
    }

    public int[][] getMap() {
        return Map;
    }


    public int[][] fillMap(int[][] GMap) {
        for (int i = 0; i < GMap.length; i++) {
            for (int j = 0; j < GMap[i].length; j++) {
               GMap[i][j] = ((int) (Math.random() * 5));
                System.out.print(GMap[i][j] + " ");
            }
            System.out.println();
        }
        return GMap;
    }

}