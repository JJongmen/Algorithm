import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX = 2_000_000;
    static int N;
    static int[] tree = new int[MAX * 4 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            if (T == 1) {
                update(1, 1, MAX, X, 1);
            } else {
                int num = query(1, 1, MAX, X);
                bw.write(num + "\n");
                update(1, 1, MAX, num, -1);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int query(int node, int start, int end, int cnt) {
        if (start == end) return start;
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
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