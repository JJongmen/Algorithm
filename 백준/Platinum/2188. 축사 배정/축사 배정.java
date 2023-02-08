import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] matched;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            for (int j = 0; j < S; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        matched = new int[M + 1];
        visit = new boolean[M + 1];
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visit, false);
            if (dfs(i)) cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean dfs(int cur) {
        for (Integer nxt : graph[cur]) {
            if (visit[nxt]) continue;
            visit[nxt] = true;
            if (matched[nxt] == 0 || dfs(matched[nxt])) {
                matched[nxt] = cur;
                return true;
            }
        }
        return false;
    }
}