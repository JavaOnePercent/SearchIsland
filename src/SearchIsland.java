import java.util.*;
import static java.lang.Math.*;

public class SearchIsland {

    private List<Coordinates> islandsPoints = new ArrayList<Coordinates>(); // лист с координатами точек "1"
    private List<List<Coordinates>> islandsList =new ArrayList<>(); // лист, куда записываются найденные острова
    private Queue<Coordinates> queuePoints = new LinkedList<Coordinates>(); // очередь точек координат острова
    private Queue<List<Coordinates>> queueIslands = new LinkedList<>(); // очередь островов, которые проверяются на уникальность
    private int islands = 0; // найденные острова
    private int uniqueness = 0; // уникальные острова
    static boolean livingThread = true; // статус потока, ищущий острова

    public SearchIsland(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 1) {
                    islandsPoints.add(new Coordinates(i, j));
                }
        }
    }

    /**
     * ищет расстояние Евклида, между точками островов
     */
    public int EuclideanDistance(Coordinates c1, Coordinates c2){
        return abs(c1.getX() - c2.getX()) + abs(c1.getY() - c2.getY());
    }

    /**
     * проверяет являются ли точки соседними
     */
    public boolean NeighboringPoint(Coordinates now, Coordinates next) {
        List<Coordinates> neighboring = Arrays.asList(
                new Coordinates(now.getX() - 1, now.getY()),
                new Coordinates(now.getX(), now.getY() - 1),
                new Coordinates(now.getX() + 1, now.getY()),
                new Coordinates(now.getX(), now.getY() + 1));
        for (int i = 0; i < neighboring.size(); i++) {
            if (neighboring.get(i).getX() == next.getX() && neighboring.get(i).getY() == next.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * выводит результат на экран
     */
    public void show() {
        System.out.println("Количество островов: " + islands + "\nКоличество островов разной формы: " + uniqueness);
    }

    /**
     * ищет количество островов разной формы
     */
    public synchronized boolean island() {
        while (queueIslands.isEmpty() && livingThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!queueIslands.isEmpty()) {
            islandsList.add(queueIslands.poll());
            for (int i = 0; i < islandsList.size() - 1; i++) {
                if (islandsList.get(islandsList.size() - 1).size() == islandsList.get(i).size()) {
                    int distance = 0;
                    boolean identification = true;
                    for (int j = 0; j < islandsList.get(islandsList.size() - 1).size(); j++) {
                        int current = EuclideanDistance(islandsList.get(i).get(j), islandsList.get(islandsList.size() - 1).get(j));
                        if (current != distance && distance != 0) {
                            identification = false;
                            break;
                        }
                        distance = current;
                    }
                    if (identification) {
                        uniqueness -= 1;
                        break;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * ищет количество островов
     */
    public synchronized boolean uniqueness() {
        if(!islandsPoints.isEmpty()){
            queuePoints.add(islandsPoints.remove(0));
            List<Coordinates> island = new ArrayList<Coordinates>();
            while (!queuePoints.isEmpty()) {
                Coordinates now = queuePoints.poll();
                island.add(now);
                int i = 0;
                while (i < islandsPoints.size()) {

                    if (NeighboringPoint(now, islandsPoints.get(i))) {
                        queuePoints.add(islandsPoints.remove(i));
                    }
                    else {
                        i += 1;
                    }
                }
            }
            islands += 1;
            uniqueness += 1;
            queueIslands.add(island);
            notify();
            return livingThread;
        }
        else {
            livingThread = false;
            return livingThread;
        }
    }
}