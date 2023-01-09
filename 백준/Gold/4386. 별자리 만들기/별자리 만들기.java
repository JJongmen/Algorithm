import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static double[][] points;
    static int[] parents;
    static StarRoad[] roads;

    static class StarRoad {
        int a, b;
        double dist;

        public StarRoad(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        points = new double[N + 1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }

        roads = new StarRoad[N * N / 2];
        int idx = 0;
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                roads[idx++] = new StarRoad(i, j, getDist(i, j));
            }
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        Arrays.sort(roads, 0, idx, Comparator.comparingDouble(o -> o.dist));

        double cost = 0;
        int cnt = 0;
        for (StarRoad road : roads) {
            if (!union(road.a, road.b)) continue;
            cost += road.dist;
            if (++cnt == N - 1) break;
        }
        System.out.printf("%.2f", cost);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parents[b] = a;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static double getDist(int i, int j) {
        return Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
    }
}