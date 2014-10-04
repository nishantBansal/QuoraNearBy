import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

/**
 * Created by Ishh on 9/28/2014.
 */
public class SearchNearByTopicsAndQuestions {

    static TreeMap<Double, Topic> closestTopics;
    static String filePath = "input.txt";
    static int boxesRead;
    public static void main(String[] args) {
        ReadFile.read(filePath);
        for(Query q : ReadFile.queries) {
            closestTopics = new TreeMap<Double, Topic>();
            boxesRead = 0;
            getClosestTopics(q);
            q.processResults(closestTopics.values());
        }
    }

    private static void getClosestTopics(Query q) {
        String boxIndex = BoundingBox.getBoundingBoxIndex(q.getX(), q.getY());
        addClosestTopics(q, boxIndex);
        int i = 1;
        while(closestTopics.size() < q.getResultCount() && boxesRead < ReadFile.boxes.size()) {
            getTopicsFromNeighbours(q, i);
            i++;
        }
    }

    private static void getTopicsFromNeighbours(Query q, int hop) {
        String boxIndex = BoundingBox.getBoundingBoxIndex(q.getX(), q.getY());
        HashSet<String> neighborsIndex = BoundingBox.getNeighbors(boxIndex, hop);
        for(String index: neighborsIndex) {
            addClosestTopics(q, index);
        }
    }

    private static void addClosestTopics(Query q, String boxIndex) {
        if(ReadFile.boxes.containsKey(boxIndex)) {
            boxesRead++;
            BoundingBox box = ReadFile.boxes.get(boxIndex);
            ArrayList<Topic> topics = box.getTopics();
            for(Topic t : topics ) {
                double distance = computeDistance(q, t);
                closestTopics.put(distance, t);
            }
        }
    }

    private static double computeDistance(Query q, Topic t) {
        return sqrt(pow(q.getX() - t.getX(), 2) + pow(q.getY() - t.getY(), 2));
    }
}