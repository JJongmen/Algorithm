import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_VALUE = 65536;
    static int N, K;
    static int[] arr = new int[250001];
    static int[] tree = new int[65537 * 4 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            update(1, 0, MAX_VALUE, arr[i], 1);
            if (i < K) continue;
            result += query(1, 0, MAX_VALUE, (K + 1) / 2);
            update(1, 0, MAX_VALUE, arr[i - K + 1], -1);
        }
        System.out.println(result);
    }

    private static int query(int node, int start, int end, int cnt) {
        if (start == end) return start;
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        if (tree[lc] >= cnt) return query(lc, start, mid, cnt);
        return query(rc, mid + 1, end, cnt - tree[lc]);
    }

    private static void update(int node, int start, int end, int idx, int diff) {
        if (end < idx || idx < start) return;
        tree[node] += diff;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, diff);
        update(node * 2 + 1, mid + 1, end, idx, diff);
    }
}