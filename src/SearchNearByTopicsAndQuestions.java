import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.ceil;

/**
 * Created by Ishh on 9/28/2014.
 */
public class SearchNearByTopicsAndQuestions {

    static TreeMap<Double, Topic> closestTopics = new TreeMap<Double, Topic>();
    static String filePath = "input.txt";
    public static void main(String[] args) {
        //System.out.println(ceil(0.99));
        ReadFile.read(filePath);
        for(Query q : ReadFile.queries) {
            getClosestTopics(q);
        }
    }

    private static void getClosestTopics(Query q) {
        String boxIndex = BoundingBox.getBoundingBoxIndex(q.getX(), q.getY());
        if(ReadFile.boxes.containsKey(boxIndex)) {
            BoundingBox box = ReadFile.boxes.get(boxIndex);
            ArrayList<Topic> topics = box.getTopics();
            for(Topic t : topics ) {
                double distance = computeDistance(q, t);
                //System.out.println("Distance: " + distance);
                closestTopics.put(distance, t);
            }
        }
    }

    private static double computeDistance(Query q, Topic t) {
        return sqrt(pow(q.getX() - t.getX(), 2) + pow(q.getY() - t.getY(), 2));
    }
}