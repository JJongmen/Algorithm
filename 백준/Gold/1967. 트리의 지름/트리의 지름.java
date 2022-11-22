import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<List<Node>> tree = new ArrayList<>();
    static boolean[] visit;
    static int maxDist;
    static int farNum;

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
        for (int i = 0; i <= N; i++) {
            tree.add(new LinkedList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            tree.get(A).add(new Node(B, D));
            tree.get(B).add(new Node(A, D));
        }

        visit = new boolean[N + 1];
        dfs(1, 0);
        visit = new boolean[N + 1];
        dfs(farNum, 0);
        System.out.println(maxDist);
    }

    private static void dfs(int cur, int dist) {
        visit[cur] = true;
        if (maxDist < dist) {
            maxDist = dist;
            farNum = cur;
        }
        for (Node nxt : tree.get(cur)) {
            if (visit[nxt.num]) continue;
            dfs(nxt.num, dist + nxt.dist);
        }
    }
}