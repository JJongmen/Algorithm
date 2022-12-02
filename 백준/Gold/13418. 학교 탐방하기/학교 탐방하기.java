import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Road> roads = new ArrayList<>();
    static int[] parents;

    static class Road {
        int x, y;
        int isDownHill;

        public Road(int x, int y, int isDownHill) {
            this.x = x;
            this.y = y;
            this.isDownHill = isDownHill;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            roads.add(new Road(A, B, C));
        }



        // 오르막길이 리스트의 앞에 오도록 정렬
        roads.sort(Comparator.comparingInt(o -> o.isDownHill));

        // 최악의 경로
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        int worstCnt = 0;
        int cnt = 0;
        for (Road road : roads) {
            if (!is_diff_group(road.x, road.y)) continue;
            if (road.isDownHill == 0) worstCnt++;
            if (++cnt == N) break;
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        int bestCnt = 0;
        cnt = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            Road road = roads.get(i);
            if (!is_diff_group(road.x, road.y)) continue;
            if (road.isDownHill == 0) bestCnt++;
            if (++cnt == N) break;
        }

        System.out.println(worstCnt * worstCnt - bestCnt * bestCnt);
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