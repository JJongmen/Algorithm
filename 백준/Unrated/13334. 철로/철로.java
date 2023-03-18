import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Location[] locations;
    static int D;

    static class Location {
        int left, right;

        public Location(int home, int office) {
            if (home < office) {
                left = home;
                right = office;
            } else {
                left = office;
                right = home;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        locations = new Location[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            locations[i] = new Location(h, o);
        }
        D = Integer.parseInt(br.readLine());

        int result = 0;
        Arrays.sort(locations, (o1, o2) -> {
            if (o1.right == o2.right) {
                return o1.left - o2.left;
            }
            return o1.right - o2.right;
        });
        PriorityQueue<Location> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.left));
        for (Location location : locations) {
            if (location.right - location.left > D) continue;
            int roadStart = location.right - D;
            pq.offer(location);
            while (!pq.isEmpty() && pq.peek().left < roadStart) {
                pq.poll();
            }
            result = Math.max(result, pq.size());
        }
        System.out.println(result);
    }
}