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
    static int size;
    static List<Integer>[] graph;
    static int[][] p;
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

        size = (int) Math.ceil(Math.log(N) / Math.log(2));
        p = new int[N + 1][size];
        d = new int[N + 1];
        visit = new boolean[N + 1];
        dfs(1, 0);
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= N; j++) {
                p[j][i] = p[p[j][i - 1]][i - 1];
            }
        }

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
        if (d[a] < d[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = d[a] - d[b];
        for (int i = size - 1; i >= 0; i--) {
            int jump = 1 << i;
            if ((diff & jump) > 0) a = p[a][i];
        }

        if (a == b) return a;

        for (int i = size - 1; i >= 0; i--) {
            if (p[a][i] == 0) continue;
            if (p[a][i] == p[b][i]) continue;
            a = p[a][i];
            b = p[b][i];
        }
        return p[a][0];
    }

    private static void dfs(int node, int depth) {
        d[node] = depth;
        visit[node] = true;
        for (Integer nxt : graph[node]) {
            if (visit[nxt]) continue;
            p[nxt][0] = node;
            dfs(nxt, depth + 1);
        }
    }
}