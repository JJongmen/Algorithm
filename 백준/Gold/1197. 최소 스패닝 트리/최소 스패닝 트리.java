import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static Edge[] edges;
    static int[] parents;

    static class Edge {
        int vertex1, vertex2;
        int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new Edge[E];
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

        int result = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if (!is_diff_group(edge.vertex1, edge.vertex2)) continue;
            result += edge.weight;
            if (++cnt == V - 1) break;
        }
        System.out.println(result);
    }

    private static boolean is_diff_group(int x, int y) {
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