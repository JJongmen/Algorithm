import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] suffix = {1, 3, 7, 9};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        StringBuilder result = new StringBuilder();
        Queue<Integer> que = new LinkedList<>(List.of(2, 3, 5, 7));
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            if (cur.toString().length() == N) {
                result.append(cur).append("\n");
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nxt = 10 * cur + suffix[i];
                if (isPrime(nxt)) {
                    que.offer(nxt);
                }
            }
        }

        System.out.println(result);
    }

    private static boolean isPrime(int num) {
        if (num % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}