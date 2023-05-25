import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000;
    static int N;
    static int[] population = new int[11];
    static List<Integer>[] graph = new List[11];
    static boolean[] team = new boolean[11];    // T, F 팀으로 나눈다. A는 항상 T팀
    static boolean[] visit = new boolean[11];
    static int totalPopulation;
    static int areaPopulation;
    static int visitCnt;
    static int minDiff = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
        }

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            while (cnt-- > 0) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        team[1] = true;
        brute(2, 1);
        System.out.println(minDiff == INF ? -1 : minDiff);
    }

    private static void brute(int idx, int areaCnt) {
        if (idx == N + 1) {
            if (areaCnt == N) return;
            areaPopulation = 0;
            visitCnt = 0;
            Arrays.fill(visit, 1, N + 1, false);
            dfs(1, true);
            if (visitCnt != areaCnt) return;
            int diff = Math.abs(totalPopulation - 2 * areaPopulation);
            visitCnt = 0;
            areaPopulation = 0;
            for (int i = 2; i <= N; i++) {
                if (team[i]) continue;
                dfs(i, false);
                break;
            }
            if (visitCnt != N - areaCnt) return;
            minDiff = Math.min(minDiff, diff);
            return;
        }
        team[idx] = true;
        brute(idx + 1, areaCnt + 1);
        team[idx] = false;
        brute(idx + 1, areaCnt);
    }

    private static void dfs(int cur, boolean teamT) {
        visit[cur] = true;
        visitCnt++;
        areaPopulation += population[cur];
        for (Integer nxt : graph[cur]) {
            if (visit[nxt] || team[nxt] != teamT) continue;
            dfs(nxt, teamT);
        }
    }
}