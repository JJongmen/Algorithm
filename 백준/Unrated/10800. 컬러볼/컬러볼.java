import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Ball[] balls;
    static int[] colors;
    static int[] sizes;

    static class Ball {
        int idx;
        int color, size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        balls = new Ball[N];
        colors = new int[N + 1];
        sizes = new int[2001];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, C, S);
        }
        Arrays.sort(balls, (o1, o2) -> {
            if (o1.size == o2.size) {
                return o1.color - o2.color;
            }
            return o1.size - o2.size;
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int prefixSum = 0;
        int[] sizeSum = new int[N];
        for (int i = 0; i < balls.length; i++) {
            Ball ball = balls[i];
            prefixSum += ball.size;
            colors[ball.color] += ball.size;
            sizes[ball.size] += ball.size;
            if (i > 0 && balls[i - 1].color == ball.color && balls[i - 1].size == ball.size) sizeSum[ball.idx] = sizeSum[balls[i - 1].idx];
            else sizeSum[ball.idx] = prefixSum - colors[ball.color] - sizes[ball.size] + ball.size;
        }

        for (int sum : sizeSum) {
            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}