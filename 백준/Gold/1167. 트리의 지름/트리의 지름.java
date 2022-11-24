import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static List<List<Node>> tree = new ArrayList<>();
    static int root = 1;
    static long maxDist = 0;

    static {
        for (int i = 0; i <= 100000; i++) {
            tree.add(new LinkedList<>());
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
        input();
        dfs(root, 0, 0);
        dfs(root, 0, 0);
        System.out.println(maxDist);
    }

    private static void dfs(int cur, int p, int total) {
        if (maxDist < total) {
            root = cur;
            maxDist = total;
        }
        for (Node nxt : tree.get(cur)) {
            if (nxt.num == p) {
                continue;
            }
            dfs(nxt.num, cur, total + nxt.dist);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }
                int dist = Integer.parseInt(st.nextToken());
                tree.get(parent).add(new Node(num, dist));
            }
        }
    }
}