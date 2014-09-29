import java.util.ArrayList;

/**
 * Created by Ishh on 9/29/2014.
 */
public class Query {

    private String type;
    private int resultCount;
    private float x;
    private float y;
    private ArrayList results;

    public Query(String type, int resultCount, float x, float y) {
        this.type = type;
        this.resultCount = resultCount;
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public int getResultCount() {
        return resultCount;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ArrayList getResults() {
        return results;
    }

    public void setResults(ArrayList results) {
        this.results = results;
    }
}
