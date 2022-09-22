import java.io.*;
import java.util.*;

public class Main {

    private static class Node {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            while (d-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }

            int[] times = new int[n + 1];
            Arrays.fill(times, Integer.MAX_VALUE);
            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
            pq.add(new Node(c, 0));
            times[c] = 0;
            while (!pq.isEmpty()) {
                Node current = pq.poll();
                if (times[current.to] < current.time) {
                    continue;
                }
                for (Node next : graph.get(current.to)) {
                    if (times[next.to] > next.time + current.time) {
                        times[next.to] = next.time + current.time;
                        pq.offer(new Node(next.to, times[next.to]));
                    }
                }
            }

            int cnt = 0;
            int maxTime = 0;
            for (int time : times) {
                if (time != Integer.MAX_VALUE) {
                    cnt++;
                    maxTime = Math.max(maxTime, time);
                }
            }
            bw.write(cnt + " " + maxTime + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}