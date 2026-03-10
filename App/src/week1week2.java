import java.util.*;

public class week1week2 {

    private HashMap<String, Set<String>> index = new HashMap<>();
    private HashMap<String, List<String>> documents = new HashMap<>();
    private int n = 3;

    public void addDocument(String id, String text) {
        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {
            String gram = words[i] + " " + words[i + 1] + " " + words[i + 2];
            ngrams.add(gram);
            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(id);
        }

        documents.put(id, ngrams);
    }

    public void analyzeDocument(String id, String text) {
        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {
            String gram = words[i] + " " + words[i + 1] + " " + words[i + 2];
            ngrams.add(gram);
        }

        System.out.println("Extracted " + ngrams.size() + " n-grams");

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {
            if (index.containsKey(gram)) {
                for (String doc : index.get(gram)) {
                    matchCount.put(doc, matchCount.getOrDefault(doc, 0) + 1);
                }
            }
        }

        for (String doc : matchCount.keySet()) {
            int matches = matchCount.get(doc);
            double similarity = (matches * 100.0) / ngrams.size();
            System.out.println("Found " + matches + " matching n-grams with " + doc);
            System.out.println("Similarity: " + similarity + "%");
        }
    }

    public static void main(String[] args) {
        week1week2 system = new week1week2();

        system.addDocument("essay_089.txt",
                "machine learning improves computer vision and natural language processing");

        system.addDocument("essay_092.txt",
                "machine learning improves computer vision and deep learning systems");

        system.analyzeDocument("essay_123.txt",
                "machine learning improves computer vision and natural language systems");
    }
}