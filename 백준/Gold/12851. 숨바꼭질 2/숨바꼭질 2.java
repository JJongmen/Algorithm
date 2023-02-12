import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static final int INF = 1000000;
    public static final int MAX = 100000;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();

        if (N >= K) {
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        int[] counts = new int[MAX + 1];
        int[] times = new int[MAX + 1];
        Arrays.fill(times, INF);

        Queue<Integer> que = new LinkedList<>();
        que.offer(N);
        times[N] = 0;
        counts[N] = 1;

        while (!que.isEmpty()) {
            Integer cur = que.poll();
            if (times[cur] > times[K]) break;

            int[] nexts = {cur - 1, cur + 1, cur * 2};
            for (int nxt : nexts) {
                if (0 <= nxt && nxt <= MAX) {
                    if (times[nxt] == INF) {
                        que.offer(nxt);
                        times[nxt] = times[cur] + 1;
                    }
                    if (times[nxt] > times[cur]) {
                        counts[nxt] += counts[cur];
                    }
                }
            }
        }
        System.out.println(times[K]);
        System.out.println(counts[K]);
    }
}