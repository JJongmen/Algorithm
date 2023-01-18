import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] comeCnt;
    static boolean[] visit;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        comeCnt = new int[N + 1];
        cnt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            dfs(i, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(N - comeCnt[i] - cnt[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int node) {
        visit[node] = true;
        comeCnt[node]++;
        for (Integer nxt : graph[node]) {
            if (visit[nxt]) continue;
            cnt[start]++;
            dfs(start, nxt);
        }
    }
}