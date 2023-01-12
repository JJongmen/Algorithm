import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Road>[] graph;
    static List<Integer>[] comeFrom;
    static int[] times;
    static boolean[] visit;

    static class Road {
        int to;
        int time;

        public Road(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        comeFrom = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            comeFrom[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Road(b, c));
            comeFrom[b].add(a);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        times = new int[N + 1];
        Queue<Road> que = new LinkedList<>();
        que.offer(new Road(start, 0));
        while (!que.isEmpty()) {
            Road cur = que.poll();
            if (times[cur.to] != cur.time) continue;
            for (Road nxt : graph[cur.to]) {
                int nxtTime = times[cur.to] + nxt.time;
                if (times[nxt.to] < nxtTime) {
                    times[nxt.to] = nxtTime;
                    que.offer(new Road(nxt.to, nxtTime));
                }
            }
        }

        visit = new boolean[N + 1];
        System.out.println(times[end]);
        System.out.println(countRoad(end));
    }

    private static int countRoad(int end) {
        int cnt = 0;
        for (Integer from : comeFrom[end]) {
            int time = 0;
            for (Road road : graph[from]) {
                if (road.to == end) {
                    time = road.time;
                    break;
                }
            }
            if (times[from] + time == times[end]) {
                cnt++;
                if (visit[from]) continue;
                visit[from] = true;
                cnt += countRoad(from);
            }
        }
        return cnt;
    }
}