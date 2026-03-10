import java.util.*;

public class week1week2 {
    static class Transaction {
        int id;
        int amount;
        String merchant;
        String time;

        Transaction(int id, int amount, String merchant, String time) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.time = time;
        }
    }

    public List<int[]> findTwoSum(List<Transaction> transactions, int target) {
        Map<Integer, Transaction> complementMap = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : transactions) {
            int complement = target - t.amount;
            if (complementMap.containsKey(complement)) {
                result.add(new int[]{complementMap.get(complement).id, t.id});
            }
            complementMap.put(t.amount, t);
        }
        return result;
    }

    public void detectDuplicates(List<Transaction> transactions) {
        Map<Integer, List<Transaction>> amountMap = new HashMap<>();

        for (Transaction t : transactions) {
            amountMap.computeIfAbsent(t.amount, k -> new ArrayList<>()).add(t);
        }

        for (Map.Entry<Integer, List<Transaction>> entry : amountMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("Duplicate amount: " + entry.getKey());
                for (Transaction tx : entry.getValue()) {
                    System.out.println("  Transaction ID: " + tx.id + ", Merchant: " + tx.merchant);
                }
            }
        }
    }

    public static void main(String[] args) {
        week1week2 q = new week1week2();
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, 50, "Amazon", "10:00"),
                new Transaction(2, 30, "Flipkart", "10:05"),
                new Transaction(3, 20, "Amazon", "10:10"),
                new Transaction(4, 50, "Myntra", "10:15")
        );

        System.out.println("Two-sum results:");
        for (int[] pair : q.findTwoSum(transactions, 50)) {
            System.out.println(Arrays.toString(pair));
        }

        System.out.println("\nDuplicate detection:");
        q.detectDuplicates(transactions);
    }
}