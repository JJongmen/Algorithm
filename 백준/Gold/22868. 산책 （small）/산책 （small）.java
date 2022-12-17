import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 100000;
    static int N, M;
    static List<Integer>[] graph;
    static int[] d;
    static List<Integer>[] pre;
    static int[] reverseRoute;
    static PriorityQueue<List<Integer>> routes = new PriorityQueue<>((o1, o2) -> {
        int idx = 0;
        while (o1.get(idx).equals(o2.get(idx))) {
            idx++;
        }
        return o1.get(idx).compareTo(o2.get(idx));
    });
    static boolean[] visit;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            graph[B].add(A);
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        pre = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            pre[i] = new ArrayList<>();
        }
        getDist(S, E);
        checkShortRoute(S, E);
        getDist(E, S);
        System.out.println(result);
    }

    private static void getDist(int start, int end) {
        d = new int[N + 1];
        Arrays.fill(d, INF);
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        d[start] = 0;
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph[cur]) {
                if (visit[nxt]) continue;
                int nxtDist = d[cur] + 1;
                if (d[nxt] < nxtDist) continue;
                if (d[nxt] > nxtDist) {
                    pre[nxt].clear();
                    que.offer(nxt);
                }
                d[nxt] = nxtDist;
                pre[nxt].add(cur);
            }
        }
        result += d[end];
    }

    private static void checkShortRoute(int S, int E) {
        reverseRoute = new int[N];
        findRoute(0, E, S);
        List<Integer> route = routes.poll();
        for (int i = 1; i < route.size() - 1; i++) {
            Integer cur = route.get(i);
            visit[cur] = true;
        }
    }

    private static void findRoute(int idx, int cur, int dest) {
        if (cur == dest) {
            reverseRoute[idx] = cur;
            List<Integer> route = new ArrayList<>();
            for (int i = idx; i >= 0; i--) {
                route.add(reverseRoute[i]);
            }
            routes.add(route);
            return;
        }
        reverseRoute[idx] = cur;
        for (Integer back : pre[cur]) {
            findRoute(idx + 1, back, dest);
        }
    }
}