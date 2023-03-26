import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] arr = new long[1000001];
    static long[] tree = new long[1 << 21];
    static long[] lazy = new long[1 << 21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
        }
        init(1, 1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {   // b 번째 수부터 c번째 수에 d를 더한다.
                long d = Long.parseLong(st.nextToken());
                update_range(1, 1, N, b, c, d);
            } else {        // b번째 수부터 c번째 수의 합을 출력한다.
                bw.write(sum(1, 1, N, b, c) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void update_range(int node, int start, int end, int left, int right, long diff) {
        update_lazy(node, start, end);
        if (end < left || right < start) return;
        if (left <= start && end <= right) {
            tree[node] += (end - start + 1) * diff;
            if (start != end) {
                lazy[node * 2] += diff;
                lazy[node * 2 + 1] += diff;
            }
            return;
        }
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        update_range(lc, start, mid, left, right, diff);
        update_range(rc, mid + 1, end, left, right, diff);
        tree[node] = tree[lc] + tree[rc];
    }

    private static long sum(int node, int start, int end, int left, int right) {
        update_lazy(node, start, end);
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        return sum(lc, start, mid, left, right) + sum(rc, mid + 1, end, left, right);
    }

    private static void update_lazy(int node, int start, int end) {
        if (lazy[node] == 0) return;
        tree[node] += (end - start + 1) * lazy[node];
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = tree[lc] + tree[rc];
    }
}