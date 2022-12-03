import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Position> positions = new ArrayList<>();
    static List<Path> paths = new ArrayList<>();
    static int[] parents = new int[1001];

    static {
        positions.add(null);
        for (int i = 1; i <= 1000; i++) {
            parents[i] = i;
        }
    }

    static class Position {
        int x, y;   // 좌표

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Position other) {
            return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
        }
    }

    static class Path {
        double dist;   // 거리
        int x, y;   // 연결된 우주신들의 번호

        public Path(double dist, int x, int y) {
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            positions.add(new Position(X, Y));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            union(num1, num2);
        }

        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                paths.add(new Path(positions.get(i).getDistance(positions.get(j)), i, j));
            }
        }
        paths.sort(Comparator.comparingDouble(o -> o.dist));

        double result = 0;
        int cnt = M;
        for (Path path : paths) {
            if (!union(path.x, path.y)) continue;
            result += path.dist;
            if (++cnt == N - 1) break;
        }
        System.out.printf("%.2f", result);
    }

    /**
     * 같은 그룹으로 합친다.
     * 서로 다른 그룹이였다면 true 반환
     * 같은 그룹이였다면 false 반환
     */
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