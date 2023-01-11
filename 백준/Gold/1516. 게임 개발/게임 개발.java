import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 50000005;
    static int N;
    static List<Integer>[] graph;
    static int[] inDegree;
    static int[] times;
    static int[] totalTimes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];
        times = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;
            int need = Integer.parseInt(st.nextToken());
            while (need != -1) {
                graph[need].add(i);
                inDegree[i]++;
                need = Integer.parseInt(st.nextToken());
            }
        }

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
                if (--inDegree[nxt] == 0) que.offer(nxt);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            result.append(totalTimes[i]).append("\n");
        }
        System.out.println(result);
    }
}