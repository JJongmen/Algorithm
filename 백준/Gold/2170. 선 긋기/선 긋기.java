import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Position(x, y));
        }

        int result = 0;
        int st = pq.peek().x;
        int en = pq.peek().y;
        while (!pq.isEmpty()) {
            Position cur = pq.poll();
            if (cur.x <= en) {
                en = Math.max(en, cur.y);
            } else {
                result += en - st;
                st = cur.x;
                en = cur.y;
            }
        }
        result += en - st;
        System.out.println(result);
    }
}