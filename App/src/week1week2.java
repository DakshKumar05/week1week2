import java.util.*;

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefillTime;
    int refillRate;

    TokenBucket(int maxTokens, int refillRate) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();
    }

    void refill() {
        long now = System.currentTimeMillis();
        long elapsed = (now - lastRefillTime) / 1000;
        int refill = (int) (elapsed * refillRate);
        if (refill > 0) {
            tokens = Math.min(maxTokens, tokens + refill);
            lastRefillTime = now;
        }
    }

    boolean allowRequest() {
        refill();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}

public class week1week2 {

    private HashMap<String, TokenBucket> clients = new HashMap<>();
    private int limit = 1000;

    public synchronized void checkRateLimit(String clientId) {
        clients.putIfAbsent(clientId, new TokenBucket(limit, limit / 3600));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            System.out.println("Allowed (" + bucket.tokens + " requests remaining)");
        } else {
            System.out.println("Denied (0 requests remaining)");
        }
    }

    public void getRateLimitStatus(String clientId) {
        if (!clients.containsKey(clientId)) return;

        TokenBucket bucket = clients.get(clientId);
        int used = bucket.maxTokens - bucket.tokens;

        System.out.println("used: " + used + ", limit: " + bucket.maxTokens);
    }

    public static void main(String[] args) {

        week1week2 limiter = new week1week2();

        limiter.checkRateLimit("abc123");
        limiter.checkRateLimit("abc123");
        limiter.checkRateLimit("abc123");

        limiter.getRateLimitStatus("abc123");
    }
}