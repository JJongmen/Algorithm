import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Flow> flows = new ArrayList<>();
    static int[] parents;

    static class Flow {
        int weight;
        int x, y;

        public Flow(int weight, int x, int y) {
            this.weight = weight;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (j <= i) continue;
                flows.add(new Flow(weight, i, j));
            }
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        flows.sort(Comparator.comparingInt(o -> o.weight));
        long result = 0;
        int cnt = 0;
        for (Flow flow : flows) {
            if (!is_diff_group(flow.x, flow.y)) continue;
            result += flow.weight;
            if (++cnt == N - 1) break;
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