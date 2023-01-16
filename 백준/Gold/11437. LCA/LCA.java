import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] p;
    static int[] d;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            graph[B].add(A);
        }

        p = new int[N + 1];
        d = new int[N + 1];
        visit = new boolean[N + 1];
        dfs(1, 0);

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            bw.write(lca(A, B) + "\n");
        }
        bw.flush();
    }

    private static int lca(int a, int b) {
        while (d[a] != d[b]) {
            if (d[a] > d[b]) {
                a = p[a];
            } else {
                b = p[b];
            }
        }

        while (a != b) {
            a = p[a];
            b = p[b];
        }
        return a;
    }

    private static void dfs(int node, int depth) {
        d[node] = depth;
        visit[node] = true;
        for (Integer nxt : graph[node]) {
            if (visit[nxt]) continue;
            p[nxt] = node;
            dfs(nxt, depth + 1);
        }
    }
}