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
    static List<Integer>[] graph;
    static int[] inDegree;
    static int[] complete;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            inDegree[B]++;
        }

        complete = new int[N + 1];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
                complete[i] = 1;
            }
        }

        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph[cur]) {
                complete[nxt] = Math.max(complete[nxt], complete[cur] + 1);
                if (--inDegree[nxt] == 0) que.offer(nxt);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            result.append(complete[i]).append(" ");
        }
        System.out.println(result);
    }
}