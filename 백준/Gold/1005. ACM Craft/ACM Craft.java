import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, N, K, X, Y, W;
    static int[] times = new int[1001];
    static int[] totalTimes;
    static List<Integer>[] graph = new List[1001];
    static int[] inDegree = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input(br);
            solve();
            bw.write(totalTimes[W] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            graph[i] = new LinkedList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            graph[X].add(Y);
            inDegree[Y]++;
        }
        W = Integer.parseInt(br.readLine());
    }

    private static void solve() {
        totalTimes = new int[N + 1];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
                totalTimes[i] = times[i];
            }
        }
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph[cur]) {
                totalTimes[nxt] = Math.max(totalTimes[nxt], totalTimes[cur] + times[nxt]);
                if (--inDegree[nxt] == 0) {
                    que.offer(nxt);
                }
            }
        }
    }
}