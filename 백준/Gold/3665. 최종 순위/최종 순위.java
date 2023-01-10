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
    static int[] arr;
    static List<Integer>[] graph;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n];
            inDegree = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                inDegree[arr[i]] = i;
            }

            graph = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    graph[arr[i]].add(arr[j]);
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (graph[a].contains(b)) {
                    graph[a].remove(Integer.valueOf(b));
                    graph[b].add(a);
                    inDegree[b]--;
                    inDegree[a]++;
                } else {
                    graph[b].remove(Integer.valueOf(a));
                    graph[a].add(b);
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }

            Queue<Integer> que = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    que.offer(i);
                }
            }

            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            while (!que.isEmpty()) {
                Integer cur = que.poll();
                sb.append(cur).append(" ");
                cnt++;
                for (Integer nxt : graph[cur]) {
                    if (--inDegree[nxt] == 0) {
                        que.offer(nxt);
                    }
                }
            }
            sb.append("\n");
            bw.write(cnt == n ? sb.toString() : "IMPOSSIBLE\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}