import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        tree = new long[N * 4];
        init(1, 1, N);

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (x <= y) {
                bw.write(query(1, 1, N, x, y) + "\n");
            } else {
                bw.write(query(1, 1, N, y, x) + "\n");
            }
            update(1, 1, N, a, b);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void update(int node, int start, int end, int index, long val) {
        if (index < start || end < index) return;
        if (start == end) {
            arr[index] = val;
            tree[node] = val;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, index, val);
        update(rc, mid + 1, end, index, val);
        tree[node] = tree[lc] + tree[rc];
    }

    private static long query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0L;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return query(lc, start, mid, left, right) + query(rc, mid + 1, end, left, right);
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = tree[lc] + tree[rc];
    }
}