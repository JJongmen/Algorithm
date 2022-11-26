import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y, K;
    static List<Integer>[] graph = new List[101];
    static int[] inDegree = new int[101];
    static int[][] parts = new int[101][101];
    static int[][] totalParts = new int[101][101];
    static List<Integer> basicParts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph[i] = new LinkedList<>();
            basicParts.add(i);
        }
        M = Integer.parseInt(br.readLine());
        Set<Integer> middleParts = new HashSet<>();
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph[Y].add(X);
            parts[X][Y] = K;
            inDegree[X]++;
            middleParts.add(X);
        }
        basicParts.removeAll(middleParts);
        for (Integer basicPart : basicParts) {
            parts[basicPart][basicPart] = 1;
        }
    }

    private static void solve() {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.offer(i);
        }
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph[cur]) {
                for (Integer basicPart : basicParts) {
                    if (totalParts[cur][basicPart] == 0) {
                        totalParts[nxt][basicPart] += parts[nxt][cur] * parts[cur][basicPart];
                    } else {
                        totalParts[nxt][basicPart] += parts[nxt][cur] * totalParts[cur][basicPart];
                    }
                }
                if (--inDegree[nxt] == 0) que.offer(nxt);
            }
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Integer basicPart : basicParts) {
            bw.write(basicPart + " " + totalParts[N][basicPart] + "\n");
        }
        bw.flush();
        bw.close();
    }
}