import java.util.*;

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

    public LinkedHashSet<Integer> processResults(Collection<Topic> results) {
        LinkedHashSet<Integer> ret = new LinkedHashSet<Integer>();
        if(this.getType().equals("q")) {
            for (Topic t : results) {
                if(ReadFile.questionInverseIndex.containsKey(t.getId())) {
                    ArrayList<Integer> questionList = ReadFile.questionInverseIndex.get(t.getId());
                    ret.addAll(questionList);
                }
            }
        }
        else {
            for(Topic t : results){
                ret.add(t.getId());
            }
        }
        int max = 0;
        Iterator<Integer> itr = ret.iterator();
        while(itr.hasNext() && max < this.getResultCount()) {
            Integer num = itr.next();
            System.out.print(num + " ");
            max++;
        }
        System.out.println();
        return ret;
    }
}
