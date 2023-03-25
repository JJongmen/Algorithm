import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dvd_idx = new int[100001];
    static int[] tree = new int[1 << 19];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= N; i++) {
                dvd_idx[i] = M + i;
            }
            init(1, 0, N + M);

            int return_idx = M;
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                sb.append(query(1, 0, N + M, 0, dvd_idx[num] - 1)).append(' ');
                update(1, 0, N + M, dvd_idx[num], -1);
                update(1, 0, N + M, dvd_idx[num] = return_idx--, 1);
            }
            sb.append('\n');
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void update(int node, int start, int end, int index, int diff) {
        if (index < start || end < index) return;
        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            int lc = node * 2;
            int rc = lc + 1;
            update(lc, start, mid, index, diff);
            update(rc, mid + 1, end, index, diff);
        }
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        return query(lc, start, mid, left, right) + query(rc, mid + 1, end, left, right);
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            if (start > M) {
                tree[node] = 1;
            } else {
                tree[node] = 0;
            }
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