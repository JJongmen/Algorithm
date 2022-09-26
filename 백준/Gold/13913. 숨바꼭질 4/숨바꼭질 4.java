import java.util.*;

public class Main {
    static int[] visit;
    static int[] prev;
    static int K;
    static Queue<Integer> que;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        K = scan.nextInt();
        if (K <= N) {
            System.out.println(N - K);
            StringBuilder sb = new StringBuilder();
            for (int i = N; i >= K; i--) {
                sb.append(i);
                sb.append(' ');
            }
            System.out.println(sb);
            return;
        }
        visit = new int[100001];
        prev = new int[100001];
        Arrays.fill(visit, -1);
        Arrays.fill(prev, -1);
        que = new LinkedList<>();
        visit[N] = 0;
        que.offer(N);
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (move(cur, cur - 1)) return;
            if (move(cur, cur + 1)) return;
            if (move(cur, cur * 2)) return;
        }
    }

    private static boolean move(int cur, int next) {
        if (next < 0 || next > 100000 || visit[next] != -1) return false;
        visit[next] = visit[cur] + 1;
        prev[next] = cur;
        if (next == K) {
            System.out.println(visit[next]);

            Stack<Integer> stack = new Stack<>();
            stack.push(next);
            int temp = prev[next];
            while (temp != -1) {
                stack.push(temp);
                temp = prev[temp];
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(' ');
            }
            System.out.println(sb);
            return true;
        }
        que.offer(next);
        return false;
    }
}