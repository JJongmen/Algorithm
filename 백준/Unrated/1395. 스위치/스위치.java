import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] tree = new int[1 << 20];
    static int[] lazy = new int[1 << 20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int O = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            if (O == 0) {
                reverse(1, 1, N, S, T);
            } else {
                bw.write(sum(1, 1, N, S, T) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int sum(int node, int start, int end, int left, int right) {
        lazy_reverse(node, start, end);
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    private static void lazy_reverse(int node, int start, int end) {
        if (lazy[node] == 0) return;
        if (lazy[node] % 2 == 1) {
            tree[node] = (end - start + 1) - tree[node];
        }
        if (start != end) {
            int lc = node * 2;
            int rc = lc + 1;
            lazy[lc] += lazy[node];
            lazy[rc] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void reverse(int node, int start, int end, int left, int right) {
        lazy_reverse(node, start, end);
        if (end < left || right < start) return;
        int lc = node * 2;
        int rc = lc + 1;
        if (left <= start && end <= right) {
            tree[node] = (end - start + 1) - tree[node];
            lazy[lc]++;
            lazy[rc]++;
            return;
        }
        int mid = (start + end) / 2;
        reverse(lc, start, mid, left, right);
        reverse(rc, mid + 1, end, left, right);
        tree[node] = tree[lc] + tree[rc];
    }
}