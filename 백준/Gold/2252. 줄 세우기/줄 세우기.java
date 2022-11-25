import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int A, B;
    static List<Integer>[] graph = new List[32001];
    static int[] inDegree = new int[32001];
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(result);
    }

    private static void solve() {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] != 0) continue;
            que.offer(i);
        }
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            result.append(cur).append(" ");
            for (Integer nxt : graph[cur]) {
                if (--inDegree[nxt] != 0) continue;
                que.offer(nxt);
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            graph[i] = new LinkedList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            inDegree[B]++;
        }
    }
}