import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX = 100000;

    static int[] want = new int[MAX + 1];
    static boolean[] check = new boolean[MAX + 1];
    static boolean[] visit = new boolean[MAX + 1];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                want[i] = Integer.parseInt(st.nextToken());
            }

            int cnt = 0;
            Arrays.fill(check, 1, n + 1, false);
            for (int i = 1; i <= n; i++) {
                if (check[i]) continue;
                Arrays.fill(visit, 1, n + 1, false);
                cnt += dfs(i);
            }
            result.append(n - cnt).append("\n");
        }
        System.out.println(result);
    }

    private static int dfs(int cur) {
        if (check[cur]) {
            if (visit[cur]) {
                int cnt = 1;
                int tmp = want[cur];
                while (tmp != cur) {
                    cnt++;
                    tmp = want[tmp];
                }
                return cnt;
            }
            return 0;
        }
        check[cur] = true;
        visit[cur] = true;
        return dfs(want[cur]);
    }
}