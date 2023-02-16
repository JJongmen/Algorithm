import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] works;
    static int[] worker;
    static int[] workCnt;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        works = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            works[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int workCnt = Integer.parseInt(st.nextToken());
            while (workCnt-- > 0) {
                works[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        worker = new int[M + 1];
        visit = new boolean[M + 1];
        workCnt = new int[N + 1];
        int result = 0;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visit, 1, M + 1, false);
            if (dfs(i)) result++;
            Arrays.fill(visit, 1, M + 1, false);
            if (dfs(i)) result++;
        }
        System.out.println(result);
    }

    private static boolean dfs(int curWorker) {
        for (Integer work : works[curWorker]) {
            if (visit[work]) continue;
            visit[work] = true;
            if (worker[work] == 0 || dfs(worker[work])) {
                worker[work] = curWorker;
                return true;
            }
        }
        return false;
    }
}