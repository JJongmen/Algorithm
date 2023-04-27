import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] graph = new List[1001];
    static int[] owner = new int[1001];
    static boolean[] visit = new boolean[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= M; i++) {
                graph[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                for (int j = a; j <= b; j++) {
                    graph[i].add(j);
                }
            }

            Arrays.fill(owner, 1, N + 1, 0);
            int cnt = 0;
            for (int i = 1; i <= M; i++) {
                Arrays.fill(visit, 1, N + 1, false);
                if (matching(i)) cnt++;
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean matching(int cur) {
        for (Integer nxt : graph[cur]) {
            if (visit[nxt]) continue;
            visit[nxt] = true;
            if (owner[nxt] == 0 || matching(owner[nxt])) {
                owner[nxt] = cur;
                return true;
            }
        }
        return false;
    }
}