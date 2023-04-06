import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MIN_TEMP = 0;
    public static final int MAX_TEMP = 65535;
    static int N, K;
    static int[] arr = new int[250001];
    static int[] tree = new int[(MAX_TEMP + 1) * 4 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            update(1, MIN_TEMP, MAX_TEMP, arr[i], 1);
            if (i >= K) {
                result += query(1, MIN_TEMP, MAX_TEMP, (K + 1) / 2);
                update(1, MIN_TEMP, MAX_TEMP, arr[i - K + 1], -1);
            }
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

    private static void update(int node, int start, int end, int index, int diff) {
        if (end < index || index < start) return;
        if (start == end) {
            tree[node] += diff;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, index, diff);
        update(rc, mid + 1, end, index, diff);
        tree[node] = tree[lc] + tree[rc];
    }
}