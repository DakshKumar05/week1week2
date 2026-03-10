import java.util.*;

public class week1week2 {
    private LinkedHashMap<String, String> l1Cache = new LinkedHashMap<>(10000, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 10000;
        }
    };

    private Map<String, String> l2Cache = new HashMap<>();
    private Map<String, Integer> accessCounts = new HashMap<>();

    public String getVideo(String videoId) {
        accessCounts.put(videoId, accessCounts.getOrDefault(videoId, 0) + 1);

        if (l1Cache.containsKey(videoId)) {
            return "L1 Cache HIT";
        }

        if (l2Cache.containsKey(videoId)) {
            if (accessCounts.get(videoId) > 5) {
                l1Cache.put(videoId, "VideoData_From_SSD");
            }
            return "L2 Cache HIT -> Promoted to L1";
        }

        l2Cache.put(videoId, "/ssd/path/" + videoId);
        return "L3 Database HIT -> Added to L2";
    }

    public static void main(String[] args) {
        week1week2 cacheSystem = new week1week2();

        System.out.println(cacheSystem.getVideo("vid123")); // L3 -> L2
        System.out.println(cacheSystem.getVideo("vid123")); // L2 hit
        for (int i = 0; i < 6; i++) {
            System.out.println(cacheSystem.getVideo("vid456")); // after 6 accesses, promoted to L1
        }
        System.out.println(cacheSystem.getVideo("vid456")); // L1 hit
    }
}