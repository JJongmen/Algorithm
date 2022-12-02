import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;
    static List<Road> roads = new ArrayList<>();

    static class Road {
        int cost;
        int x, y;

        public Road(int cost, int x, int y) {
            this.cost = cost;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            roads.add(new Road(C, A, B));
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        roads.sort(Comparator.comparingInt(o -> o.cost));
        int result = 0;
        int cnt = 0;
        for (Road road : roads) {
            if (!is_diff_gorup(road.x, road.y)) continue;
            result += road.cost;
            if (++cnt == N - 2) break;
        }
        System.out.println(result);
    }

    private static boolean is_diff_gorup(int x, int y) {
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