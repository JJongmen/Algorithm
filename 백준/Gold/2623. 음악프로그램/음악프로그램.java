import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] graph = new List[1001];
    static int[] inDegree = new int[1001];
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            graph[i] = new LinkedList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int prevSinger = Integer.parseInt(st.nextToken());
            for (int i = 0; i < count - 1; i++) {
                int singer = Integer.parseInt(st.nextToken());
                graph[prevSinger].add(singer);
                inDegree[singer]++;
                prevSinger = singer;
            }
        }
    }

    private static void solve() {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.offer(i);
        }
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            result.add(cur);
            for (Integer nxt : graph[cur]) {
                if (--inDegree[nxt] == 0) que.offer(nxt);
            }
        }
    }

    private static void output() {
        if (result.size() != N) {
            System.out.println(0);
            return;
        }
        StringBuilder answer = new StringBuilder();
        for (Integer singer : result) {
            answer.append(singer).append("\n");
        }
        System.out.println(answer);
    }
}