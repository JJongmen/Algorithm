import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int T = 1;
    static int N, M;
    static List<List<Integer>> graph;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            init();
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph.get(A).add(B);
                graph.get(B).add(A);
            }
            int result = 0;
            for (int root = 1; root <= N; root++) {
                if (dfs(root, 0)) {
                    result++;
                }
            }
            bw.write(getMessage(result));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static String getMessage(int result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Case ")
                .append(T++)
                .append(": ")
                .append(getTreeMessage(result))
                .append("\n");
        return sb.toString();
    }

    private static void init() {
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new LinkedList<>());
        }
        visit = new boolean[N + 1];
    }

    private static String getTreeMessage(int result) {
        if (result == 0) {
            return "No trees.";
        } else if (result == 1) {
            return "There is one tree.";
        } else {
            return "A forest of " + result + " trees.";
        }
    }

    private static boolean dfs(int cur, int parent) {
        if (visit[cur]) return false;
        visit[cur] = true;
        boolean result = true;
        for (Integer nxt : graph.get(cur)) {
            if (nxt == parent) continue;
            if (visit[nxt]) {
                result = false;
                continue;
            }
            if (!dfs(nxt, cur)) {
                result = false;
            }
        }
        return result;
    }
}