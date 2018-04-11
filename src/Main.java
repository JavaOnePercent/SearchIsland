public class Main {
    public static void main(String[] args) {
        int map[][] = {{0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                       {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
                       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                       {1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                       {1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                       {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1},
                       {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                       {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
                       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                       {1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                       {1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                       {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1}};
        SearchIsland searchIsland = new SearchIsland(map);
        Island island = new Island(searchIsland);
        Identification identification = new Identification(searchIsland);
        Thread islands = new Thread(island);
        Thread identifications = new Thread(identification);
        islands.start();
        identifications.start();
    }
}