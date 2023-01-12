import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, M, P;
    static List<Integer>[] graph;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            graph = new List[M + 1];
            for (int i = 1; i <= M; i++) {
                graph[i] = new ArrayList<>();
            }
            inDegree = new int[M + 1];

            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph[A].add(B);
                inDegree[B]++;
            }

            Queue<Integer> que = new LinkedList<>();
            int[] orders = new int[M + 1];
            boolean[] moreOne = new boolean[M + 1];
            for (int i = 1; i <= M; i++) {
                if (inDegree[i] == 0) {
                    que.offer(i);
                    orders[i] = 1;
                }
            }

            while (!que.isEmpty()) {
                Integer cur = que.poll();
                for (Integer nxt : graph[cur]) {
                    if (orders[nxt] < orders[cur]) {
                        orders[nxt] = orders[cur];
                        moreOne[nxt] = false;
                    } else if (orders[nxt] == orders[cur] && !moreOne[nxt]) {
                        orders[nxt]++;
                        moreOne[nxt] = true;
                    }
                    if (--inDegree[nxt] == 0) {
                        que.offer(nxt);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(K).append(" ").append(orders[M]).append("\n");
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}