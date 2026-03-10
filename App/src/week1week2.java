import java.util.*;

public class week1week2 {
    private Map<String, Integer> globalStats = new HashMap<>();

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        Map<String, Integer> prefixFrequencies = new HashMap<>();
    }

    private TrieNode root = new TrieNode();

    public void updateFrequency(String query) {
        globalStats.put(query, globalStats.getOrDefault(query, 0) + 1);
        int freq = globalStats.get(query);

        TrieNode current = root;
        for (char c : query.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
            current.prefixFrequencies.put(query, freq);
        }
    }

    public List<String> search(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) return new ArrayList<>();
            current = current.children.get(c);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue().compareTo(a.getValue())
        );
        pq.addAll(current.prefixFrequencies.entrySet());

        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            results.add(pq.poll().getKey());
        }
        return results;
    }

    public static void main(String[] args) {
        week1week2 q = new week1week2();
        q.updateFrequency("john_doe");
        q.updateFrequency("john_doe");
        q.updateFrequency("jane_smith");
        q.updateFrequency("admin");
        q.updateFrequency("admin");
        q.updateFrequency("admin");

        System.out.println(q.search("jo"));
        System.out.println(q.search("ad"));
    }
}