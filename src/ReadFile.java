import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Ishh on 9/28/2014.
 */
public class ReadFile {

    public static int topicsCount;
    public static Hashtable<Integer, ArrayList<Integer>> questionInverseIndex = new Hashtable<Integer, ArrayList<Integer>>();
    public static Hashtable<String, BoundingBox> boxes = new Hashtable<String, BoundingBox>();
    public static ArrayList<Query> queries = new ArrayList<Query>();
    public static void read(String filePath) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(filePath));
            String s = input.readLine();
            String[] split = s.split(" ");
            topicsCount = Integer.parseInt(split[0]);
            int questionsCount = Integer.parseInt(split[1]);
            int queryCount = Integer.parseInt(split[2]);
            readTopics(input, topicsCount);
            readQuestions(input, questionsCount);
            readQueries(input, queryCount);
        }
        catch (IOException e){
            System.out.println("Exception reading the file");
        }
    }

    private static void readTopics(BufferedReader input, int count) {
        try {
            for (int i = 0; i < count; i++) {
                String topicStr = input.readLine();
                String[] split = topicStr.split(" ");
                int id = Integer.parseInt(split[0]);
                float x = Float.parseFloat(split[1]);
                float y = Float.parseFloat(split[2]);
                Topic topic = new Topic(id, x, y);
                addTopicToBox(topic);
            }
        }
        catch (IOException e){
            System.out.println("Exception reading the topics from file");
        }
    }

    private static void addTopicToBox(Topic topic){
        String boxIndex = BoundingBox.getBoundingBoxIndex(topic);
        if(boxes.containsKey(boxIndex)) {
            BoundingBox box = boxes.get(boxIndex);
            ArrayList<Topic> topicsList = box.getTopics();
            topicsList.add(topic);
        }
        else {
            ArrayList<Topic> list = new ArrayList<Topic>();
            list.add(topic);
            BoundingBox box = new BoundingBox();
            box.setTopics(list);
            boxes.put(boxIndex, box);
        }
    }

    private static void readQuestions(BufferedReader input, int count) {
        try {
            for (int i = 0; i < count; i++) {
                String questions = input.readLine();
                String[] split = questions.split(" ");
                int questionId = Integer.parseInt(split[0]);
                for(int t = 1; t < split.length; t++){
                    int topicId = Integer.parseInt(split[t]);
                    if(questionInverseIndex.containsKey(topicId)) {
                        ArrayList<Integer> questionList = questionInverseIndex.get(topicId);
                        questionList.add(questionId);
                    }
                    else {
                        ArrayList<Integer> questionList = new ArrayList<Integer>();
                        questionList.add(questionId);
                        questionInverseIndex.put(topicId, questionList);
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("Exception reading the topics from file");
        }
    }

    private static void readQueries(BufferedReader input, int count) {
        try {
            for (int i = 0; i < count; i++) {
                String queryStr = input.readLine();
                String[] split = queryStr.split(" ");
                String queryType = split[0];
                int resultCount = Integer.parseInt(split[1]);
                float x = Float.parseFloat(split[2]);
                float y = Float.parseFloat(split[3]);
                Query query = new Query(queryType, resultCount, x, y);
                queries.add(query);
            }
        }
        catch (IOException e){
            System.out.println("Exception reading the topics from file");
        }
    }
}
