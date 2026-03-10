import java.util.*;

public class week1week2 {

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> sourceCount = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        sourceCount.put(source, sourceCount.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(pageViews.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Top Pages:");

        int count = 0;
        for (Map.Entry<String, Integer> e : list) {
            String url = e.getKey();
            int views = e.getValue();
            int unique = uniqueVisitors.get(url).size();

            System.out.println((count + 1) + ". " + url + " - " + views + " views (" + unique + " unique)");
            count++;
            if (count == 10) break;
        }

        System.out.println("\nTraffic Sources:");
        for (String s : sourceCount.keySet()) {
            System.out.println(s + " -> " + sourceCount.get(s));
        }
    }

    public static void main(String[] args) {

        week1week2 dashboard = new week1week2();

        dashboard.processEvent("/article/breaking-news", "user123", "google");
        dashboard.processEvent("/article/breaking-news", "user456", "facebook");
        dashboard.processEvent("/sports/championship", "user123", "direct");
        dashboard.processEvent("/sports/championship", "user789", "google");
        dashboard.processEvent("/article/breaking-news", "user999", "google");

        dashboard.getDashboard();
    }
}