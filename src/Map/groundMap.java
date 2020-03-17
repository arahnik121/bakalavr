package Map;

public class groundMap {
    private static int[][] GMap = new int[10][20];

    public static int[][] getMap() {
        return GMap;
    }

    public static int[][] fillMap(int[][] GMap) {
        for (int i = 0; i < GMap.length; i++) {
            for (int j = 0; j < GMap[i].length; j++) {
                GMap[i][j] = ((int) (Math.random() * 5));
                System.out.print(GMap[i][j] + " ");
            }
            System.out.println();
        }
        return GMap;
    }


    public static void matrixEqual(int[][] GMap) {
        for (int i = 0; i < GMap.length - 1; i++) {
            for (int j = 0; j < GMap[i].length - 1; j++) {
                if (GMap[i][j] == GMap[i][j + 1] && GMap[i][j] == GMap[i + 1][j] && GMap[i][j] == GMap[i + 1][j + 1]) {
                    System.out.print("On coordinates " + i + " " + j + " ");
                    switch (GMap[i][j]) {
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
                            System.out.println("object cannot be recognized");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        matrixEqual(fillMap(GMap));
    }
}