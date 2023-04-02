import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static long[] tree = new long[1 << 21 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int x = Integer.parseInt(st.nextToken());
                update(1, 1, N, p, x);
            } else {
                int q = Integer.parseInt(st.nextToken());
                bw.write(sum(1, 1, N, p, q) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return sum(lc, start, mid, left, right) + sum(rc, mid + 1, end, left, right);
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