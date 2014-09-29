import java.util.ArrayList;

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

    public static String[] getNeighbors(String boxIndex, int hop) {
        String[] coordinates = boxIndex.split(",");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        ArrayList<String> neighbors = new ArrayList<String>();
        int x_min = (x - hop) > 0? (x - hop):0;
        int x_max = x + hop;
        int y_min = (y - hop) > 0? (y - hop):0;
        int y_max = (y + hop);
        return coordinates;
    }
}

class Topic {
    private int id;
    private float x;
    private float y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    Topic(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
