import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static List<Cable> cables = new ArrayList<>();
    static boolean[] isPowered = new boolean[1001];
    static int[] parents = new int[1001];
    static {
        for (int i = 1; i <= 1000; i++) {
            parents[i] = i;
        }
    }

    static class Cable {
        int x, y;
        int cost;

        public Cable(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            isPowered[Integer.parseInt(st.nextToken())] = true;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            cables.add(new Cable(u, v, w));
        }

        cables.sort(Comparator.comparingInt(o -> o.cost));
        int result = 0;
        int cnt = 0;
        for (Cable cable : cables) {
            if (!union(cable.x, cable.y)) continue;
            result += cable.cost;
            if (++cnt == N - K) break;
        }
        System.out.println(result);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (isPowered[x] && isPowered[y]) {
                return false;
            } else if (!isPowered[x] && isPowered[y]) {
                parents[x] = y;
            } else {
                parents[y] = x;
            }
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