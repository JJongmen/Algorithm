import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<List<Integer>> heavierGraph = new LinkedList<>();
    static List<List<Integer>> lighterGraph = new LinkedList<>();
    static boolean[] visit;
    static int[] heavierCnts = new int[100];
    static int[] lighterCnts = new int[100];
    static int result;
    static {
        for (int i = 0; i <= 99; i++) {
            heavierGraph.add(new LinkedList<>());
            lighterGraph.add(new LinkedList<>());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            heavierGraph.get(B).add(A);
            lighterGraph.get(A).add(B);
        }

        int mid = N / 2;
        for (int num = 1; num <= N; num++) {
            visit = new boolean[N + 1];
            bfs(num, heavierGraph, lighterCnts);
            visit = new boolean[N + 1];
            bfs(num, lighterGraph, heavierCnts);

        }
        for (int num = 1; num <= N; num++) {
            if (heavierCnts[num] > mid || lighterCnts[num] > mid) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static void bfs(int start, List<List<Integer>> graph, int[] cnts) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visit[start] = true;
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph.get(cur)) {
                if (visit[nxt]) continue;
                que.offer(nxt);
                visit[nxt] = true;
                cnts[nxt]++;
            }
        }
    }
}