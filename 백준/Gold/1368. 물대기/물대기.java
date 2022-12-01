import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Node> graph = new ArrayList<>();
    static int[] parents;

    static class Node {
        int weight;
        int x, y;

        public Node(int weight, int x, int y) {
            this.weight = weight;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        graph.sort(Comparator.comparingInt(o -> o.weight));
        int result = 0;
        int cnt = 0;
        for (Node node : graph) {
            if (!union(node.x, node.y)) continue;
            result += node.weight;
            if (++cnt == N) break;
        }
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            int weight = Integer.parseInt(br.readLine());
            graph.add(new Node(weight, 0, i));
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (j > i) {
                    graph.add(new Node(weight, i, j));
                }
            }
        }
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parents[y] = x;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
}