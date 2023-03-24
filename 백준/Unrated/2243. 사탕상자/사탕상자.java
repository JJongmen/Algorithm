import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int TASTE_CNT = 1000000;
    static int[] tree = new int[1 << 21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == 1) {
                int taste = lower_bound(B);
                bw.write(taste + "\n");
                update(1, 1, TASTE_CNT, taste, -1);
            } else {
                int C = Integer.parseInt(st.nextToken());
                update(1, 1, TASTE_CNT, B, C);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lower_bound(int target) {
        int l = 0;
        int r = TASTE_CNT;
        while (l < r) {
            int mid = (l + r) / 2;
            int cnt = query(1, 1, TASTE_CNT, 1, mid);
            if (target <= cnt) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static void update(int node, int start, int end, int index, int diff) {
        if (index < start || end < index) return;
        tree[node] += diff;
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        if (start != end) {
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
}
