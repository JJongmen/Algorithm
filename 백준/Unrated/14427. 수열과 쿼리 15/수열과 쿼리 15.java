import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000000005;
    static int N, M;
    static int[] arr = new int[100001];
    static int[] tree = new int[100000 * 4 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, 1, N);
        tree[0] = INF;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                arr[i] = v;
                update(1, 1, N, i);
            } else {
                bw.write(getMinIdx(1, 1, N, 1, N) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getMinIdx(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        int lIdx = getMinIdx(lc, start, mid, left, right);
        int rIdx = getMinIdx(rc, mid + 1, end, left, right);
        if (arr[lIdx] < arr[rIdx]) {
            return lIdx;
        }
        return rIdx;
    }

    private static void update(int node, int start, int end, int index) {
        if (end < index || index < start) return;
        if (start == end) return;
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, index);
        update(rc, mid + 1, end, index);
        if (arr[tree[lc]] < arr[tree[rc]]) {
            tree[node] = tree[lc];
        } else if (arr[tree[lc]] == arr[tree[rc]]) {
            tree[node] = Math.min(tree[lc], tree[rc]);
        } else {
            tree[node] = tree[rc];
        }
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        if (arr[tree[lc]] < arr[tree[rc]]) {
            tree[node] = tree[lc];
        } else if (arr[tree[lc]] == arr[tree[rc]]) {
            tree[node] = Math.min(tree[lc], tree[rc]);
        } else {
            tree[node] = tree[rc];
        }
    }
}