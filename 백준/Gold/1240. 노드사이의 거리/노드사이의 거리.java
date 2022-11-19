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
    static int N, M;
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visit;
    static {
        for (int i = 0; i <= 1000; i++) {
            graph.add(new LinkedList<>());
        }
    }

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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, D));
            graph.get(B).add(new Node(A, D));
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            visit = new boolean[N + 1];
            bw.write(dfs(A, B) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int start, int end) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(start, 0));
        visit[start] = true;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (Node nxt : graph.get(cur.num)) {
                if (visit[nxt.num]) continue;
                int nxtDist = cur.dist + nxt.dist;
                if (end == nxt.num) return nxtDist;
                que.offer(new Node(nxt.num, nxtDist));
                visit[nxt.num] = true;
            }
        }
        return -1;
    }
}