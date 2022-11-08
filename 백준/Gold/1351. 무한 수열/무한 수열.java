import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static long N, P, Q;
    static Map<Long, Long> memo = new HashMap<>();
    static {
        memo.put(0L, 1L);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextLong();
        P = scan.nextLong();
        Q = scan.nextLong();
        System.out.println(recur(N));
    }

    private static long recur(long n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long value = recur(Math.floorDiv(n, P)) + recur(Math.floorDiv(n, Q));
        memo.put(n, value);
        return value;
    }
}