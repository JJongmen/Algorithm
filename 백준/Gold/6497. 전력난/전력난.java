import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Road[] roads;
    static int[] p;

    static class Road {
        int x, y;
        int dist;

        public Road(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (M == 0 && N == 0) break;
            roads = new Road[N];
            int total = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                roads[i] = new Road(x, y, z);
                total += z;
            }

            p = new int[M];
            for (int i = 1; i < M; i++) {
                p[i] = i;
            }

            Arrays.sort(roads, Comparator.comparingInt(o -> o.dist));
            int min = 0;
            int cnt =0;
            for (Road road : roads) {
                if (!is_diff_group(road.x, road.y)) continue;
                min += road.dist;
                if (++cnt == M - 1) break;
            }
            bw.write(total - min + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean is_diff_group(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            p[y] = x;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if (x == p[x]) {
            return x;
        }
        return p[x] = find(p[x]);
    }
}