import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static int[][] board = new int[105][105];
    static int rSize = 3;
    static int cSize = 3;

    static class Number {
        int num;
        int cnt;

        public Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static void rCalculate() {
        int newCSize = 0;
        for (int i = 1; i <= rSize; i++) {
            int[] counts = new int[101];
            for (int j = 1; j <= cSize; j++) {
                if (board[i][j] == 0) continue;
                counts[board[i][j]]++;
            }
            PriorityQueue<Number> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1.cnt == o2.cnt) return o1.num - o2.num;
                return o1.cnt - o2.cnt;
            });
            for (int k = 1; k <= 100; k++) {
                if (counts[k] == 0) continue;
                pq.offer(new Number(k, counts[k]));
            }
            int idx = 0;
            while (!pq.isEmpty()) {
                Number number = pq.poll();
                board[i][++idx] = number.num;
                board[i][++idx] = number.cnt;
                if (idx == 100) break;
            }
            for (int j = idx + 1; j <= cSize; j++) board[i][j] = 0;
            newCSize = Math.max(newCSize, idx);
        }
        cSize = newCSize;
    }

    static void cCalculate() {
        int newRSize = 0;
        for (int j = 1; j <= cSize; j++) {
            int[] counts = new int[101];
            for (int i = 1; i <= rSize; i++) {
                if (board[i][j] == 0) continue;
                counts[board[i][j]]++;
            }
            PriorityQueue<Number> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1.cnt == o2.cnt) return o1.num - o2.num;
                return o1.cnt - o2.cnt;
            });
            for (int k = 1; k <= 100; k++) {
                if (counts[k] == 0) continue;
                pq.offer(new Number(k, counts[k]));
            }
            int idx = 0;
            while (!pq.isEmpty()) {
                Number number = pq.poll();
                board[++idx][j] = number.num;
                board[++idx][j] = number.cnt;
                if (idx == 100) break;
            }
            for (int i = idx + 1; i <= rSize; i++) board[i][j] = 0;
            newRSize = Math.max(newRSize, idx);
        }
        rSize = newRSize;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        for (; board[r][c] != k; time++) {
            if (rSize >= cSize) rCalculate();
            else cCalculate();
            if (time > 100) {
                time = -1;
                break;
            }
        }

        System.out.println(time);
    }
}