import java.util.*;

public class week1week2 {
    private HashMap<String, Integer> stock = new HashMap<>();
    private LinkedHashMap<String, Queue<Integer>> waitingList = new LinkedHashMap<>();

    public week1week2() {
        stock.put("IPHONE15_256GB", 100);
    }

    public synchronized void checkStock(String productId) {
        if (stock.containsKey(productId)) {
            System.out.println(stock.get(productId) + " units available");
        } else {
            System.out.println("Product not found");
        }
    }

    public synchronized void purchaseItem(String productId, int userId) {
        if (!stock.containsKey(productId)) {
            System.out.println("Product not found");
            return;
        }

        int currentStock = stock.get(productId);

        if (currentStock > 0) {
            stock.put(productId, currentStock - 1);
            System.out.println("Success, " + (currentStock - 1) + " units remaining");
        } else {
            waitingList.putIfAbsent(productId, new LinkedList<>());
            waitingList.get(productId).add(userId);
            int position = waitingList.get(productId).size();
            System.out.println("Added to waiting list, position #" + position);
        }
    }

    public static void main(String[] args) {
        week1week2 manager = new week1week2();

        manager.checkStock("IPHONE15_256GB");
        manager.purchaseItem("IPHONE15_256GB", 12345);
        manager.purchaseItem("IPHONE15_256GB", 67890);

        for (int i = 0; i < 100; i++) {
            manager.purchaseItem("IPHONE15_256GB", 10000 + i);
        }

        manager.purchaseItem("IPHONE15_256GB", 99999);
    }
}