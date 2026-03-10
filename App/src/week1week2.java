import java.util.*;

public class week1week2 {
    private Map<String, Integer> userMap = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !userMap.containsKey(username);
    }

    public boolean registerUsername(String username, int userId) {
        if (checkAvailability(username)) {
            userMap.put(username, userId);
            return true;
        }
        return false;
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        if (!checkAvailability(username)) {
            suggestions.add(username + "1");
            suggestions.add(username + "2");
            suggestions.add(username.replace("_", "."));
        }
        return suggestions;
    }

    public String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;
        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > maxAttempts) {
                maxAttempts = entry.getValue();
                mostAttempted = entry.getKey();
            }
        }
        return mostAttempted;
    }

    public static void main(String[] args) {
        week1week2 checker = new week1week2();
        checker.registerUsername("john_doe", 1);
        checker.registerUsername("admin", 2);

        System.out.println("checkAvailability(\"john_doe\") → " + checker.checkAvailability("john_doe"));
        System.out.println("checkAvailability(\"jane_smith\") → " + checker.checkAvailability("jane_smith"));
        System.out.println("suggestAlternatives(\"john_doe\") → " + checker.suggestAlternatives("john_doe"));

        for (int i = 0; i < 10543; i++) {
            checker.checkAvailability("admin");
        }

        System.out.println("getMostAttempted() → " + checker.getMostAttempted());
    }
}