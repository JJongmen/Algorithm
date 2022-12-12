import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000000000;
    static int N, M;
    static List<Node>[] graph = new List[1001];
    static int[] d = new int[1001];
    static int[] pre = new int[1001];

    static class Node {
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Arrays.fill(d, 1, N + 1, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.num] != cur.dist) continue;
            for (Node nxt : graph[cur.num]) {
                int nxtDist = d[cur.num] + nxt.dist;
                if (d[nxt.num] <= nxtDist)  continue;
                d[nxt.num] = nxtDist;
                pq.offer(new Node(nxt.num, nxtDist));
                pre[nxt.num] = cur.num;
            }
        }

        int cur = end;
        Stack<Integer> stack = new Stack<>();
        stack.push(cur);
        while (cur != start) {
            cur = pre[cur];
            stack.push(cur);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(d[end] + "\n");
        bw.write(stack.size() + "\n");
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}