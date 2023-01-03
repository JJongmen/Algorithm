import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Position[] pos;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            pos = new Position[n + 2];
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                pos[i] = new Position(x, y);
            }

            bw.write(bfs(0) ? "happy\n" : "sad\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs(int start) {
        boolean[] visit = new boolean[n + 2];
        Queue<Position> que = new LinkedList<>();
        que.offer(pos[start]);
        visit[start] = true;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < n + 2; i++) {
                if (visit[i]) continue;
                int dist = Math.abs(pos[i].x - cur.x) + Math.abs(pos[i].y - cur.y);
                if (dist > 1000) continue;
                que.offer(pos[i]);
                visit[i] = true;
            }
        }
        return visit[n + 1];
    }
}