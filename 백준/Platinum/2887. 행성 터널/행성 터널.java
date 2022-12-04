import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Planet> planets = new ArrayList<>();
    static List<Tunnel> tunnels = new ArrayList<>();
    static int[] parents = new int[100000];
    static {
        for (int i = 1; i < 100000; i++) {
            parents[i] = i;
        }
    }

    static class Planet {
        int x, y, z;
        int num;

        public Planet(int x, int y, int z, int num) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.num = num;
        }
    }

    static class Tunnel {
        int x, y;
        int cost;

        public Tunnel(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public Tunnel(Planet planet, Planet other) {
            x = planet.num;
            y = other.num;
            cost = Math.min(Math.abs(planet.x - other.x),
                    Math.min(Math.abs(planet.y - other.y), Math.abs(planet.z - other.z)));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets.add(new Planet(x, y, z, i));
        }

        planets.sort(Comparator.comparingInt(o -> o.x));
        for (int i = 0; i < N - 1; i++) {
            tunnels.add(new Tunnel(planets.get(i), planets.get(i + 1)));
        }
        planets.sort(Comparator.comparingInt(o -> o.y));
        for (int i = 0; i < N - 1; i++) {
            tunnels.add(new Tunnel(planets.get(i), planets.get(i + 1)));
        }
        planets.sort(Comparator.comparingInt(o -> o.z));
        for (int i = 0; i < N - 1; i++) {
            tunnels.add(new Tunnel(planets.get(i), planets.get(i + 1)));
        }

        tunnels.sort(Comparator.comparingInt(o -> o.cost));
        long result = 0;
        int cnt = 0;
        for (Tunnel tunnel : tunnels) {
            if (!is_diff_group(tunnel.x, tunnel.y)) continue;
            result += tunnel.cost;
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