import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, M;
    static int[] dist = new int[100003];
    static boolean[] isUsed = new boolean[1003];
    static List<List<Integer>> tubeToStation = new ArrayList<>();
    static List<List<Integer>> stationToTube = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int tube = 1; tube <= M; tube++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                int station = Integer.parseInt(st.nextToken());
                tubeToStation.get(tube).add(station);
                stationToTube.get(station).add(tube);
            }
        }

        if (N == 1) {
            System.out.println(1);
            return;
        }

        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        dist[1] = 1;
        while (!que.isEmpty()) {
            Integer curStation = que.poll();
            for (Integer nxtTube : stationToTube.get(curStation)) {
                if (isUsed[nxtTube]) continue;
                isUsed[nxtTube] = true;
                for (Integer nxtStation : tubeToStation.get(nxtTube)) {
                    if (dist[nxtStation] != -1) continue;
                    que.offer(nxtStation);
                    dist[nxtStation] = dist[curStation] + 1;
                    if (nxtStation == N) {
                        System.out.println(dist[nxtStation]);
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static void init() {
        for (int i = 0; i <= N; i++) {
            stationToTube.add(new LinkedList<>());
        }
        for (int i = 0; i <= M; i++) {
            tubeToStation.add(new LinkedList<>());
        }
        Arrays.fill(dist, 1, N + 1, -1);
    }
}