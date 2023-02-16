import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] works;
    static boolean[] visit;
    static int[] worker;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        works = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            works[i] = new LinkedList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int workCnt = Integer.parseInt(st.nextToken());
            while (workCnt-- > 0) {
                works[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        worker = new int[M + 1];
        int result = 0;
        for (int i = 1; i <= N; i++) {
            visit = new boolean[M + 1];
            if (dfs(i)) result++;
        }
        System.out.println(result);
    }

    private static boolean dfs(int curWorker) {
        for (Integer work : works[curWorker]) {
            // 이미 매칭때 방문했다면 다시 방문하지 않음
            if (visit[work]) continue;
            visit[work] = true;
            // 아무도 담당하는 일이 아니거나 기존 담당자를 밀어내고 일을 담당할 수 있으면 true
            if (worker[work] == 0 || dfs(worker[work])) {
                worker[work] = curWorker;
                return true;
            }
        }
        return false;
    }
}