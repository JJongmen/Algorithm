import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isPrime = new boolean[10000];
    static {
        Arrays.fill(isPrime, 2, 10000, true);
        for (int i = 2; i < 100; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < 10000; j += i) {
                isPrime[j] = false;
            }
        }
    }

    static class FourDigits {
        int a, b, c, d;

        public FourDigits(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            bw.write(bfs(A, B));
        }
        bw.flush();
    }

    private static String bfs(int start, int end) {
        int[] dist = new int[10000];
        Arrays.fill(dist, 1000, 10000, -1);
        Queue<FourDigits> que = new LinkedList<>();
        String s = Integer.toString(start);
        que.offer(new FourDigits(start / 1000, (start / 100) % 10, (start / 10) % 10, start % 10));
        dist[start] = 0;
        while (!que.isEmpty()) {
            FourDigits cur = que.poll();
            if (d2i(cur) == end) break;
            for (int i = 1; i <= 9; i++) {
                int num = d2i(i, cur.b, cur.c, cur.d);
                if (!isPrime[num] || dist[num] != -1) continue;
                que.offer(new FourDigits(i, cur.b, cur.c, cur.d));
                dist[num] = dist[d2i(cur)] + 1;
            }
            for (int i = 0; i <= 9; i++) {
                int num = d2i(cur.a, i, cur.c, cur.d);
                if (isPrime[num] && dist[num] == -1) {
                    que.offer(new FourDigits(cur.a, i, cur.c, cur.d));
                    dist[num] = dist[d2i(cur)] + 1;
                }
                num = d2i(cur.a, cur.b, i, cur.d);
                if (isPrime[num] && dist[num] == -1) {
                    que.offer(new FourDigits(cur.a, cur.b, i, cur.d));
                    dist[num] = dist[d2i(cur)] + 1;
                }
                num = d2i(cur.a, cur.b, cur.c, i);
                if (isPrime[num] && dist[num] == -1) {
                    que.offer(new FourDigits(cur.a, cur.b, cur.c, i));
                    dist[num] = dist[d2i(cur)] + 1;
                }
            }
        }
        return dist[end] == -1 ? "Impossible\n" : dist[end] + "\n";
    }

    private static int d2i(int a, int b, int c, int d) {
        return 1000 * a + 100 * b + 10 * c + d;
    }

    private static int d2i(FourDigits fDigits) {
        return 1000 * fDigits.a + 100 * fDigits.b + 10 * fDigits.c + fDigits.d;
    }
}