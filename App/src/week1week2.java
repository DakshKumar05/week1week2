import java.util.*;

class DNSEntry {
    String domain;
    String ip;
    long expiry;

    DNSEntry(String d, String i, long ttl) {
        domain = d;
        ip = i;
        expiry = System.currentTimeMillis() + ttl;
    }

    boolean expired() {
        return System.currentTimeMillis() > expiry;
    }
}

public class week1week2 {

    private int capacity = 5;
    private int hits = 0;
    private int misses = 0;

    private LinkedHashMap<String, DNSEntry> cache =
            new LinkedHashMap<String, DNSEntry>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> e) {
                    return size() > capacity;
                }
            };

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.expired()) {
                hits++;
                System.out.println("Cache HIT -> " + entry.ip);
                return entry.ip;
            } else {
                cache.remove(domain);
                System.out.println("Cache EXPIRED");
            }
        }

        misses++;
        String ip = queryDNS(domain);
        cache.put(domain, new DNSEntry(domain, ip, 300000));
        System.out.println("Cache MISS -> " + ip);
        return ip;
    }

    private String queryDNS(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public void getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0) / total;
        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) throws Exception {
        week1week2 dns = new week1week2();

        dns.resolve("google.com");
        dns.resolve("google.com");

        Thread.sleep(2000);

        dns.resolve("google.com");

        dns.getCacheStats();
    }
}