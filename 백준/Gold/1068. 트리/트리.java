import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] parents = new int[50];
    static int start;
    static int remove;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            parents[i] = p;
            if (p == -1) {
                start = i;
            } else {
                graph.get(p).add(i);
            }
        }
        remove = Integer.parseInt(br.readLine());
        if (start == remove) {
            System.out.println(0);
            return;
        }
        graph.get(parents[remove]).remove(Integer.valueOf(remove));
        System.out.println(dfs(start));
    }

    private static int dfs(int cur) {
        if (graph.get(cur).isEmpty()) {
            return 1;
        }
        int cnt = 0;
        for (Integer nxt : graph.get(cur)) {
            cnt += dfs(nxt);
        }
        return cnt;
    }
}