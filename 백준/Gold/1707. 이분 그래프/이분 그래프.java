import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final String YES = "YES";
    public static final String NO = "NO";
    static int K, V, E;
    static int u, v;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] color;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            init();
            while (E-- > 0) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            String answer = YES;
            for (int num = 1; num <= V; num++) {
                if (bfs(num)) continue;
                answer = NO;
                break;
            }
            System.out.println(answer);
        }
    }

    private static boolean bfs(int start) {
        if (visit[start]) return true;
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visit[start] = true;
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph.get(cur)) {
                if (visit[nxt] && color[nxt] == color[cur]) return false;
                if (visit[nxt]) continue;
                que.offer(nxt);
                visit[nxt] = true;
                color[nxt] = !color[cur];
            }
        }
        return true;
    }

    private static void init() {
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new LinkedList<>());
        }
        color = new boolean[V + 1];
        visit = new boolean[V + 1];
    }
}