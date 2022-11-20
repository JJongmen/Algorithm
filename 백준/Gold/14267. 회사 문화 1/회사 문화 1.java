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
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] praiseCnts = new int[100001];
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
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int superior = Integer.parseInt(st.nextToken());
            if (superior == -1) continue;
            graph.get(superior).add(i);
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            praiseCnts[i] += w;
        }
        dfs(1, 0);
        for (int i = 1; i <= N; i++) {
            bw.write(praiseCnts[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cur, int w) {
        praiseCnts[cur] += w;
        for (Integer nxt : graph.get(cur)) {
            dfs(nxt, praiseCnts[cur]);
        }
    }
}