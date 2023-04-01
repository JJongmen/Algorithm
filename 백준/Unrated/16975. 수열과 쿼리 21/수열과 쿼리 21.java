import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr = new int[100001];
    static long[] tree = new long[400000];
    static long[] lazy = new long[400000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                update(1, 1, N, i, j, k);
            } else {
                int x = Integer.parseInt(st.nextToken());
                bw.write(sum(1, 1, N, x, x) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static long sum(int node, int start, int end, int left, int right) {
        update_lazy(node, start, end);
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return sum(lc, start, mid, left, right) + sum(rc, mid + 1, end, left, right);
    }

    private static void update(int node, int start, int end, int left, int right, int diff) {
        update_lazy(node, start, end);
        if (end < left || right < start) return;
        int lc = node * 2;
        int rc = lc + 1;
        if (left <= start && end <= right) {
            tree[node] += (long) (end - start + 1) * diff;
            if (start != end) {
                lazy[lc] += diff;
                lazy[rc] += diff;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(lc, start, mid, left, right, diff);
        update(rc, mid + 1, end, left, right, diff);
        tree[node] = tree[lc] + tree[rc];
    }

    private static void update_lazy(int node, int start, int end) {
        if (lazy[node] == 0) return;
        tree[node] += (end - start + 1) * lazy[node];
        if (start != end) {
            int lc = node * 2;
            int rc = lc + 1;
            lazy[lc] += lazy[node];
            lazy[rc] += lazy[node];
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