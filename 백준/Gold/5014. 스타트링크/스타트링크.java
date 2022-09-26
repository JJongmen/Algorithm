import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int F = scan.nextInt();
        int S = scan.nextInt();
        int G = scan.nextInt();
        int U = scan.nextInt();
        int D = scan.nextInt();
        int[] dist = new int[1000001];
        Arrays.fill(dist, -1);
        Queue<Integer> que = new LinkedList<>();
        dist[S] = 0;
        que.offer(S);
        while (!que.isEmpty()) {
            int cur = que.poll();
            int down = cur - D;

            if (1 <= down && down <= F && dist[down] == -1) {
                dist[down] = dist[cur] + 1;
                if (down == G) break;
                que.offer(down);
            }
            int up = cur + U;
            if (1 <= up && up <= F && dist[up] == -1) {
                dist[up] = dist[cur] + 1;
                if (up == G) break;
                que.offer(up);
            }
        }
        System.out.println(dist[G] == -1 ? "use the stairs" : dist[G]);
    }
}