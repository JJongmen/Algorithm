import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static int N;
    static int[] speeds = new int[500000];  // 각 선수들의 상대적인 속도
    static int[] tree = new int[2000000];
    static class Runner {
        int idx, speed;

        public Runner(int idx, int speed) {
            this.idx = idx;
            this.speed = speed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Runner[] runners = new Runner[N];
        for (int i = 0; i < N; i++) {
            runners[i] = new Runner(i, Integer.parseInt(br.readLine()));
        }
        Arrays.sort(runners, Comparator.comparingInt(o -> o.speed));

        for (int i = 0; i < N; i++) {
            speeds[runners[i].idx] = i;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            int speed = speeds[i];
            bw.write(sum(1, 0, N - 1, speed + 1, N - 1) + 1 + "\n");
            update(1, 0, N - 1, speed);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void update(int node, int start, int end, int index) {
        if (index < start || end < index) return;
        tree[node]++;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(node * 2, start, mid, index);
        update(node * 2 + 1, mid + 1, end, index);
    }

    private static int sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}