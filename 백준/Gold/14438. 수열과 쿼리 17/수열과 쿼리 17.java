import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[] arr;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new int[1 << (h + 1)];
        init(1, 1, N);

        int M = Integer.parseInt(br.readLine());
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                update(1, 1, N, i, v);
            } else {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                bw.write(query(1, 1, N, i, j) + "\n");
            }
        }
        bw.flush();
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return INF;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        int lVal = query(lc, start, mid, left, right);
        int rVal = query(rc, mid + 1, end, left, right);
        return Math.min(lVal, rVal);
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
        tree[node] = Math.min(tree[lc], tree[rc]);
    }

    private static void update(int node, int start, int end, int index, int val) {
        if (index < start || index > end) return;
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
        tree[node] = Math.min(tree[lc], tree[rc]);
    }
}