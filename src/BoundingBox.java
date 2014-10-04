import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Ishh on 9/28/2014.
 */
import static java.lang.Math.ceil;
public class BoundingBox {

    private static final int size = 100;
    private ArrayList<Topic> topics;

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public static String getBoundingBoxIndex(Topic topic) {
        return getBoundingBoxIndex(topic.getX(), topic.getY());
    }

    public static String getBoundingBoxIndex(float x, float y) {
        double noise = 0.00001;
        Integer xIndex = (int)ceil((x+noise)/size);
        Integer yIndex = (int)ceil((y+noise)/size);
        return encodeIndex(xIndex, yIndex);
    }

    private static String encodeIndex(Integer x, Integer y) {
        return x.toString()+","+y.toString();
    }

    public static HashSet<String> getNeighbors(String boxIndex, int hop) {
        String[] coordinates = boxIndex.split(",");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        HashSet<String> neighbors = new HashSet<String>();
        int x_min = (x - hop) > 0? (x - hop):1;
        int x_max = x + hop;
        int y_min = (y - hop) > 0? (y - hop):1;
        int y_max = (y + hop);

        /* The neighbors will be
         *      N
         *  W [box] E
         *      S
         */

        // South Neighbors
        if(y_min == y - hop) {
            for(int i = x_min; i <= x_max; i++) {
                neighbors.add(encodeIndex(i , y_min));
            }
        }
        // West Neighbors
        if(x_min == x - hop) {
            for(int i = y_min; i <= y_max; i++) {
                neighbors.add(encodeIndex(x_min, i));
            }
        }
        // East Neighbors
        for(int i = y_min; i <= y_max; i++) {
            neighbors.add(encodeIndex(x_max, i));
        }
        // North Neighbors
        for(int i = x_min; i <= x_max; i++) {
            neighbors.add(encodeIndex(i , y_max));
        }
        return neighbors;
    }
}

class Topic {
    private int id;
    private float x;
    private float y;

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    Topic(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
