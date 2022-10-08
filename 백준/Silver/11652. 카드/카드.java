import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            long num = scan.nextLong();
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        System.out.println(map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey()
        );
    }
}