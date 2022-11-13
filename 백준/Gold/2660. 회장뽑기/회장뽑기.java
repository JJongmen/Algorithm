import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> scores = new ArrayList<>();
    static int minScore = 100;
    static {
        for (int i = 0; i <= 50; i++) {
            graph.add(new LinkedList<>());
            scores.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            int score = bfs(i);
            minScore = Math.min(minScore, score);
            scores.get(score).add(i);
        }

        List<Integer> candidates = scores.get(minScore);
        Collections.sort(candidates);
        bw.write(minScore + " " + candidates.size() + "\n");
        for (Integer candidate : candidates) {
            bw.write(candidate + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int start) {
        int score = 0;
        Queue<Integer> que = new LinkedList<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        que.offer(start);
        dist[start] = 0;
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph.get(cur)) {
                if (dist[nxt] != -1) continue;
                que.offer(nxt);
                dist[nxt] = dist[cur] + 1;
                score = Math.max(score, dist[nxt]);
            }
        }
        return score;
    }
}