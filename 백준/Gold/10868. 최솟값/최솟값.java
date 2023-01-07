import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000000005;
    static int N, M;
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[N * 4];
        init(1, 1, N);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(query(1, 1, N, a, b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return INF;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return Math.min(query(lc, start, mid, left, right), query(rc, mid + 1, end, left, right));
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
}