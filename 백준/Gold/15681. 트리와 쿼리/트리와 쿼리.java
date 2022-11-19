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
    static int N, R, Q, U, V;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] cnts = new int[100003];
    static {
        for (int i = 0; i <= 100000; i++) {
            graph.add(new LinkedList<>());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }
        dfs(R, 0);
        while (Q-- > 0) {
            bw.write(cnts[Integer.parseInt(br.readLine())] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int cur, int p) {
        int cnt = 1;
        for (Integer nxt : graph.get(cur)) {
            if (nxt == p) continue;
            cnt += dfs(nxt, cur);
        }
        return cnts[cur] = cnt;
    }
}